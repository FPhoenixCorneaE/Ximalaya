package com.fphoenixcorneae.ximalaya.common.router.main

import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.ximalaya.common.constant.Route

/**
 * @desc：MainRouterHelper
 * @date：2021/07/30 16:53
 */
object MainRouterHelper {

    fun navigation() = kotlin.run {
        DRouter.build(Route.Main.MAIN).start()
    }
}