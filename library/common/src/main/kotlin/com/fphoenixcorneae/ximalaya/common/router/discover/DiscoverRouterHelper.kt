package com.fphoenixcorneae.ximalaya.common.router.discover

import androidx.fragment.app.Fragment
import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ximalaya.common.constant.Route
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @desc：DiscoverRouterHelper
 * @date：2021/08/03 10:17
 */
object DiscoverRouterHelper {

    suspend fun navigation(): Fragment = kotlin.run {
        suspendCoroutine { coroutine ->
            DRouter.build(Route.Discover.MAIN)
                .start(appContext) {
                    coroutine.resume(it.fragment)
                }
        }
    }
}