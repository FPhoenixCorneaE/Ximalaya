package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.main.databinding.MainActivitySplashBinding
import kotlinx.coroutines.delay

/**
 * @desc：启动页
 * @date：2021/7/4 17:09
 */
class SplashActivity : BaseActivity<MainActivitySplashBinding>() {

    override fun initViewBinding(): MainActivitySplashBinding {
        return MainActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            delay(SPLASH_STAY_TIME)
            SplashAdDialog().show(mContext)
        }
    }

    companion object {
        const val SPLASH_STAY_TIME = 1500L
    }
}