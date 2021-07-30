package com.fphoenixcorneae.ximalaya.main.mvvm.main

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.common.router.home.HomeRouterHelper
import com.fphoenixcorneae.ximalaya.main.R
import com.fphoenixcorneae.ximalaya.main.databinding.MainActivityMainBinding

/**
 * @desc：MainActivity
 * @date：2021/07/27 10:14
 */
@Route(path = Router.Main.MAIN)
class MainActivity : BaseActivity<MainActivityMainBinding>() {

    override fun initViewBinding(): MainActivityMainBinding {
        return MainActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewBinding.apply {
            val fragments = arrayListOf(
                HomeRouterHelper.navigation(),
                HomeRouterHelper.navigation(),
                HomeRouterHelper.navigation(),
                HomeRouterHelper.navigation(),
            )
            vpMain.adapter = object : FragmentStateAdapter(mContext) {
                override fun getItemCount(): Int {
                    return fragments.size
                }

                override fun createFragment(position: Int): Fragment {
                    return fragments[position]
                }

            }
            val titles = arrayListOf("首页", "我听", "发现", "我的")
            val colorResources = arrayOf(
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
            ).toIntArray()
            val imageResources = arrayOf(
                R.mipmap.main_ic_tab_homepage,
                R.mipmap.main_ic_tab_litsten,
                R.mipmap.main_ic_tab_discover,
                R.mipmap.main_ic_tab_user,
            ).toIntArray()
            rlNavigation.itemColor(
                ContextCompat.getColor(mContext, R.color.color_0x999999),
                ContextCompat.getColor(mContext, R.color.color_0xff7050)
            )
                .onCenterViewClickListener {

                }
                .setupWithViewPager2(vpMain, titles, colorResources, imageResources)
        }
    }
}