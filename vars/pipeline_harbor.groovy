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
        }

        parameters {
            string(name: 'app', defaultValue: '', description: '输入app:')
            string(name: 'imageId', defaultValue: '', description: '输入镜像ID:')
        }

        stages {

            stage('同步腾讯云') {
                steps {
                    script {
                        pushTencent("${params.app}", "${params.imageId}")
                    }
                }
            }

        }

        post {
            always {cleanWs()}
        }

    }
}
