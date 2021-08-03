package com.fphoenixcorneae.ximalaya.mine.mvvm

import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.mine.databinding.MineFragmentMineBinding

/**
 * @desc：MineFragment
 * @date：2021/08/03 10:06
 */
@Route(path = Router.Mine.MAIN)
class MineFragment : BaseFragment<MineFragmentMineBinding>() {

    override fun initViewBinding(): MineFragmentMineBinding {
        return MineFragmentMineBinding.inflate(layoutInflater)
    }
}