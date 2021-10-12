package com.fphoenixcorneae.ximalaya.common.router.home

import androidx.fragment.app.Fragment
import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ximalaya.common.constant.Route
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @desc：HomeRouterHelper
 * @date：2021/07/30 16:53
 */
object HomeRouterHelper {

    suspend fun navigation(): Fragment = kotlin.run {
        suspendCoroutine { coroutine ->
            DRouter.build(Route.Home.MAIN)
                .start(appContext) {
                    coroutine.resume(it.fragment)
                }
        }
    }
}