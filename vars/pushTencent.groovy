def call(String app, String imageId) {
    env.IMAGE_NAME = "${HARBOR}/library/${app}:${imageId}"

    env.QCLOUD_IMAGE_NAME = "meiwu.tencentcloudcr.com/library/${app}:${imageId}"

    log.debug("腾讯云镜像ID = ${env.QCLOUD_IMAGE_NAME}")

    docker.withRegistry("https://meiwu.tencentcloudcr.com", "harbor_tencent") {
        sh "docker pull $IMAGE_NAME"
        sh "docker tag $IMAGE_NAME $QCLOUD_IMAGE_NAME"
        sh "docker push $QCLOUD_IMAGE_NAME"
    }
    sh "docker rmi -f $QCLOUD_IMAGE_NAME"
    sh "docker rmi -f $IMAGE_NAME"
}