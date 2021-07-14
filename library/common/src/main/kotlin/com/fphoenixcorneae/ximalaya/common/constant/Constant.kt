package com.fphoenixcorneae.ximalaya.common.constant

import com.fphoenixcorneae.ext.appContext

object Constant {

    /**
     * 主机
     */
    object Host {
        // 必应
        const val BING = "https://cn.bing.com"

        // 喜马拉雅
        const val XIMALAYA = "https://api.ximalaya.com"
    }

    /**
     * 偏好设置
     */
    object SP {
        // 启动广告
        const val AD_URL = "ad_url"
        const val AD_COPYRIGHT = "ad_copyright"
        const val AD_COPYRIGHT_LINK = "ad_copyright_link"
    }

    object Default {
        private const val AD_NAME = "/ad.jpg"
        val AD_IMG_FILE_PATH by lazy { appContext.filesDir.absolutePath + AD_NAME }
    }
}