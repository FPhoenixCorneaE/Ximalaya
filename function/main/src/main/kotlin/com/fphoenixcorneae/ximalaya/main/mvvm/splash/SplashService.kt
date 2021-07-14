package com.fphoenixcorneae.ximalaya.main.mvvm.splash

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @desc：SplashService
 * @date：2021-07-13 14:00
 */
interface SplashService {

    /**
     * 获取启动广告
     */
    @GET("/HPImageArchive.aspx")
    suspend fun getSplashAd(
        @Query("format") type: String,
        @Query("n") status: String,
    ): SplashAdBean
}