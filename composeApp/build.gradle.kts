import com.android.build.api.dsl.ApplicationExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    //alias(libs.plugins.composeHotReload)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.codingFelineBuildConfig)
}

fun getGoogleAiStudioKey(): Pair<String, String> {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return (properties.getProperty("GOOGLE_AI_STUDIO_API_KEY") ?: "") to (properties.getProperty("GOOGLE_AI_STUDIO_PROJECT_NUMBER") ?: "")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm()
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.google.ai.generative)
            implementation(libs.core.splash.screen)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.jetbrains.compose.material.icons.extended)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.lifecycle.viewmodel.navigation3)
            implementation(libs.jetbrains.adaptive)
            implementation(libs.jetbrains.material3.window.size)

            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktorfit.lib)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.configure {
            // This is the CRITICAL line for common code visibility
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
        androidMain.configure {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
    }
    sourceSets.commonMain.configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
    sourceSets.androidMain.configure {
        kotlin.srcDir("build/generated/ksp/android/androidDebug/kotlin")
    }
    sourceSets.jvmMain.configure {
        kotlin.srcDir("build/generated/ksp/jvm/jvmMain/kotlin")
    }
}

buildkonfig {
    packageName = "com.yourdomain.foodnutrition"
    defaultConfigs {
        val (googleAiStudioKey, googleAiProjectNumber) = getGoogleAiStudioKey()
        buildConfigField(
            type = STRING,
            name = "GOOGLE_AI_STUDIO_API_KEY",
            value = googleAiStudioKey
        )
        buildConfigField(
            type = STRING,
            name = "GOOGLE_AI_STUDIO_PROJECT_NUMBER",
            value = googleAiProjectNumber
        )
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
    add("kspIosSimulatorArm64", libs.ktorfit.ksp)
    add("kspJvm", libs.ktorfit.ksp)
}

extensions.configure<ApplicationExtension>("android") {
    namespace = "com.agelousis.foodnutrition"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.agelousis.foodnutrition"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.agelousis.foodnutrition.MainKt"

        val jvmRuntimeArgs = arrayOf(
            "--add-opens=java.base/java.lang=ALL-UNNAMED",
            "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
            "--add-opens=java.desktop/sun.font=ALL-UNNAMED",
            "--add-opens=java.desktop/sun.java2d=ALL-UNNAMED",
            "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
            "--add-opens=java.base/java.util=ALL-UNNAMED",
            "--add-opens=java.desktop/java.awt.event=ALL-UNNAMED"
        )

        jvmArgs(*jvmRuntimeArgs)

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Food Nutrition"
            packageVersion = "1.0.0"
            description = "Search & display food nutrition"
            vendor = "Agelousis"

            jvmArgs(*jvmRuntimeArgs)

            linux {
                shortcut = true
                menuGroup = "Health"
                appCategory = "Education"
                iconFile.set(project.file("app_icon.png"))
            }
        }
    }
}

ksp {
    arg("ktorfit.errors", "1")
}

ktorfit {
    compilerPluginVersion.set("2.3.5")
}

tasks.matching { it.name == "kspKotlinJvm" }.configureEach {
    dependsOn(tasks.matching { it.name == "kspCommonMainKotlinMetadata" })
}

tasks.matching { it.name == "kspKotlinAndroid" }.configureEach {
    dependsOn(tasks.matching { it.name == "kspCommonMainKotlinMetadata" })
}