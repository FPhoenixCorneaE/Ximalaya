package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.coretrofit.RetrofitFactory
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.defaultMMKV
import com.fphoenixcorneae.util.FileProviderUtil
import com.fphoenixcorneae.util.FileUtil
import com.fphoenixcorneae.ximalaya.common.constant.Constant
import com.fphoenixcorneae.ximalaya.common.ext.indent
import com.fphoenixcorneae.ximalaya.common.ext.launch
import com.fphoenixcorneae.ximalaya.common.service.CommonService
import com.fphoenixcorneae.ximalaya.main.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

/**
 * @desc：SplashViewModel
 * @date：2021/07/13 14:17
 */
class SplashViewModel : BaseViewModel() {

    private val mCommonService by lazy {
        RetrofitFactory.getApi(CommonService::class.java, Constant.Host.BING)
    }
    private val mSplashService by lazy {
        RetrofitFactory.getApi(SplashService::class.java, Constant.Host.BING)
    }

    /** 剩余跳过时间 */
    private var mResidualSkipTime = SKIP_COUNTDOWN_TIME

    /**
     * 广告图片
     */
    private val _imgAd = MutableStateFlow<Any?>(null)
    val imgAd = _imgAd.asStateFlow()

    /**
     * 广告版权所有
     */
    private val _copyright = MutableStateFlow<CharSequence?>(null)
    val copyright = _copyright.asStateFlow()

    /** 倒计时文本 */
    private val _countdownText = MutableStateFlow(getCountdownText())
    val countdownText = _countdownText.asStateFlow()

    /** 跳过广告倒计时文本 */
    private val _skipAdText = MutableStateFlow(getResidualSkipAdTimeText())
    val skipAdText = _skipAdText.asStateFlow()

    /** 跳过广告倒计时结束 */
    private val _skipCountdownFinished = MutableStateFlow(false)
    val skipCountdownFinished = _skipCountdownFinished.asStateFlow()

    /** 是否跳过广告 */
    private val _isSkipAd = MutableStateFlow(false)
    val isSkipAd = _isSkipAd.asStateFlow()

    /**
     * 获取启动广告
     */
    fun getSplashAd(type: String, status: String) {
        launch({
            mSplashService.getSplashAd(type, status)
        }, {
            val localAdUrl = defaultMMKV.decodeString(Constant.SP.AD_URL)
            val localAdImgFile = File(Constant.Default.AD_IMG_FILE_PATH)
            val adUrl = Constant.Host.BING + it.images?.get(0)?.url
            // 如果本地广告 url 与网络返回的广告 url 一致则不下载
            if (localAdUrl == adUrl && FileUtil.isFileExists(localAdImgFile)) {
                val adImgUri = FileProviderUtil.getUriForFile(localAdImgFile)
                _imgAd.value = adImgUri
                val copyright = defaultMMKV.decodeString(Constant.SP.AD_COPYRIGHT)
                _copyright.value = copyright.indent()
            } else {
                val copyright = it.images?.get(0)?.copyright
                val copyrightLink = it.images?.get(0)?.copyrightLink
                _imgAd.value = adUrl
                _copyright.value = copyright.indent()
                downloadAdImg(adUrl, copyright, copyrightLink)
            }
        })
    }

    /**
     * 开始跳过倒计时
     */
    fun startSkipCountdown() {
        viewModelScope.launch {
            repeat(SKIP_COUNTDOWN_TIME + 1) {
                delay(1_000)
                mResidualSkipTime--
                _countdownText.value = getCountdownText()
                _skipAdText.value = getResidualSkipAdTimeText()
            }
            _skipCountdownFinished.value = true
        }
    }

    /**
     * 跳过广告点击
     */
    fun onClickSkipAd() {
        _isSkipAd.value = true
    }

    /**
     * 获取倒计时文字
     */
    private fun getCountdownText() = String.format(
        Locale.getDefault(),
        appContext.getString(R.string.main_countdown),
        mResidualSkipTime.toString().padStart(2, '0')
    )

    /**
     * 获取剩余跳过广告时间文字
     */
    private fun getResidualSkipAdTimeText() = String.format(
        Locale.getDefault(),
        appContext.getString(R.string.main_skip_ad),
        mResidualSkipTime.toString().padStart(2, '0')
    )

    /**
     * 下载广告图片
     */
    private fun downloadAdImg(imgUrl: String, copyright: String?, copyrightLink: String?) {
        launch({
            mCommonService.getDataByUrl(imgUrl)
        }, {
            val writeSuccess = FileUtil.writeFileFromIS(Constant.Default.AD_IMG_FILE_PATH, it.byteStream(), false)
            if (writeSuccess) {
                defaultMMKV.encode(Constant.SP.AD_URL, imgUrl)
                defaultMMKV.encode(Constant.SP.AD_COPYRIGHT, copyright)
                defaultMMKV.encode(Constant.SP.AD_COPYRIGHT_LINK, copyrightLink)
            }
        })
    }

    companion object {
        const val SKIP_COUNTDOWN_TIME = 5
        const val SKIP_COUNTDOWN_TIME_MILLIS = SKIP_COUNTDOWN_TIME * 1_000L
    }
}