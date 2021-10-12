package com.fphoenixcorneae.ximalaya.home.mvvm

import android.view.View
import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.ext.dpToPx
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.toolbar.CommonToolbar
import com.fphoenixcorneae.util.ResourceUtil
import com.fphoenixcorneae.ximalaya.common.constant.Route
import com.fphoenixcorneae.ximalaya.home.R
import com.fphoenixcorneae.ximalaya.home.databinding.HomeFragmentHomeBinding

/**
 * @desc：HomeFragment
 * @date：2021/07/30 17:02
 */
@Router(path = Route.Home.MAIN)
class HomeFragment : BaseFragment<HomeFragmentHomeBinding>() {

    override fun initViewBinding(): HomeFragmentHomeBinding {
        return HomeFragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        mToolbar = CommonToolbar(mContext).apply {
            leftType = CommonToolbar.TYPE_LEFT_IMAGE_BUTTON
            leftImageRes = R.drawable.common_ic_message
            rightCustomViewRes = R.layout.home_custom_toolbar_right_layout
            rightType = CommonToolbar.TYPE_RIGHT_CUSTOM_VIEW
            centerType = CommonToolbar.TYPE_CENTER_SEARCH_VIEW
            centerSearchBgColor = ResourceUtil.getColor(R.color.color_0xdddddd)
            centerSearchBgCornerRadius = 20f.dpToPx()
            centerSearchEditable = false
        }
        return mToolbar
    }
}