package com.fphoenixcorneae.ximalaya.common.router.mine

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @desc：MineRouterService
 * @date：2021/08/03 10:19
 */
interface MineRouterService : IProvider {
    fun navigation(): Fragment
}