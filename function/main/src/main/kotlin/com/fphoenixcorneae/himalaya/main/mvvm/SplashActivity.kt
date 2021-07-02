package com.fphoenixcorneae.himalaya.main.mvvm

import com.fphoenixcorneae.himalaya.main.databinding.ActivitySplashBinding
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
}