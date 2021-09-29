def call() {
    def flag = "${params.BUILD_ENV}" == 'uat_qcloud'
    return flag
}