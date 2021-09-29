def call(mvnExec) {
    configFileProvider(
        [configFile(fileId: "maven-global-settings", variable: 'MAVEN_SETTINGS_ENV')]) {
        docker.image('meiwu.tencentcloudcr.com/library/maven:3-jdk-8-alpine').inside("-v /root/.m2/${params.BUILD_ENV}:/root/.m2") {
            log.debug("MAVEN_SETTINGS: ${MAVEN_SETTINGS_ENV}")
            mvnExec("${MAVEN_SETTINGS_ENV}")
        }
    }
}