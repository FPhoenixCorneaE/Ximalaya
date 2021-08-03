package com.fphoenixcorneae.ximalaya.common.router.discover

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @desc：DiscoverRouterService
 * @date：2021/08/03 10:17
 */
interface DiscoverRouterService : IProvider {
    fun navigation(): Fragment
}