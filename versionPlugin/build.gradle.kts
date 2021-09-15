buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
    compileOnly("com.android.tools.build:gradle:7.0.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
}

gradlePlugin {
    plugins {
        create("FPhoenixCorneaEPlugin") {
            // 自定义 plugin 的 id，其他 module 引用要用到
            id = "com.FPhoenixCorneaE.plugin"
            // 实现这个插件的类的路径
            implementationClass = "com.fphoenixcorneae.plugin.VersionPlugin"
        }
    }
}
