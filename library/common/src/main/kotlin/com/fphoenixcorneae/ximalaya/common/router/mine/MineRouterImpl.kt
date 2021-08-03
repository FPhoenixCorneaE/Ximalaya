package com.fphoenixcorneae.ximalaya.common.router.mine

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：MineRouterImpl
 * @date：2021/08/03 10:19
 */
@Route(path = Router.Service.MINE)
class MineRouterImpl : MineRouterService {

    override fun navigation(): Fragment {
        return defaultARouter.build(Router.Mine.MAIN).navigation() as Fragment
    }

    override fun init(context: Context?) {
    }
}