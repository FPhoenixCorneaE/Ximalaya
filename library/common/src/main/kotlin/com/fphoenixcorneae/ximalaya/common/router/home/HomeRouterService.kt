package com.fphoenixcorneae.ximalaya.common.router.home

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @desc：HomeRouterService
 * @date：2021/07/30 17:08
 */
interface HomeRouterService : IProvider {
    fun navigation(): Fragment
}