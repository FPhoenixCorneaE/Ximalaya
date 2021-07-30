package com.fphoenixcorneae.ximalaya.common.router.main

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.aRouter

/**
 * @desc：MainRouterImpl
 * @date：2021/07/30 16:55
 */
@Route(path = Router.Main.SERVICE)
class MainRouterImpl : MainRouterService {

    override fun navigation() {
        aRouter.build(Router.Main.MAIN).navigation()
    }

    override fun init(context: Context?) {
    }
}