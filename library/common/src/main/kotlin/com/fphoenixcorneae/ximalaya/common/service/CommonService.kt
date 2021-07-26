package com.fphoenixcorneae.ximalaya.common.service

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * @desc：共同事务
 * @date：2021/07/14 14:18
 */
interface CommonService {
    /**
     * 通过 Url 获取数据
     */
    @GET
    suspend fun getDataByUrl(@Url url: String): ResponseBody
}