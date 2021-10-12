package com.fphoenixcorneae.ximalaya.discover.mvvm

import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Route
import com.fphoenixcorneae.ximalaya.discover.databinding.DiscoverFragmentDiscoverBinding

/**
 * @desc：DiscoverFragment
 * @date：2021/08/03 10:04
 */
@Router(path = Route.Discover.MAIN)
class DiscoverFragment : BaseFragment<DiscoverFragmentDiscoverBinding>() {

    override fun initViewBinding(): DiscoverFragmentDiscoverBinding {
        return DiscoverFragmentDiscoverBinding.inflate(layoutInflater)
    }
}