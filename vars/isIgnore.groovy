def call(Map map) {

    def flag = true

    if (map.containsKey('compile') && map.get('compile') == false) {
        flag = false
    }
    log.debug("项目是否需要编译： ${flag}")
    return flag
}