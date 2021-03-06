package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.ext.logd
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.permission.request
import com.fphoenixcorneae.util.AppUtil
import com.fphoenixcorneae.util.IntentUtil
import com.fphoenixcorneae.ximalaya.common.router.main.MainRouterHelper
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

    override fun initView() {
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

    override fun initData(savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            delay(SPLASH_STAY_TIME)
            // 申请权限
            requestPermissions()
        }
    }

    /**
     * 申请权限
     */
    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        request(*permissions) {
            onGranted {
                "onGranted".logd("requestPermissions")
                SplashAdDialog()
                    .setOnDismissListener {
                        MainRouterHelper.navigation()
                        finish()
                    }
                    .show(mContext)
            }
            onDenied {
                "onDenied".logd("requestPermissions")
                // 退出 App
                AppUtil.exitApp()
            }
            onShowRationale {
                "onShowRationale".logd("requestPermissions")
                it.retry()
            }
            onNeverAskAgain {
                "onNeverAskAgain".logd("requestPermissions")
                IntentUtil.openApplicationDetailsSettings()
            }
        }
    }

    companion object {
        const val SPLASH_STAY_TIME = 1_500L
    }
}