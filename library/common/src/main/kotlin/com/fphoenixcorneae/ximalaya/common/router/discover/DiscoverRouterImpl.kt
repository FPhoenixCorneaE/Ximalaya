package com.fphoenixcorneae.ximalaya.common.router.discover

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：DiscoverRouterImpl
 * @date：2021/08/03 10:17
 */
@Route(path = Router.Service.DISCOVER)
class DiscoverRouterImpl : DiscoverRouterService {

    override fun navigation(): Fragment {
        return defaultARouter.build(Router.Discover.MAIN).navigation() as Fragment
    }

    override fun init(context: Context?) {
    }
}