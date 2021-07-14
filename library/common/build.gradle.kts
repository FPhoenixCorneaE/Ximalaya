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
    api(Deps.AndroidX.appcompat)
    api(Deps.AndroidX.constraintLayout)
    api(Deps.AndroidX.material)
    api(Deps.AndroidX.coreKtx)
    api(Deps.AndroidX.activity)
    api(Deps.AndroidX.activityKtx)
    api(Deps.AndroidX.fragment)
    api(Deps.AndroidX.fragmentKtx)
    api(Deps.AndroidX.recyclerView)
    api(Deps.AndroidX.viewpager2)
    api(Deps.ARouter.api)
    kapt(Deps.ARouter.compiler)
    api(Deps.FPhoenixCorneaE.coRetrofit)
    api(Deps.FPhoenixCorneaE.jetpackMvvm)
    api(Deps.FPhoenixCorneaE.commonUtil)
    api(Deps.ThirdPart.noDrawable)
}