import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidApplication)
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
    implementation(project(mapOf("path" to ":function:main")))
}