package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SplashAdBean(
    val images: List<Image>? = null,
)

@Keep
data class Image(
    val bot: Int? = null,
    val copyright: String? = null,
    @SerializedName("copyrightlink")
    val copyrightLink: String? = null,
    val drk: Int? = null,
    @SerializedName("enddate")
    val endDate: String? = null,
    @SerializedName("fullstartdate")
    val fullStartDate: String? = null,
    val hs: List<Any>? = null,
    val hsh: String? = null,
    val quiz: String? = null,
    @SerializedName("startdate")
    val startDate: String? = null,
    val title: String? = null,
    val top: Int? = null,
    val url: String? = null,
    @SerializedName("urlbase")
    val urlBase: String? = null,
    val wp: Boolean? = null,
)