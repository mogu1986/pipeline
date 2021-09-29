/*
*  Description: jfb_ec_wx 定制的 pipeline，需要先打wx-web，然后把文件放入webapps下面
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
            timeout(time: 10, unit: 'MINUTES')
        }

        environment {
            identity = credentials("ssh-ops-identity")
            ssh_ops = credentials("ssh_ops")

            DD_PROD = credentials("dd_prod")
        }

        parameters {
            string(name: 'app', defaultValue: '', description: '输入应用清单,逗号分割【例如】base,upload')
        }

        stages {

            stage('认证') {
                steps {
                    timeout(time: 1, unit: 'MINUTES') {
                        input (
                            message: "即将发布到 腾讯云 Kubernetes，发布或者停止",
                            ok: "确定",
                            submitter: "gaowei,xiezhuihui,jiguanglin"
                        )
                    }
                }
            }

            stage('参数验证') {
                steps {
                    script {

                        env.BUILD_ENV = "prod"
                        env.APP = "release"

                        def rs = "${params.app}".split(",");

                        rs.each { e ->
                            def entity = new com.mw.AppMeta().getMeta(e)

                            if (entity == null) {
                                throw new RuntimeException("${e} 参数有误，请检查后执行！");
                            }

                            if (entity.get('lang').equals('nodejs')) {
                                throw new RuntimeException("${e} 前端工程请切换Job发布！");
                            }
                        }
                    }
                }
            }

            stage('发布') {
                steps {
                    script {
                        def remote = [:]
                        remote.name = 'root'
                        remote.host = "${ssh_ops_USR}"
                        remote.port = Integer.valueOf("${ssh_ops_PSW}")
                        remote.identityFile = "${identity}"
                        remote.user = 'root'
                        remote.allowAnyHosts = true
                        remote.logLevel = 'INFO'

                        def cmd = new StringBuffer("#!/bin/bash\n")

                        def rs = "${params.app}".split(",");
                        rs.each { e ->
                            // get deployImageId
                            kubectl('uat') { kubectl ->
                                def deployedImageId = sh(returnStdout: true, script: "${kubectl} -n uat get deploy ${e} -o jsonpath={..containers[0].image}").trim()
                                cmd.append("kubectl -n prod set image deployment ${e} ${e}=${deployedImageId}\n")
                            }

                        }
                        log.debug("${cmd}")

                        writeFile file: 'cmd.sh', text: "${cmd}"
                        sshPut remote: remote, from: 'cmd.sh', into: '.'
                        sshScript remote: remote, script: "cmd.sh"
                    }
                }
            }

        }

        post {
            always {cleanWs()}

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
