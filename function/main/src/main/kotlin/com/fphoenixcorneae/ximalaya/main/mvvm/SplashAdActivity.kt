package com.fphoenixcorneae.ximalaya.main.mvvm

import android.os.Bundle
import android.view.View
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.ximalaya.main.databinding.ActivitySplashAdBinding

/**
 * @desc：启动广告页
 * @date：2021/7/4 18:05
 */
class SplashAdActivity : BaseActivity<ActivitySplashAdBinding>() {

    override fun initViewBinding(): ActivitySplashAdBinding {
        return ActivitySplashAdBinding.inflate(layoutInflater)
    }

    override fun initToolbar(): View? {
        return null
    }

    override fun initView() {

    }

    override fun initData(savedInstanceState: Bundle?) {
    }
}