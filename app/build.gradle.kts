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
    implementation(project(mapOf("path" to ":function:home")))
    implementation(project(mapOf("path" to ":function:listen")))
    implementation(project(mapOf("path" to ":function:discover")))
    implementation(project(mapOf("path" to ":function:mine")))
}