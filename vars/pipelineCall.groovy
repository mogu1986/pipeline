def call(String app="${JOB_NAME}", String lang = "${JOB_NAME}") {

    log.debug("app = ${app}")

    def params = new com.mw.AppMeta().getMeta(app)
    
    if (params != null) {
        lang = params.get('lang')
        log.debug("${app} 查找结果 : ${params}")

        putSonar(params)
    }

    log.debug("lang : ${lang}")

    switch (lang) {
        case "jar":
            pipeline_jar(params)
            break
        case "java":
            pipeline_java(params)
            break
        case "nodejs":
            pipeline_nodejs(params)
            break
        case "sync-harbor":
            pipeline_harbor(params)
            break
        case "sync-nodejs":
            pipeline_sync_nodejs(params)
            break
        case "release":
            pipeline_release(params)
            break
        case "aliyun-harbor-nodejs":
            pipeline_sync_nodejs(params)
            break
        case "aliyun-harbor-html":
            pipeline_sync_html(params)
            break
        default:
            println "nice to meet you"
    }

}

def putSonar(Map params) {

    def keys = ['sonar.sources', 'sonar.java.binaries']

    for (int i = 0; i < keys.size(); i++) {
        def key = keys.get(i)
        if (!params.containsKey(key)) {
            params.put(key, '.')
        }
    }

    log.debug("sonar.sources = ${params['sonar.sources']}")
    log.debug("sonar.java.binaries = ${params['sonar.java.binaries']}")
}
