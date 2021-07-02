import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.composingBuilds)
    id(Plugin.androidAspectj)
}

android {
    compileSdkVersion(Deps.Android.compileSdkVersion)

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.FPhoenixCorneaE.AndroidAspectj.aspectj)
    kapt(Deps.FPhoenixCorneaE.AndroidAspectj.aspectjCompiler)
    implementation(Deps.FPhoenixCorneaE.jetpackMvvm)
}