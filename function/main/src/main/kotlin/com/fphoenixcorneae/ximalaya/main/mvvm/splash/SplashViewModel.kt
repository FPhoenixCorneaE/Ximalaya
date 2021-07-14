package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.coretrofit.RetrofitFactory
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.ext.defaultMMKV
import com.fphoenixcorneae.util.FileProviderUtil
import com.fphoenixcorneae.util.FileUtil
import com.fphoenixcorneae.ximalaya.common.constant.Constant
import com.fphoenixcorneae.ximalaya.common.ext.launch
import com.fphoenixcorneae.ximalaya.common.service.CommonService
import com.fphoenixcorneae.ximalaya.main.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

/**
 * @desc：SplashViewModel
 * @date：2021-07-13 14:17
 */
class SplashViewModel : BaseViewModel() {

    private val commonService by lazy {
        RetrofitFactory.getApi(CommonService::class.java, Constant.Host.BING)
    }
    private val splashService by lazy {
        RetrofitFactory.getApi(SplashService::class.java, Constant.Host.BING)
    }

    private val _imgAd = MutableLiveData<Any?>()
    val imgAd: LiveData<Any?> = _imgAd

    private val _copyright = MutableLiveData<String?>()
    val copyright: LiveData<String?> = _copyright

    /** 剩余跳过时间 */
    private var mResidualSkipTime = SKIP_COUNTDOWN_TIME

    /** 跳过广告倒计时文本 */
    private val _skipAdText = MutableLiveData(getResidualSkipTimeText())
    val skipAdText: LiveData<String> = _skipAdText

    /** 跳过广告倒计时结束 */
    private val _skipCountdownFinished = MutableLiveData<Boolean>()
    val skipCountdownFinished: LiveData<Boolean> = _skipCountdownFinished

    /** 是否跳过广告 */
    private val _isSkipAd = MutableLiveData<Boolean>()
    val isSkipAd: LiveData<Boolean> = _isSkipAd

    /**
     * 获取启动广告
     */
    fun getSplashAd(type: String, status: String) {
        launch({
            splashService.getSplashAd(type, status)
        }, {
            val localAdUrl = defaultMMKV.decodeString(Constant.SP.AD_URL)
            val localAdImgFile = File(Constant.Default.AD_IMG_FILE_PATH)
            val adUrl = Constant.Host.BING + it.images?.get(0)?.url
            // 如果本地广告 url 与网络返回的广告 url 一致则不下载
            if (localAdUrl == adUrl && FileUtil.isFileExists(localAdImgFile)) {
                val adImgUri = FileProviderUtil.getUriForFile(localAdImgFile)
                _imgAd.postValue(adImgUri)
                val copyright = defaultMMKV.decodeString(Constant.SP.AD_COPYRIGHT)
                _copyright.postValue(copyright)
            } else {
                val copyright = it.images?.get(0)?.copyright
                val copyrightLink = it.images?.get(0)?.copyrightlink
                _imgAd.postValue(adUrl)
                _copyright.postValue(copyright)
                downloadAdImg(adUrl, copyright, copyrightLink)
            }
        })
    }

    /**
     * 开始跳过倒计时
     */
    fun startSkipCountdown() {
        viewModelScope.launch {
            repeat(SKIP_COUNTDOWN_TIME) {
                delay(1_000)
                mResidualSkipTime--
                _skipAdText.postValue(getResidualSkipTimeText())
            }
            _skipCountdownFinished.postValue(true)
        }
    }

    private fun getResidualSkipTimeText() = String.format(
        Locale.getDefault(),
        appContext.getString(R.string.main_skip_ad),
        mResidualSkipTime.toString().padStart(2, '0')
    )

    /**
     * 跳过广告
     */
    fun onClickSkipAd() {
        _isSkipAd.postValue(true)
    }

    /**
     * 下载广告图片
     */
    private fun downloadAdImg(imgUrl: String, copyright: String?, copyrightLink: String?) {
        launch({
            commonService.getDataByUrl(imgUrl)
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
    }
}