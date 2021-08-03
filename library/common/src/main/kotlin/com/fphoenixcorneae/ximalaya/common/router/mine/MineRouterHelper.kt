package com.fphoenixcorneae.ximalaya.common.router.mine

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：MineRouterHelper
 * @date：2021/08/03 10:18
 */
object MineRouterHelper {

    @Autowired(name = Router.Service.MINE)
    lateinit var mMineService: MineRouterService

    init {
        defaultARouter.inject(this)
    }

    fun navigation(): Fragment {
        return mMineService.navigation()
    }
}