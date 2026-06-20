buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jensklingenberg.github.io/Ktorfit/repository/") }
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    //alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.googleKsp) apply false
    alias(libs.plugins.ktorfit) apply false
    alias(libs.plugins.codingFelineBuildConfig) apply false
}


subprojects {
    repositories {
        google()
        mavenCentral()
        // This repository contains the KSP processor artifacts
        maven { url = uri("https://jensklingenberg.github.io/Ktorfit/repository/") }
    }
}