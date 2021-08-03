package com.fphoenixcorneae.plugin

object Deps {

    object FPhoenixCorneaE {
        object AndroidAspectj {
            private const val version = "1.0.5"
            const val aspectj = "com.github.FPhoenixCorneaE.AndroidAspectj:aspectj:${version}"
            const val aspectjCompiler = "com.github.FPhoenixCorneaE.AndroidAspectj:aspectj-compiler:${version}"
        }

        const val coRetrofit = "com.github.FPhoenixCorneaE:CoRetrofit:1.0.1"
        const val jetpackMvvm = "com.github.FPhoenixCorneaE:JetpackMvvm:1.1.8"
        const val commonUtil = "com.github.FPhoenixCorneaE:CommonUtil:1.1.2"
        const val smartProgressBar = "com.github.FPhoenixCorneaE:SmartProgressBar:1.0.3"
        const val easyNavigation = "com.github.FPhoenixCorneaE.EasyNavigation:easyNavigation:1.0.8"
    }

    /** Plugin */
    object Plugin {
        const val kotlinAndroid = "kotlin-android"
        const val kotlinKapt = "kotlin-kapt"
        const val aRouter = "com.alibaba.arouter"
    }

    /** Android */
    object Android {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.3"
        const val applicationId = "com.fphoenixcorneae.ximalaya"
        const val minSdkVersion = 21
        const val targetSdkVersion = 30
        const val versionCode = 100
        const val versionName = "1.0.0"
    }

    /** BuildType */
    object BuildType {
        const val Debug = "debug"
        const val Release = "release"
    }

    /** Kotlin */
    object Kotlin {
        private const val version = "1.5.20"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    /** AndroidX */
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val material = "com.google.android.material:material:1.3.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.2"

        // activity
        const val activity = "androidx.activity:activity:1.2.3"
        const val activityKtx = "androidx.activity:activity-ktx:1.2.3"

        // fragment
        const val fragment = "androidx.fragment:fragment:1.3.3"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.3"

        // palette
        const val paletteKtx = "androidx.palette:palette-ktx:1.0.0"

        // recyclerView
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"

        // startup
        const val startup = "androidx.startup:startup-runtime:1.0.0"

        // viewpager2
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    }

    /** Coroutines */
    object Coroutines {
        private const val version = "1.5.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${version}"
    }

    /** ARouter */
    object ARouter {
        private const val version = "1.5.2"
        const val api = "com.alibaba:arouter-api:$version"
        const val compiler = "com.alibaba:arouter-compiler:$version"
    }

    /** ThirdPart */
    object ThirdPart {
        const val noDrawable = "com.github.whataa:noDrawable:1.1.1"
    }

    /** Test */
    object Test {
        const val androidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:4.13.2"
        const val junitExt = "androidx.test.ext:junit:1.1.2"
        const val runner = "androidx.test:runner:1.2.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
    }
}