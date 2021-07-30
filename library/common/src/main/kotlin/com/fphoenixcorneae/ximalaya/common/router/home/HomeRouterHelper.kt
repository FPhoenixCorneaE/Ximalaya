package com.fphoenixcorneae.ximalaya.common.router.home

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.aRouter

/**
 * @desc：HomeRouterHelper
 * @date：2021/07/30 16:53
 */
object HomeRouterHelper {

    @Autowired(name = Router.Home.SERVICE)
    lateinit var mHomeService: HomeRouterService

    init {
        aRouter.inject(this)
    }

    fun navigation(): Fragment {
        return mHomeService.navigation()
    }
}