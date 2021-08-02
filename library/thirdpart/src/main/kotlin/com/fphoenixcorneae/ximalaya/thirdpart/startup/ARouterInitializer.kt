package com.fphoenixcorneae.ximalaya.thirdpart.startup

import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ext.logd
import com.fphoenixcorneae.ximalaya.thirdpart.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @desc：阿里路由初始化器
 * @date：2021/07/14 15:02
 */
class ARouterInitializer : Initializer<Unit>, CoroutineScope by MainScope() {
    override fun create(context: Context) {
        launch(Dispatchers.IO) {
            // ARouter 初始化
            "ARouter 初始化".logd("startup")
            if (BuildConfig.DEBUG) {
                // 下面两行必须写在 init 之前，否则这些配置在 init 中将无效
                ARouter.openLog()
                // 开启调试模式（如果在 InstantRun 模式下运行，必须开启调试模式！
                // 线上版本需要关闭，否则有安全风险）
                ARouter.openDebug()
            }
            ARouter.init(appContext)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return mutableListOf()
    }
}