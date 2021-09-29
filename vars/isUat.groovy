def call() {
    def flag = "${params.BUILD_ENV}" == 'uat'
    return flag
}