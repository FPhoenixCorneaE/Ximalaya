package com.fphoenixcorneae.ximalaya.listen.mvvm

import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.listen.databinding.ListenFragmentListenBinding

/**
 * @desc：ListenFragment
 * @date：2021/08/03 10:05
 */
@Route(path = Router.Listen.MAIN)
class ListenFragment : BaseFragment<ListenFragmentListenBinding>() {

    override fun initViewBinding(): ListenFragmentListenBinding {
        return ListenFragmentListenBinding.inflate(layoutInflater)
    }
}