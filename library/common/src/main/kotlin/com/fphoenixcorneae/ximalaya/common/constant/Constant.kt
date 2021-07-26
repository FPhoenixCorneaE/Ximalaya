package com.fphoenixcorneae.ximalaya.common.constant

import com.fphoenixcorneae.util.PathUtil

/**
 * @desc：常量
 * @date：2021/07/14 11:08
 */
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

    /**
     * 默认
     */
    object Default {
        private const val AD_NAME = "/ad.jpg"
        val AD_IMG_FILE_PATH by lazy { PathUtil.internalFilesPath + AD_NAME }
    }
}