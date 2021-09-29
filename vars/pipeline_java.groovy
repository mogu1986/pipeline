/*
*  Description: java pipeline as code
*  Date: 2019-04-23 16:14
*  Author: gaowei
*/
def call(Map map) {
    pipeline {

        agent {
            label 'swarm'
        }

        options {
            buildDiscarder(logRotator(numToKeepStr: '50'))
            disableConcurrentBuilds()
            timeout(time: 20, unit: 'MINUTES')
        }

        environment {
            APP = "${map.app}"
            LANG = "${map.lang}"
            APP_PORT = "${map.appPort}"
            GROUP= "${map.group}"
            ARTIFACT = "${map.artifact}"
            NS = "${map.namespace}"

            SYNC_TOKEN = credentials("sync-token")

            IMAGE_NAME = "${HARBOR}/library/${JOB_NAME}:${BUILD_ID}"

            DD_DEV = credentials("dd_dev")
            DD_TEST = credentials("dd_test")
            DD_UAT = credentials("dd_uat")
            DD_PROD = credentials("dd_prod")
        }

        parameters {
            choice(name: 'BUILD_ENV', choices: 'dev\ntest\nuat', description: '请选择部署环境:')
            gitParameter(branchFilter: 'origin/(.*)', defaultValue: 'dev', name: 'BUILD_BRANCH', type: 'PT_BRANCH', description: '请选择分支:', useRepository: "${map.git}")
        }

        stages {

            stage('认证') {
                when {
                    expression {
                        return !isDev()
                    }
                }
                steps {
                    auth()
                }
            }

            stage('清空目录') {
                steps {
                    deleteDir()
                }
            }

            stage('拉取代码') {
                steps {
                    script {
                        log.debug("部署环境: ${params.BUILD_ENV}")
                        log.debug("选择的分支: ${params.BUILD_BRANCH}")
                        log.debug("App 元数据: ${map}")
                        git branch: "${params.BUILD_BRANCH}", credentialsId: 'gitlab', url: map.git
                    }
                }
            }

            stage('编译') {
                steps {
                    script {
                        mvn { settings ->
                            def cmd = isDev() ? 'deploy' : 'deploy'
                            sh "mvn -s ${settings} clean ${cmd} -B -P${params.BUILD_ENV} -Dfile.encoding=UTF-8 -Dmaven.test.skip=true -U"
                        }
                    }
                }
            }

            stage('Sonar分析') {
                when {
                    expression {
                        return isTest()
                    }
                }
                steps {
                    script {
                        withSonarQubeEnv('sonar'){
                            docker.image('meiwu.tencentcloudcr.com/library/sonar-scanner:3.2.0.1227').inside('-v /root/.sonar:/root/.sonar') {
                                sh "sonar-scanner -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.login=${SONAR_AUTH_TOKEN}  -Dsonar.projectKey=${env.APP} -Dsonar.projectName=${env.APP} -Dsonar.sources=. -Dsonar.java.binaries=."
                            }
                        }
                    }
                }
            }

            stage('单元测试') {
                when {
                    expression {
                        return false
                    }
                }
                steps {
                    allure([
                            disabled: false,
                            includeProperties: false,
                            jdk: '',
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'output/allure']]
                    ])
                }
            }

            stage('自动化测试') {
                when {
                    expression {
                        return !isUat()
                    }
                }
                failFast true
                parallel {
                    stage('UI自动化测试') {
                        steps {
                            echo "并行1"
                        }
                    }
                    stage('性能自动化测试') {
                        steps {
                            echo "并行二"
                        }
                    }
                    stage('接口自动化测试') {
                        steps {
                            echo "In stage Nested 1 within Branch C"
                        }
                    }
                }
            }

            stage('推送镜像') {
                steps {
                    script {
                        configFileProvider([configFile(fileId: 'dockerfile', variable: 'DOCKER_FILE')]) {
                            docker.withRegistry("$HARBOR_URL", "harbor") {
                                def app = docker.build("$IMAGE_NAME", "--no-cache --build-arg JAR_PATH=${ARTIFACT} --build-arg JAR_NAME=${APP} -f ${DOCKER_FILE} .")
                                app.push()
                            }
                        }
                        sh "docker rmi -f $IMAGE_NAME"
                    }
                }
            }

            stage("K8S部署") {
                steps {
                    script {
                        if (isUat()) {
                            pushTencent("${env.APP}", "${BUILD_ID}")

                            // release
                            kubectl('uat') { kubectl ->
                                sh "${kubectl} -n uat set image deployment ${env.APP} ${env.APP}=meiwu.tencentcloudcr.com/library/${env.APP}:${BUILD_ID}"
                            }
                        } else {
                            kubectl("${params.BUILD_ENV}") { kubectl ->
                                sh "${kubectl} -n ${params.BUILD_ENV} set image deployment ${env.APP} ${env.APP}=${IMAGE_NAME}"
                            }
                        }
                    }
                }
            }

            stage('同步腾讯云') {
                when {
                    expression {
                        return isTest() && false
                    }
                }
                steps {
                    script {
                        def response = httpRequest(
                                url: "${env.JENKINS_URL}/view/prod/job/sync-harbor/buildWithParameters?token=${env.SYNC_TOKEN}&app=${env.APP}&imageId=${BUILD_ID}",
                                httpMode: 'GET'
                        )
                        println('Status: '+response.status)
                        println('Response: '+response.content)
                    }
                }
            }

        }

        post {
            success {
                wrap([$class: 'BuildUser']) {
                    dingding(true, "${BUILD_USER}")
                }
            }
            failure {
                wrap([$class: 'BuildUser']) {
                    dingding(false, "${BUILD_USER}")
                }
            }
        }


    }
}
