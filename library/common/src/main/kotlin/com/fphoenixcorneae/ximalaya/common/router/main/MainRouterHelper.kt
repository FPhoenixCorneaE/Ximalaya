package com.fphoenixcorneae.ximalaya.common.router.main

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.aRouter

/**
 * @desc：MainRouterHelper
 * @date：2021/07/30 16:53
 */
object MainRouterHelper {

    @Autowired(name = Router.Main.SERVICE)
    lateinit var mMainService: MainRouterService

    init {
        aRouter.inject(this)
    }

    fun navigation() {
        mMainService.navigation()
    }
}