import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.composingBuilds)
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

android {
    compileSdkVersion(Deps.Android.compileSdkVersion)

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    compileOnly(Deps.Kotlin.stdlib)
    compileOnly(Deps.AndroidX.startup)
    compileOnly(Deps.Coroutines.core)
    compileOnly(Deps.Coroutines.android)
    compileOnly(Deps.FPhoenixCorneaE.commonUtil)
    api(Deps.ARouter.api)
    kapt(Deps.ARouter.compiler)
    api(Deps.ThirdPart.noDrawable)
}