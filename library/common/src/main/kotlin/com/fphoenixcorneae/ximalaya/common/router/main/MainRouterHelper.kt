package com.fphoenixcorneae.ximalaya.common.router.main

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：MainRouterHelper
 * @date：2021/07/30 16:53
 */
object MainRouterHelper {

    @Autowired(name = Router.Service.MAIN)
    lateinit var mMainService: MainRouterService

    init {
        defaultARouter.inject(this)
    }

    fun navigation() {
        mMainService.navigation()
    }
}