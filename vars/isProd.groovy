def call() {
    def flag = ("${params.BUILD_ENV}" == 'prod') || ("${env.BUILD_ENV}" == "prod")
    return flag
}