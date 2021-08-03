package com.fphoenixcorneae.ximalaya.common.router.discover

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：DiscoverRouterHelper
 * @date：2021/08/03 10:17
 */
object DiscoverRouterHelper {

    @Autowired(name = Router.Service.DISCOVER)
    lateinit var mDiscoverService: DiscoverRouterService

    init {
        defaultARouter.inject(this)
    }

    fun navigation(): Fragment {
        return mDiscoverService.navigation()
    }
}