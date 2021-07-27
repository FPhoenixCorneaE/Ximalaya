package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.fphoenixcorneae.jetpackmvvm.base.dialog.BaseDialog
import com.fphoenixcorneae.ximalaya.main.R
import com.fphoenixcorneae.ximalaya.main.databinding.MainDialogSplashAdBinding

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
            isSkipAd.observe(viewLifecycleOwner) {
                dismiss()
            }
            skipCountdownFinished.observe(viewLifecycleOwner) {
                dismiss()
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.apply {
            getSplashAd("js", "1")
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