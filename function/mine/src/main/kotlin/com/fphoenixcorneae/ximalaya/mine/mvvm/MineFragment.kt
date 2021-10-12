package com.fphoenixcorneae.ximalaya.mine.mvvm

import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Route
import com.fphoenixcorneae.ximalaya.mine.databinding.MineFragmentMineBinding

/**
 * @desc：MineFragment
 * @date：2021/08/03 10:06
 */
@Router(path = Route.Mine.MAIN)
class MineFragment : BaseFragment<MineFragmentMineBinding>() {

    override fun initViewBinding(): MineFragmentMineBinding {
        return MineFragmentMineBinding.inflate(layoutInflater)
    }
}