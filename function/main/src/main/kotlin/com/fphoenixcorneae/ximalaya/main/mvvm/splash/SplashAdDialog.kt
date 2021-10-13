package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.ximalaya.main.R
import com.fphoenixcorneae.ximalaya.main.databinding.MainDialogSplashAdBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @desc：启动广告页弹窗
 * @date：2021/7/4 18:05
 */
class SplashAdDialog : BaseDialog<MainDialogSplashAdBinding>() {

    private val mViewModel by viewModels<SplashViewModel>()

    override fun initViewBinding(): MainDialogSplashAdBinding {
        return MainDialogSplashAdBinding.inflate(layoutInflater)
    }

    override fun initView() {
        mViewBinding.apply {
            viewModel = mViewModel
            spbSkipAd.setProgress(0f, SplashViewModel.SKIP_COUNTDOWN_TIME_MILLIS)
        }
    }

    override fun initObserver() {
        mViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                isSkipAd.collect {
                    if (it && dialog?.isShowing == true) {
                        dismiss()
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                skipCountdownFinished.collect {
                    if (it && dialog?.isShowing == true) {
                        dismiss()
                    }
                }
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.apply {
            // 获取启动广告
            getSplashAd("js", "1")
            // 开始跳过倒计时
            startSkipCountdown()
        }
    }

    override fun getWidth(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getHeight(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun getWindowAnimations(): Int {
        return R.style.SplashAdDialogAnimation
    }
}