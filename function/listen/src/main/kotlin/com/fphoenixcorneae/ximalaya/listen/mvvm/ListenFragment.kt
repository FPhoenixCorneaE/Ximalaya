package com.fphoenixcorneae.ximalaya.listen.mvvm

import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Route
import com.fphoenixcorneae.ximalaya.listen.databinding.ListenFragmentListenBinding

/**
 * @desc：ListenFragment
 * @date：2021/08/03 10:05
 */
@Router(path = Route.Listen.MAIN)
class ListenFragment : BaseFragment<ListenFragmentListenBinding>() {

    override fun initViewBinding(): ListenFragmentListenBinding {
        return ListenFragmentListenBinding.inflate(layoutInflater)
    }
}