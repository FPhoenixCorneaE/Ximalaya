// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(Classpath.androidToolsBuildGradle)
        classpath(Classpath.kotlinGradlePlugin)
        classpath(Classpath.aRouterRegister)
        classpath(Classpath.aspectjTools)
        classpath(Classpath.androidAspectj)
        classpath(Classpath.fatAar)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}