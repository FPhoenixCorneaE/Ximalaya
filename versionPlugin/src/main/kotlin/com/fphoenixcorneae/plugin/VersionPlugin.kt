package com.fphoenixcorneae.plugin

import com.android.build.gradle.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class VersionPlugin : Plugin<Project> {
    companion object {
        const val api = "api"
        const val implementation = "implementation"
        const val testImplementation = "testImplementation"
        const val androidTestImplementation = "androidTestImplementation"
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
                    // 公共 android 配置项
                    project.extensions.getByType<AppExtension>().applyAppCommons(project)
                    // 公共依赖
                    project.configAppDependencies()
                }
                // com.android.library
                is LibraryPlugin -> {
                    // 公共插件
                    project.configCommonPlugin()
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
            apply(Deps.Plugin.aRouter)
        }
    }

    /**
     * app Module 公共依赖
     */
    private fun Project.configAppDependencies() {
        dependencies.apply {
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(implementation, fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
            add(implementation, Deps.Kotlin.stdlib)
            add(implementation, Deps.AndroidX.appcompat)
            configTestDependencies()
        }
    }

    /**
     * library Module 公共依赖
     */
    private fun Project.configLibraryDependencies() {
        dependencies.apply {
            add(api, fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add(api, fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
            add(implementation, Deps.Kotlin.stdlib)
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
    private fun AppExtension.applyAppCommons(project: Project) {
        defaultConfig {
            applicationId = Deps.Android.applicationId
        }
        applyBaseCommons(project)
    }

    /**
     * library Module 配置项
     */
    private fun LibraryExtension.applyLibraryCommons(project: Project) {
        applyBaseCommons(project)
    }

    /**
     * 公共需要添加的设置，如 sdk 目标版本，jdk 版本，jvm 目标版本等
     */
    private fun BaseExtension.applyBaseCommons(project: Project) {
        compileSdkVersion(Deps.Android.compileSdkVersion)
        buildToolsVersion(Deps.Android.buildToolsVersion)

        defaultConfig {
            minSdkVersion(Deps.Android.minSdkVersion)
            targetSdkVersion(Deps.Android.targetSdkVersion)
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
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        dexOptions {
            jumboMode = true
        }

        lintOptions {
            isCheckReleaseBuilds = false
            isAbortOnError = false
        }
    }
}