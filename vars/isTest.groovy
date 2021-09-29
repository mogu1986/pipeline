def call() {
    def flag = "${params.BUILD_ENV}" == 'test'
    return flag
}