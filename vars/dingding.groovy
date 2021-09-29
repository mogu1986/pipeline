def call (boolean success, String zuser) {

    def text = success ? '成功' : '失败'
    def url = success ? 'http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/sign-check-icon.png' : 'http://www.iconsdb.com/icons/preview/soylent-red/x-mark-3-xxl.png'
    def env_text = "DEV"

    def token = "${env.DD_TEST}"

    if (isDev()) {
        token = "${env.DD_DEV}"
        env_text = "DEV"
    } else if(isTest()) {
        token = "${env.DD_TEST}"
        env_text = "TEST"
    } else if(isUat()) {
        token = "${env.DD_UAT}"
        env_text = "UAT"
    } else if(isProd()) {
        token = "${env.DD_PROD}"
        env_text = "PROD"
    }

    def ds = new Date().format("yyyy-MM-dd HH:mm:ss")

    def patchOrg = """
        {
            "msgtype": "link", 
            "link": {
                "title": "【${env_text}】环境 ${APP} 构建${text}",
                "text": "构建编号：${BUILD_NUMBER}
构建人：${zuser}
构建时间：${ds}", 
                "picUrl": "${url}", 
                "messageUrl": "${BUILD_URL}"
            }
        }
    """

    def response = httpRequest(
            url: "https://oapi.dingtalk.com/robot/send?access_token=${token}",
            httpMode: 'POST',
            requestBody: patchOrg,
            contentType: 'APPLICATION_JSON_UTF8'
    )
    println('Status: '+response.status)
    println('Response: '+response.content)
}