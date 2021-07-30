package com.fphoenixcorneae.ximalaya.home.mvvm

import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.home.databinding.HomeFragmentHomeBinding

/**
 * @desc：HomeFragment
 * @date：2021/07/30 17:02
 */
@Route(path = Router.Home.MAIN)
class HomeFragment : BaseFragment<HomeFragmentHomeBinding>() {

    override fun initViewBinding(): HomeFragmentHomeBinding {
        return HomeFragmentHomeBinding.inflate(layoutInflater)
    }
}