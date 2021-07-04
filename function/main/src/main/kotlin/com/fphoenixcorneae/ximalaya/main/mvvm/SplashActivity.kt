package com.fphoenixcorneae.ximalaya.main.mvvm

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.ext.startKtxActivity
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.main.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay

/**
 * @desc：启动页
 * @date：2021/7/4 17:09
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            delay(SPLASH_STAY_TIME)
            startKtxActivity<SplashAdActivity>(flags = null, extra = null, value = null)
        }
    }

    companion object {
        const val SPLASH_STAY_TIME = 1500L
    }
}