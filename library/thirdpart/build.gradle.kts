import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.composingBuilds)
}

android {
    compileSdkVersion(Deps.Android.compileSdkVersion)

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    configurations.all {
        resolutionStrategy {
            // 强制依赖指定版本
            force(Deps.Coroutines.core)
            force(Deps.Coroutines.android)
        }
    }
}

dependencies {
    compileOnly(Deps.Kotlin.stdlib)
    compileOnly(Deps.AndroidX.startup)
    compileOnly(Deps.Coroutines.core)
    compileOnly(Deps.Coroutines.android)
    compileOnly(Deps.FPhoenixCorneaE.commonUtil)
    api(Deps.ThirdPart.noDrawable)
}