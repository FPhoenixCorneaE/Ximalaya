package com.fphoenixcorneae.ximalaya.thirdpart.startup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.didi.drouter.api.DRouter
import com.fphoenixcorneae.ext.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @desc：滴滴路由初始化器
 * @date：2021/07/14 15:02
 */
class DRouterInitializer : Initializer<Unit>, CoroutineScope by MainScope() {
    override fun create(context: Context) {
        launch(Dispatchers.IO) {
            // DRouter 初始化
            "DRouter 初始化".logd("startup")
            DRouter.init(context as Application)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return mutableListOf()
    }
}