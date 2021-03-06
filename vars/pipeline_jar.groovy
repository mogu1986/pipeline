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
        }

        parameters {
            choice(name: 'BUILD_ENV', choices: 'dev\ntest', description: '请选择环境:')
            gitParameter(branchFilter: 'origin/(.*)', defaultValue: 'dev', name: 'BUILD_BRANCH', type: 'PT_BRANCH', description: '请选择分支:', useRepository: "${map.git}")
        }

        stages {

            stage('拉取代码') {
                steps {
                    script {
                        log.debug("选择的分支: ${params.BUILD_BRANCH}")
                        git branch: "${params.BUILD_BRANCH}", credentialsId: 'gitlab', url: map.git
                    }
                }
            }

            stage('编译发布') {
                steps {
                    mvn { settings ->
                        sh "mvn -s ${settings} clean deploy -B -P${params.BUILD_ENV} -Dfile.encoding=UTF-8 -Dmaven.test.skip=true -U"
                    }
                }
            }

            stage('Sonar分析') {
                when {
                    expression {
                        return false
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

        }

        post {
            always {cleanWs()}
        }

    }
}