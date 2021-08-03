package com.fphoenixcorneae.ximalaya.common.router.listen

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.thirdpart.ext.defaultARouter

/**
 * @desc：ListenRouterHelper
 * @date：2021/08/03 10:14
 */
object ListenRouterHelper {

    @Autowired(name = Router.Service.LISTEN)
    lateinit var mListenService: ListenRouterService

    init {
        defaultARouter.inject(this)
    }

    fun navigation(): Fragment {
        return mListenService.navigation()
    }
}