package com.fphoenixcorneae.ximalaya.common.router.home

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：HomeRouterHelper
 * @date：2021/07/30 16:53
 */
object HomeRouterHelper {

    @Autowired(name = Router.Service.HOME)
    lateinit var mHomeService: HomeRouterService

    init {
        defaultARouter.inject(this)
    }

    fun navigation(): Fragment {
        return mHomeService.navigation()
    }
}