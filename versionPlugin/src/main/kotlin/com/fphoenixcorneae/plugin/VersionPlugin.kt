package com.fphoenixcorneae.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class VersionPlugin : Plugin<Project> {
    companion object {
        const val api = "api"
        const val implementation = "implementation"
        const val kapt = "kapt"
        const val testImplementation = "testImplementation"
        const val androidTestImplementation = "androidTestImplementation"
        const val AROUTER_MODULE_NAME = "AROUTER_MODULE_NAME"
    }

    override fun apply(project: Project) {
        project.plugins.config(project)
    }

    private fun PluginContainer.config(project: Project) {
        whenPluginAdded {
            when (this) {
                // com.android.application
                is AppPlugin -> {
                    // 公共插件
                    project.configCommonPlugin()
                    // 公共 kapt 配置项
                    project.extensions.getByType<KaptExtension>().applyCommonKapt(project)
                    // 公共 android 配置项
                    project.extensions.getByType<BaseAppModuleExtension>().applyAppCommons(project)
                    // 公共依赖
                    project.configAppDependencies()
                }
                // com.android.library
                is LibraryPlugin -> {
                    // 公共插件
                    project.configCommonPlugin()
                    // 公共 kapt 配置项
                    project.extensions.getByType<KaptExtension>().applyCommonKapt(project)
                    // 公共 android 配置项
                    project.extensions.getByType<LibraryExtension>().applyLibraryCommons(project)
                    // 公共依赖
                    project.configLibraryDependencies()
                }
                is KotlinAndroidPluginWrapper -> {
                    // 根据 project build.gradle.kts 中的配置动态设置 kotlinVersion
                    project.getKotlinPluginVersion().also { kotlinVersion ->
                    }
                }
            }
        }
    }

    /**
     * 公共 plugin 插件依赖
     */
    private fun Project.configCommonPlugin() {
        plugins.apply {
            apply(Deps.Plugin.kotlinAndroid)
            apply(Deps.Plugin.kotlinKapt)
            apply(Deps.Plugin.androidAspectj)
            apply(Deps.Plugin.aRouter)
        }
    }

    /**
     * app Module 公共依赖
     */
    private fun Project.configAppDependencies() {
        dependencies.apply {
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
            add(implementation, Deps.Kotlin.stdlib)
            add(implementation, Deps.AndroidX.appcompat)
            // ARouter
            add(implementation, Deps.ARouter.api)
            add(kapt, Deps.ARouter.compiler)
            configTestDependencies()
        }
    }

    /**
     * library Module 公共依赖
     */
    private fun Project.configLibraryDependencies() {
        dependencies.apply {
            add(api, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
            add(implementation, Deps.Kotlin.stdlib)
            // Aspectj
            add(implementation, Deps.FPhoenixCorneaE.AndroidAspectj.aspectj)
            add(kapt, Deps.FPhoenixCorneaE.AndroidAspectj.aspectjCompiler)
            // ARouter
            add(implementation, Deps.ARouter.api)
            add(kapt, Deps.ARouter.compiler)
            configTestDependencies()
        }
    }

    /**
     * test 依赖配置
     */
    private fun DependencyHandler.configTestDependencies() {
        add(testImplementation, Deps.Test.junit)
        add(androidTestImplementation, Deps.Test.junitExt)
        add(androidTestImplementation, Deps.Test.runner)
        add(androidTestImplementation, Deps.Test.espresso)
    }

    /**
     * app Module 配置项，此处固定了 applicationId
     */
    private fun BaseAppModuleExtension.applyAppCommons(project: Project) {
        defaultConfig {
            applicationId = Deps.Android.applicationId
        }
        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
        applyBaseCommons(project)
    }

    /**
     * library Module 配置项
     */
    private fun LibraryExtension.applyLibraryCommons(project: Project) {
        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
        applyBaseCommons(project)
    }

    /**
     * 公共需要添加的设置，如 sdk 目标版本，jdk 版本，jvm 目标版本等
     */
    private fun BaseExtension.applyBaseCommons(project: Project) {
        compileSdkVersion(Deps.Android.compileSdkVersion)
        buildToolsVersion(Deps.Android.buildToolsVersion)

        defaultConfig {
            minSdk = Deps.Android.minSdkVersion
            targetSdk = Deps.Android.targetSdkVersion
            versionCode = Deps.Android.versionCode
            versionName = Deps.Android.versionName
            testInstrumentationRunner = Deps.Test.androidJUnitRunner
        }

        buildTypes {
            getByName(Deps.BuildType.Release) {
                // 执行proguard混淆
                isMinifyEnabled = false
                // 移除无用的resource文件
                isShrinkResources = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            getByName(Deps.BuildType.Debug) {
                // 执行proguard混淆
                isMinifyEnabled = false
                // 移除无用的resource文件
                isShrinkResources = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        sourceSets {
            val main = getByName("main")
            main.java.srcDirs("src/main/kotlin")
            main.jniLibs.srcDirs("src/main/jniLibs")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }

        lintOptions {
            isCheckDependencies = true
            isCheckReleaseBuilds = false
            isAbortOnError = false
        }
    }

    /**
     * kapt 配置项
     */
    private fun KaptExtension.applyCommonKapt(project: Project) {
        arguments {
            arg(AROUTER_MODULE_NAME, project.name)
        }
    }
}