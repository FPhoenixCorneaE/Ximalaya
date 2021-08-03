package com.fphoenixcorneae.ximalaya.discover.mvvm

import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.discover.databinding.DiscoverFragmentDiscoverBinding

/**
 * @desc：DiscoverFragment
 * @date：2021/08/03 10:04
 */
@Route(path = Router.Discover.MAIN)
class DiscoverFragment : BaseFragment<DiscoverFragmentDiscoverBinding>() {

    override fun initViewBinding(): DiscoverFragmentDiscoverBinding {
        return DiscoverFragmentDiscoverBinding.inflate(layoutInflater)
    }
}