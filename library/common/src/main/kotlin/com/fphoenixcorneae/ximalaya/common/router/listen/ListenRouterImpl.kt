package com.fphoenixcorneae.ximalaya.common.router.listen

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：ListenRouterImpl
 * @date：2021/08/03 10:14
 */
@Route(path = Router.Service.LISTEN)
class ListenRouterImpl : ListenRouterService {

    override fun navigation(): Fragment {
        return defaultARouter.build(Router.Listen.MAIN).navigation() as Fragment
    }

    override fun init(context: Context?) {
    }
}