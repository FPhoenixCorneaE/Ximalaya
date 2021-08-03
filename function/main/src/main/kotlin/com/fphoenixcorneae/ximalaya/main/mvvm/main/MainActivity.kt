package com.fphoenixcorneae.ximalaya.main.mvvm.main

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.fphoenixcorneae.ext.dp2Px
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.common.constant.Router
import com.fphoenixcorneae.ximalaya.common.router.discover.DiscoverRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.home.HomeRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.listen.ListenRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.mine.MineRouterHelper
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
            val fragments = listOf(
                HomeRouterHelper.navigation(),
                ListenRouterHelper.navigation(),
                DiscoverRouterHelper.navigation(),
                MineRouterHelper.navigation(),
            )
            vpMain.adapter = object : FragmentStateAdapter(mContext) {
                override fun getItemCount(): Int {
                    return fragments.size
                }

                override fun createFragment(position: Int): Fragment {
                    return fragments[position]
                }

            }
            val titles = arrayListOf(
                getString(R.string.main_home), getString(R.string.main_listen),
                getString(R.string.main_discover), getString(R.string.main_mine)
            )
            val colorResources = intArrayOf(0, 0, 0, 0)
            val imageResources = intArrayOf(
                R.mipmap.main_ic_tab_home, R.mipmap.main_ic_tab_listen,
                R.mipmap.main_ic_tab_discover, R.mipmap.main_ic_tab_mine,
            )
            // 设置底部导航栏
            rlNavigation.disableSmoothScroll()
                .coloredBackground(false)
                .navigationHeight(60.dp2Px())
                .itemColor(
                    ContextCompat.getColor(mContext, R.color.color_0xff7050),
                    ContextCompat.getColor(mContext, R.color.color_0x999999)
                )
                .onCenterViewClickListener {

                }
                .setupWithViewPager2(vpMain, titles, colorResources, imageResources)
        }
    }
}