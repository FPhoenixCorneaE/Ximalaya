package com.fphoenixcorneae.ximalaya.common.router.listen

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @desc：ListenRouterService
 * @date：2021/08/03 10:14
 */
interface ListenRouterService : IProvider {
    fun navigation(): Fragment
}