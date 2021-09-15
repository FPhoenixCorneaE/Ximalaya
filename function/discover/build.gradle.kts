import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidLibrary)
    id(Plugin.composingBuilds)
}

android {
    compileSdk = Deps.Android.compileSdkVersion
}

dependencies {
    implementation(project(mapOf("path" to ":library:common")))
}