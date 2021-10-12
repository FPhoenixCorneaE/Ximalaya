import com.fphoenixcorneae.plugin.Deps

plugins {
    id(Plugin.androidApplication)
    id(Plugin.composingBuilds)
    id(Plugin.dRouter)
}

android {
    compileSdk = Deps.Android.compileSdkVersion
}

dependencies {
    implementation(project(mapOf("path" to ":function:main")))
    implementation(project(mapOf("path" to ":function:home")))
    implementation(project(mapOf("path" to ":function:listen")))
    implementation(project(mapOf("path" to ":function:discover")))
    implementation(project(mapOf("path" to ":function:mine")))
}