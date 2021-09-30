package com.mw

class AppMeta {

    def meta = [
            //  jar
            [
                    app: "framework-parent",
                    lang: "jar",
                    git: "https://github.com/mogu1986/framework-parent.git"
            ],
            [
                    app: "feign-spring-boot-starter",
                    lang: 'jar',
                    git: 'https://github.com/mogu1986/feign-spring-boot-starter.git'
            ],
            [
                    app: "boot",
                    lang: 'boot',
                    git: 'https://github.com/mogu1986/boot.git'
            ],
            [
                    app: "redis-spring-boot-starter",
                    lang: 'jar',
                    git: 'https://github.com/mogu1986/redis-spring-boot-starter.git'
            ],
            [
                    app: "distribution",
                    lang: 'java',
                    namespace: 'default',
                    artifact: 'distribution-service/target/distribution-service.jar',
                    git: 'https://github.com/mogu1986/distribution.git'
            ],
            [
                    app: "order",
                    lang: 'java',
                    namespace: 'default',
                    artifact: 'order-service/target/order-service.jar',
                    git: 'https://github.com/mogu1986/order.git'
            ],
            //     start
            [
                    app: "mw-parent",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/mw-core/mw-parent.git'
            ],
            [
                    app: "mw-core-base",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/mw-core/mw-core-base.git'
            ],
            [
                    app: "mqcenter",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/base/mqcenter-spring-boot-starter.git'
            ],
            [
                    app: "public-pom",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/base/public-pom.git'
            ],
             [
                    app: "public-base",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/base/public-base.git'
            ],
            [
                    app: "mw-cloud-website",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'target/mw-cloud-website.jar',
                    git: 'http://gitlab.top.mw/mw-website/mw-cloud-website.git'
            ],
            [
                    app: "tech-process",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'technology-processTechnology-service-provider/target/technology-process-service.jar',
                    git: 'http://gitlab.top.mw/technical-management/technology-processTechnology.git'
            ],
            [
                    app: "mw-promotion",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-promotion-web/target/mw-promotion-web.jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/mw-promotion.git'
            ],
            //  production
            [
                    app: "production-base",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'production-base-service-provider/target/production-base-service.jar',
                    git: 'http://gitlab.top.mw/production-management/production-base.git'
            ],
            [
                    app: "production-task",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'production-task-service-provider/target/production-task-service.jar',
                    git: 'http://gitlab.top.mw/production-management/production-task.git'
            ],
            [
                    app: "mrp",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mrp-service-provider/target/mrp-service.jar',
                    git: 'http://gitlab.top.mw/production-management/mrp.git'
            ],
			
            //  mw-iim
            [
                    app: "mw-iim",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-iim-web/target/mw-iim.jar',
                    git: 'http://gitlab.top.mw/mw-iim/mw-iim.git'
            ],
            [
                    app: "mw-iim-admin",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-iim-admin-web/target/mw-iim-admin.jar',
                    git: 'http://gitlab.top.mw/mw-iim/mw-iim-admin.git'
            ],
            //  mw-pos
            [
                    app: "mw-pos",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-pos-web/target/mw-pos-web.jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-pos.git'
            ],
			
            //  base
            [
                    app: "base",
                    lang: 'java',
                    namespace: 'default',
                    artifact: 'base-service/target/base-service.jar',
                    git: 'http://gitlab.top.mw/base/base.git'
            ],
            [
                    app: "gateway",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'gateway-service/target/gateway-service.jar',
                    git: 'http://gitlab.top.mw/base/gateway.git'
            ],
            [
                    app: "mw-attach-gateway",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'target/mw-attach-gateway.jar',
                    git: 'http://gitlab.top.mw/base/mw-attach-gateway.git'
            ],
            [
                    app: "mw-encrypt-center",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'target/mw-encrypt-center.jar',
                    git: 'http://gitlab.top.mw/base/mw-encrypt-center.git'
            ],
            [
                    app: "authcenter",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'authcenter-service/target/authcenter-service.jar',
                    git: 'http://gitlab.top.mw/base/authcenter.git'
            ],
            [
                    app: "job",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'job-service/target/job-service.jar',
                    git: 'http://gitlab.top.mw/base/job.git'
            ],
            [
                    app: "msgcenter",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'msgcenter-service/target/msgcenter-service.jar',
                    git: 'http://gitlab.top.mw/base/msgcenter.git'
            ],
            [
                    app: "businessmsg",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'businessmsg-service/target/businessmsg-service.jar',
                    git: 'http://gitlab.top.mw/base/businessmsg.git'
            ],
            [
                    app: "point",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'point-service-provider/target/point-service-provider.jar',
                    git: 'http://gitlab.top.mw/salepoint/point.git'
            ],
            [
                    app: "upload",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'base-upload-service/target/base-upload-service.jar',
                    git: 'http://gitlab.top.mw/base/base-upload.git'
            ],
            [
                    app: "mw-msgcenter",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-msgcenter-service/target/mw-msgcenter-service.jar',
                    git: 'http://gitlab.top.mw/base/mw-msgcenter.git'
            ],
            [
                    app: "mw-transaction-server",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'target/mw-transaction-server.jar',
                    git: 'http://gitlab.top.mw/base/mw-transaction-server.git'
            ],
            //  account
            [
                    app: "user-account",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'user-account-service/target/user-account-service.jar',
                    git: 'http://gitlab.top.mw/user-account/user-account.git'
            ],
			
            //  admincenter
            [
                    app: "admincenter-approval",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'admin-center-approval-service/target/admincenter-approval-server.jar',
                    git: 'http://gitlab.top.mw/adminCenter/pay-approval-center.git'
            ],
			[
                    app: "admincenter-intangible-assets",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'admincenter-intangible-assets-service/target/admincenter-intangible-assets-service.jar',
                    git: 'http://gitlab.top.mw/adminCenter/intangibleAssets.git'
            ],
			[
                    app: "admincenter-approval-manage",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'admincenter-approval-manage-service/target/admincenter-approval-manage-service.jar',
                    git: 'http://gitlab.top.mw/adminCenter/approval-manage.git'
            ],
			
			//  bill
		    [
                    app: "billcenter",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'bill-center-service/target/bill-center-service.jar',
                    git: 'http://gitlab.top.mw/bill-center/bill-center.git'
            ],
			
			//  fixedassets
		    [
                    app: "fixed-assets",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'fixedAssets-base/target/fixedAssets-base.jar',
                    git: 'http://gitlab.top.mw/fixedAssets/fixedAssets-base.git'
            ],
			
			//  finance
            [
                    app: "finance-costing",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'finance-costing-service/target/finance-costing-service.jar',
                    git: 'http://gitlab.top.mw/finance/finance-costing.git'
            ],
		    [
                    app: "finance-processproject",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'finance-processproject/target/finance-processproject.jar',
                    git: 'http://gitlab.top.mw/fixedAssets/processProject.git'
            ],
		    [
                    app: "finance-settleaccounts",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'finance-settleaccounts-service/target/finance-settleaccounts-service.jar',
                    git: 'http://gitlab.top.mw/finance/finance-settleaccounts.git'
            ],
            [
                    app: "mw-finance-web",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-finance-web/target/mw-finance-web.jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-finance.git'
            ],
			//  hr
		    [
                    app: "hr-base",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'hr-base-service-provider/target/hr-base-service-provider.jar',
                    git: 'http://gitlab.top.mw/hr/hr-base.git'
            ],
		    [
                    app: "hr-assess",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'hr-assess-service-provider/target/hr-assess-service-provider.jar',
                    git: 'http://gitlab.top.mw/hr/hr-assess.git'
            ],
		    [
                    app: "hr-arrangework",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'hr-arrangework-service-provider/target/hr-arrangework-service-provider.jar',
                    git: 'http://gitlab.top.mw/hr/hr-arrangework.git'
            ],
		    [
                    app: "hr-business-events",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'hr-business-events-service-provider/target/hr-business-events-service-provider.jar',
                    git: 'http://gitlab.top.mw/hr/business-events.git'
            ],
            [
                    app: "mw-hrm-api",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-hrm-api.git'
            ],
            [
                    app: "mw-hrm",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-hrm-web/target/mw-hrm-web.jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-hrm.git'
            ],
			//  invoicing
		    [
                    app: "invoicing-base",
                    lang: 'jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/invoicing-base.git'
            ],
		    [
                    app: "invoicing-merchandise",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'invoicing-web-merchandise/target/invoicing-web-merchandise.jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/invoicing-merchandise-provider.git'
            ],
		    [
                    app: "invoicing-order",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'invoicing-web-order/target/invoicing-web-order.jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/invoicing-order-provider.git'
            ],
		    [
                    app: "invoicing-policy",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'invoicing-web-policy/target/invoicing-web-policy.jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/invoicing-policy-provider.git'
            ],
		    [
                    app: "invoicing-simple",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'invoicing-web-simple/target/invoicing-web-simple.jar',
                    git: 'http://gitlab.top.mw/jinxiao/java/invoicing-simple-provider.git'
            ],
			
			//  person-plat
		    [
                    app: "person-plat-base",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'person-plat-base-service/target/person-plat-base-service.jar',
                    git: 'http://gitlab.top.mw/person-plat/person-plat-base.git'
            ],
			
			//  storage
		    [
                    app: "storage-input",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'storage-stockInput-service-provider/target/storage-input-service.jar',
                    git: 'http://gitlab.top.mw/storage/storage-stockInput.git'
            ],
		    [
                    app: "barcode",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'barcode-service-provider/target/barcode-service.jar',
                    git: 'http://gitlab.top.mw/storage/barcode.git'
            ],
		    [
                    app: "entrust-material",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'entrust-material-service-provider/target/entrust-material-service.jar',
                    git: 'http://gitlab.top.mw/storage/entrust-material.git'
            ],
		    [
                    app: "mw-storage-web",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-storage-web/target/mw-storage-web.jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-storage.git'
            ],
		    [
                    app: "mw-store",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'store-service-provider/target/store-service-provider.jar',
                    git: 'http://gitlab.top.mw/mw-surmax/mw-store.git'
            ],
		    [
                    app: "storage-out",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'storage-out-service-provider/target/storage-out-service-provider.jar',
                    git: 'http://gitlab.top.mw/storage/storage-stockOut.git'
            ],
		    [
                    app: "storage-stock",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'storage-stock-service-provider/target/storage-stock-service.jar',
                    git: 'http://gitlab.top.mw/storage/storage-stock.git'
            ],
		    [
                    app: "storage-transport",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'storage-transport-service-provider/target/storage-transport-service-provider.jar',
                    git: 'http://gitlab.top.mw/storage/storage-transport.git'
            ],
			
			//  international			
		    [
                    app: "international",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'international-business-service-provider/target/international-business-service-provider.jar',
                    git: 'http://gitlab.top.mw/international-business/international.git'
            ],
			
			//  mw-logistics
		    [
                    app: "logistics-admin-web",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'logistics-admin-web/target/logistics-admin-web-1.0.0-SNAPSHOT.jar',
                    git: 'http://gitlab.top.mw/mw-logistics/logistics-admin.git'
            ],
		    [
                    app: "logistics-web",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'logistics-web/target/logistics-web-1.0.0-SNAPSHOT.jar',
                    git: 'http://gitlab.top.mw/mw-logistics/logistics-service.git'
            ],
			
			//  mw-microshop
		    [
                    app: "mw-microshop-bm",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-microshop-bm/target/mw-microshop-bm.jar',
                    git: 'http://gitlab.top.mw/mw-microshop/mw-microshop.git'
            ],
		    [
                    app: "mw-microshop-chat",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-microshop-chat/target/mw-microshop-chat.jar',
                    git: 'http://gitlab.top.mw/mw-microshop/mw-microshop.git'
            ],
			[
                    app: "safety-manager",
                    namespace: "dev",
                    lang: 'java',
                    artifact: 'safety-manager-service-provider/target/safety-manager-service.jar',
                    git: 'http://gitlab.top.mw/safety-manager/safety-manager-base.git'
            ],
            [
                    app: "inspection-management",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'inspection-management-service-provider/target/inspection-management-service.jar',
                    git: 'http://gitlab.top.mw/inspection/inspection-management.git'
            ],
            [
                    app: "mw-rds-web",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-rds-adapter/mw-rds-web/target/mw-rds-web.jar',
                    git: 'http://gitlab.top.mw/mw-rds/mw-rds.git'
            ],
            [
                    app: "mw-rds-applets",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-rds-adapter/mw-rds-applets/target/mw-rds-applets.jar',
                    git: 'http://gitlab.top.mw/mw-rds/mw-rds.git'
            ],
            // mes
            [
                    app: "mw-mes",
                    lang: 'java',
                    namespace: 'dev',
                    artifact: 'mw-mes-web/target/mw-mes-web.jar',
                    git: 'http://gitlab.top.mw/mes/mw-mes.git'
            ],
            //  front
            [
                    app: "surmax-admin-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/surmax-admin-vue.git'
            ],
            [
                    app: "surmax-manager-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/surmax-manager-vue.git'
            ],
            [
                    app: "components",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    compile: false,
                    git: 'http://gitlab.top.mw/front/components.git'
            ],
            [
                    app: "logistics-admin-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/logistics-admin-vue.git'
            ],
            [
                    app: "logistics-h5-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/logistics-h5-vue.git'
            ],
            [
                    app: "mw-login-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/mw-login-vue.git'
            ],
            [
                    app: "pos-manager-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/pos-manager-vue.git'
            ],
            [
                    app: "supply-manager-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/supply-manager-vue.git'
            ],
            [
                    app: "microshop-admin-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/microshop-admin-vue.git'
            ],
            [
                    app: "meiwu-website-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/meiwu-website-vue.git'
            ],
            [
                    app: "meiwu-admin-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/meiwu-admin-vue.git'
            ],
            [
                    app: "surmax-manager-h5",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'build',
                    git: 'http://gitlab.top.mw/front/surmax-manager-h5.git'
            ],
            [
                    app: "microshop-h5-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/microshop-h5-vue.git'
            ],
            [
                    app: "rds-manager-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/rds-manager-vue.git'
            ],
            [
                    app: "rds-h5-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/rds-h5-vue.git'
            ],
            [
                    app: "mes-manager-vue",
                    namespace: "dev",
                    lang: 'nodejs',
                    artifact: 'dist',
                    git: 'http://gitlab.top.mw/front/mes-manager-vue.git'
            ],
            [
                    app: "sync-harbor",
                    lang: 'sync-harbor'
            ],
            [
                    app: "release",
                    lang: 'release'
            ]
    ]

    def getMeta(String app) {
        def result = meta.find { value ->

            if (value.app.equals(app)) {
                return value
            }
            return null
        }
        return result
    }

    def getNamespace(String app) {
        def meta = this.getMeta(app)
        if (meta == null) {
            return null
        }
        return meta.get('namespace')
    }

    def getJavaOpts(Map map, String env) {
        def optsMap = map.get('javaOpts')
        def opts = optsMap.get(env)
        map.put('javaOpts', opts)
    }

    def getEnv(String env) {
        if (env.equals("dev")) {
            return "dev"
        }
        return "test"
//        return "release"
    }

    def getStr(String json) {
        def jsonSlpuer = new groovy.json.JsonSlurperClassic()
        def rs = jsonSlpuer.parseText(json)
        return rs
    }

}
