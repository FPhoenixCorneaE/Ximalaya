package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import androidx.annotation.Keep

@Keep
data class SplashAdBean(
    val images: List<Image>? = null
)

@Keep
data class Image(
    val bot: Int? = null,
    val copyright: String? = null,
    val copyrightlink: String? = null,
    val drk: Int? = null,
    val enddate: String? = null,
    val fullstartdate: String? = null,
    val hs: List<Any>? = null,
    val hsh: String? = null,
    val quiz: String? = null,
    val startdate: String? = null,
    val title: String? = null,
    val top: Int? = null,
    val url: String? = null,
    val urlbase: String? = null,
    val wp: Boolean? = null
)