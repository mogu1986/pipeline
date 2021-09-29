/*
*  Description: java pipeline as code
*  sh "npm install -g cnpm --registry=https://registry.npm.taobao.org"
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
            timeout(time: 30, unit: 'MINUTES')
        }

        parameters {
            string(name: 'app', defaultValue: 'mw-login-vue', description: '输入app:')
        }

        stages {

            stage('拉取代码') {
                steps {
                    script {
                        log.debug("同步app: ${params.app}")
                        log.debug("部署环境: uat")
                        def appMeta = new com.mw.AppMeta().getMeta("${params.app}")
                        log.debug("appMeta : ${appMeta}")

                        git branch: 'test', credentialsId: 'gitlab', url: appMeta.git
                    }
                }
            }

            stage('拉取配置') {
                steps {
                    script {
                        sh "git clone -b uat http://gitlab.top.mw/devops/config.git mw_config"
                        sh "mv mw_config/${params.app}/nginx.conf ./"
                        sh "rm -rf mw_config"
                        sh "ls -lh"
                    }
                }
            }

            stage('编译') {
                when {
                    expression {
                        return isIgnore(new com.mw.AppMeta().getMeta("${params.app}"))
                    }
                }
                steps {
                    nodejs('NODEJS') {
                        sh "yarn install"
                        sh "yarn run build --mode uat"
                        sh "ls -lh"
                    }
                }
            }

            stage('推送镜像') {
                steps {
                    script {

                        env.IMAGE_NAME = "${HARBOR}/library/${params.app}:${BUILD_ID}"

                        docker.withRegistry("$HARBOR_URL", "harbor") {
                            configFileProvider([configFile(fileId: 'dockerfile_nodejs', variable: 'DOCKER_FILE')]) {
                                def appMeta = new com.mw.AppMeta().getMeta("${params.app}")

                                def ARTIFACT = "${appMeta.artifact}"

                                log.debug("ARTIFACT = ${ARTIFACT}")

                                def app = docker.build("$IMAGE_NAME", "--no-cache --build-arg ARTIFACT_NAME=${ARTIFACT} -f ${DOCKER_FILE} .")
                                app.push()
                            }
                        }
                    }
                }
            }

            stage('同步腾讯云') {
                steps {
                    script {
                        pushTencent("${params.app}", "${BUILD_ID}")
                    }
                }
            }

        }

        post {
            always {cleanWs()}
        }

    }
}