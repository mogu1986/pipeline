/*
*  Description: java pipeline as code
*  Date: 2019-04-23 16:14
*  Author: gaowei
*/
def call(Map map) {
    pipeline {

        agent {
            label 'nodejs'
        }

        options {
            buildDiscarder(logRotator(numToKeepStr: '50'))
            disableConcurrentBuilds()
            timeout(time: 30, unit: 'MINUTES')
        }

        environment {
            APP = "${map.app}"
            LANG = "${map.lang}"

            ARTIFACT = "${map.artifact}"
            SYNC_TOKEN = credentials("sync-token")

            IMAGE_NAME = "${HARBOR}/library/${JOB_NAME}:${BUILD_ID}"

            identity = credentials("ssh-ops-identity")
            ssh_ops = credentials("ssh_ops")

            DD_DEV = credentials("dd_dev")
            DD_TEST = credentials("dd_test")
            DD_UAT = credentials("dd_uat")
            DD_PROD = credentials("dd_prod")
        }

        parameters {
            choice(name: 'BUILD_ENV', choices: 'dev\ntest\nuat\nprod', description: '请选择部署环境:')
            gitParameter(branchFilter: 'origin/(.*)', defaultValue: 'dev', name: 'BUILD_BRANCH', type: 'PT_BRANCH', description: '请选择分支:', useRepository: "${map.git}")
        }

        stages {

            stage('输入密钥') {
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

                        def branch = "${params.BUILD_BRANCH}"

                        if(isProd()) {
                            if ("${APP}".startsWith("surmax-manager-h5")) {
                                branch = "master"
                            } else {
                                branch = "release"
                            }
                        }

                        log.debug("部署环境: ${params.BUILD_ENV}")
                        log.debug("选择的分支: ${branch}")

                        git branch: "${branch}", credentialsId: 'gitlab', url: map.git
                    }
                }
            }

            stage('拉取配置') {
                steps {
                    script {
                        sh "git clone -b ${params.BUILD_ENV} http://gitlab.top.mw/devops/config.git mw_config"
                        sh "mv mw_config/${env.APP}/nginx.conf ./"
                        sh "rm -rf mw_config"
                        sh "ls -lh"
                    }
                }
            }

            stage('编译') {
                when {
                    expression {
                        return isIgnore(map)
                    }
                }
                steps {
                    script {
                        nodejs('NODEJS') {
                            sh "yarn install"
                            if ("${APP}".startsWith("surmax-manager-h5")) {
                                if (isProd()) {
                                    sh "yarn run build --mode production"
                                } else {
                                    sh "yarn run build --mode development"
                                }
                            } else {
                                sh "yarn run build --mode ${params.BUILD_ENV}"
                            }
                            sh "ls -lh"
                        }
                    }
                }
            }

            stage('推送镜像') {
                steps {
                    script {
                        docker.withRegistry("$HARBOR_URL", "harbor") {
                            configFileProvider([configFile(fileId: 'dockerfile_nodejs', variable: 'DOCKER_FILE')]) {
                                def app = docker.build("$IMAGE_NAME", "--no-cache --build-arg ARTIFACT_NAME=${ARTIFACT} -f ${DOCKER_FILE} .")
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
                        configFileProvider([configFile(fileId: "${params.BUILD_ENV}-k8s-config", variable: 'config')]) {
                            if (isUat() || isProd()) {
                                // push images to tencent
                                pushTencent("${env.APP}", "${BUILD_ID}")

                                if (isUat()) {
                                    kubectl("${params.BUILD_ENV}") { kubectl ->
                                        sh "${kubectl} -n ${params.BUILD_ENV} set image deployment ${env.APP} ${env.APP}=meiwu.tencentcloudcr.com/library/${env.APP}:${BUILD_ID}"
                                    }
                                }

                                // 生产环境
                                if (isProd()) {
                                    def remote = [:]
                                    remote.name = 'root'
                                    remote.host = "${ssh_ops_USR}"
                                    remote.port = Integer.valueOf("${ssh_ops_PSW}")
                                    remote.identityFile = "${identity}"
                                    remote.user = 'root'
                                    remote.allowAnyHosts = true
                                    remote.logLevel = 'INFO'

                                    def cmd = new StringBuffer("#!/bin/bash\n")
                                    cmd.append("kubectl -n prod set image deployment ${env.APP} ${env.APP}=meiwu.tencentcloudcr.com/library/${env.APP}:${BUILD_ID}")
                                    log.debug("${cmd}")

                                    writeFile file: 'cmd.sh', text: "${cmd}"
                                    sshPut remote: remote, from: 'cmd.sh', into: '.'
                                    sshScript remote: remote, script: "cmd.sh"
                                }

                            } else {
                                sh "docker run --rm -v ${config}:/.kube/config bitnami/kubectl:1.15 -n ${env.BUILD_ENV} set image deployment ${env.APP} ${env.APP}=${IMAGE_NAME}"
                            }
                        }
                    }
                }
            }

            stage("同步COS") {
                when {
                    expression {
                        return "${APP}".startsWith("meiwu-website-vue")
                    }
                }
                steps {
                    script {
                        configFileProvider([configFile(fileId: "${params.BUILD_ENV}-cos-config", variable: 'conf')]) {
//                            sh "zip -r static.zip public/images"
//                            sh "ls -lh"
//                            sh "coscmd -c ${conf} upload static.zip /static/ -r"
                            sh "coscmd -c ${conf} upload public/images/ /static/website_images/ -r"
                        }
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