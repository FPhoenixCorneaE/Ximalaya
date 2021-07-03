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
}

dependencies {
    api(Deps.AndroidX.constraintLayout)
    api(Deps.FPhoenixCorneaE.jetpackMvvm)
}