def call() {
    def uatSubmit = "gaowei,gaohan,quandongya,yaohaiqiang,caojiahai1,maguochang,anxiaohu,mafei,zimingfei,zhangxiaodong,xiezhuihui,jiguanglin"
    def prodSubmit = "gaowei,xiezhuihui,quandongya,yaohaiqiang,caojiahai1,jiguanglin,zhangxiaodong,wangqingyu"

    def submit = isProd() ? prodSubmit : uatSubmit

    timeout(time: 1, unit: 'MINUTES') {
        input (
            message: "即将发布到 ${params.BUILD_ENV} 环境，发布或者停止",
            ok: "确定",
            submitter: submit
        )
    }

}