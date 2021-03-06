package com.fphoenixcorneae.ximalaya.main.mvvm.main

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.didi.drouter.annotation.Router
import com.fphoenixcorneae.ext.dp2Px
import com.fphoenixcorneae.ext.dpToPx
import com.fphoenixcorneae.ext.toast
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.common.constant.Route
import com.fphoenixcorneae.ximalaya.common.router.discover.DiscoverRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.home.HomeRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.listen.ListenRouterHelper
import com.fphoenixcorneae.ximalaya.common.router.mine.MineRouterHelper
import com.fphoenixcorneae.ximalaya.common.widget.GlobalPlay
import com.fphoenixcorneae.ximalaya.main.R
import com.fphoenixcorneae.ximalaya.main.databinding.MainActivityMainBinding
import kotlinx.coroutines.launch

/**
 * @desc：MainActivity
 * @date：2021/07/27 10:14
 */
@Router(path = Route.Main.MAIN)
class MainActivity : BaseActivity<MainActivityMainBinding>() {

    override fun initViewBinding(): MainActivityMainBinding {
        return MainActivityMainBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initView() {
        mViewBinding.apply {
            lifecycleScope.launch {
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
            }
            // 设置底部导航栏
            val titles = arrayListOf(
                getString(R.string.main_home), getString(R.string.main_listen),
                getString(R.string.main_discover), getString(R.string.main_mine)
            )
            val colorResources = intArrayOf(0, 0, 0, 0)
            val imageResources = intArrayOf(
                R.drawable.main_ic_tab_home, R.drawable.main_ic_tab_listen,
                R.drawable.main_ic_tab_discover, R.drawable.main_ic_tab_mine,
            )
            rlNavigation.disableSmoothScroll()
                .coloredBackground(false)
                .navigationHeight(60.dp2Px())
                .itemColor(
                    ContextCompat.getColor(mContext, R.color.color_0xff7050),
                    ContextCompat.getColor(mContext, R.color.color_0x999999)
                )
                .iconScale(1f, 0.9f)
                .setCenterView(
                    GlobalPlay(mContext)
                        .setBitmap(R.drawable.notification_default)
                        .setBarWidth(2.dpToPx())
                        .setRadius(20.dpToPx())
                        .apply {
                            layoutParams = ViewGroup.LayoutParams(60.dp2Px(), 60.dp2Px())
                        }
                )
                .onCenterViewClickListener {
                    toast("click global play view!")
                }
                .setupWithViewPager2(vpMain, titles, colorResources, imageResources)
        }

        val controller = ViewCompat.getWindowInsetsController(window.decorView)
        controller?.apply {
            // 隐藏状态栏
            hide(WindowInsetsCompat.Type.statusBars())
            // 隐藏虚拟按键
            hide(WindowInsetsCompat.Type.navigationBars())
            //
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}