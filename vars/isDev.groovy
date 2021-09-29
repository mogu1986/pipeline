def call() {
    def flag = "${params.BUILD_ENV}" == 'dev'
    return flag
}