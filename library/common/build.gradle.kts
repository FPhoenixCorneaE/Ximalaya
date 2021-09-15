import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.composingBuilds)
}

android {
    compileSdk = Deps.Android.compileSdkVersion
}

dependencies {
    compileOnly(Deps.Kotlin.stdlib)
    // project
    api(project(mapOf("path" to ":library:thirdpart")))
    api(project(mapOf("path" to ":library:ximalaya")))
    // androidX
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
    // coroutinesRetrofit
    api(Deps.FPhoenixCorneaE.coRetrofit)
    // jetpackMvvm
    api(Deps.FPhoenixCorneaE.jetpackMvvm)
    // commonUtil
    api(Deps.FPhoenixCorneaE.commonUtil)
    // smartProgressBar
    api(Deps.FPhoenixCorneaE.smartProgressBar)
    // easyNavigation
    api(Deps.FPhoenixCorneaE.easyNavigation)
}