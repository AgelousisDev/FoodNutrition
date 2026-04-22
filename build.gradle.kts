buildscript {
    repositories {
        google()        // <--- Add this
        mavenCentral()
        maven { url = uri("https://jensklingenberg.github.io/Ktorfit/repository/") }
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    //alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.googleKsp) version "2.1.0-1.0.29" apply false
    alias(libs.plugins.ktorfit) version "2.2.0" apply false
}


subprojects {
    repositories {
        google()
        mavenCentral()
        // This repository contains the KSP processor artifacts
        maven { url = uri("https://jensklingenberg.github.io/Ktorfit/repository/") }
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            // Forces the compiler plugin to use the specific Kotlin 2.1.0 build
            if (requested.group == "de.jensklingenberg.ktorfit" && requested.name == "compiler-plugin") {
                useVersion("2.1.0-2.1.0")
            }
        }
    }
}