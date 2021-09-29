def call(env, kubeExec) {
    configFileProvider([configFile(fileId: "${env}-k8s-config", variable: 'config')]) {
//        sh "docker run --rm -v ${config}:/.kube/config bitnami/kubectl:1.16 ${kubeExec}"
        kubeExec("docker run --rm -v ${config}:/.kube/config meiwu.tencentcloudcr.com/library/kubectl:1.15")
    }
}