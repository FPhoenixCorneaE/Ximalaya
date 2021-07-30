package com.fphoenixcorneae.ximalaya.common.router.home

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.aRouter

/**
 * @desc：MainRouterImpl
 * @date：2021/07/30 16:55
 */
@Route(path = Router.Home.SERVICE)
class HomeRouterImpl : HomeRouterService {

    override fun navigation(): Fragment {
        return aRouter.build(Router.Home.MAIN).navigation() as Fragment
    }

    override fun init(context: Context?) {
    }
}