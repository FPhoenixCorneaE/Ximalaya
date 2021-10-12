package com.fphoenixcorneae.ximalaya.common.router.mine

import androidx.fragment.app.Fragment
import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ximalaya.common.constant.Route
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @desc：MineRouterHelper
 * @date：2021/08/03 10:18
 */
object MineRouterHelper {

    suspend fun navigation(): Fragment = kotlin.run {
        suspendCoroutine { coroutine ->
            DRouter.build(Route.Mine.MAIN)
                .start(appContext) {
                    coroutine.resume(it.fragment)
                }
        }
    }
}