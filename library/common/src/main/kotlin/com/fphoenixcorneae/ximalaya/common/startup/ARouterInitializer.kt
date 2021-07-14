package com.fphoenixcorneae.ximalaya.common.startup

import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ext.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ARouterInitializer : Initializer<Unit>, CoroutineScope by MainScope() {
    override fun create(context: Context) {
        launch(Dispatchers.IO) {
            // ARouter 初始化
            "ARouter 初始化".logd("startup")
            ARouter.init(appContext)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return mutableListOf()
    }
}