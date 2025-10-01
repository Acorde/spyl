import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.moe.spyl"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.moe.spyl"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        missingDimensionStrategy("environment", "dev")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            buildConfigField("String", "BASE_URL", "\"https://\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://\"")
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"

            )
        }
    }


    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17)) // JDK used by Java tasks
        }
    }
    kotlin {
        // Ensures Gradle uses JDK 17 to compile Kotlin
        jvmToolchain(17)

        compilerOptions {
            // ⬅️ replaces kotlinOptions { jvmTarget = "11" }
            jvmTarget.set(JvmTarget.JVM_17)

            // (optional) keep your other flags here:
            // languageVersion.set(KotlinVersion.KOTLIN_2_0) // or 2_1, 2_2 as you need
            // apiVersion.set(KotlinVersion.KOTLIN_2_0)
            // freeCompilerArgs.addAll("-Xjvm-default=all", "-opt-in=kotlin.RequiresOptIn")
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    constraints {
        implementation(libs.javapoet)   // influences resolution
        add("ksp", libs.javapoet)    // if using KSP

    }
    // --- AndroidX core/runtime ---
    implementation(libs.androidx.core.ktx)                 // Kotlin extensions for core Android APIs
    implementation(libs.androidx.lifecycle.runtime.ktx)    // Lifecycle + coroutines
    implementation(libs.androidx.activity.compose)         // setContent(), back handling, etc.

    // --- Compose platform (BOM pins all compose artifacts) ---
    implementation(platform(libs.androidx.compose.bom))

    // --- Compose UI / Material ---
    implementation(libs.androidx.ui)                       // Compose UI core
    implementation(libs.androidx.ui.graphics)              // Graphics utilities
    implementation(libs.androidx.material3)                // Material 3 components
    implementation(libs.androidx.material3.window.size)    // WindowSizeClass (adaptive layouts)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.ui.tooling.preview)       // @Preview runtime deps

    // --- Navigation / Windowing (for tablets, foldables, desktops) ---
    implementation(libs.androidx.navigation.compose)       // Navigation in Compose
    implementation(libs.androidx.window)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.foundation.layout)                   // Jetpack WindowManager (folds/metrics)

    // --- Unit & Instrumented tests ---
    testImplementation(libs.junit)                         // JVM unit tests
    androidTestImplementation(libs.androidx.junit)         // AndroidX JUnit runner
    androidTestImplementation(libs.androidx.espresso.core) // UI tests (Views/interop)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose testing

    // --- Debug-only tooling (exclude from release) ---
    debugImplementation(libs.androidx.ui.tooling)          // Live previews/inspector
    debugImplementation(libs.androidx.ui.test.manifest)    // Compose test manifest stub

    //Retrofit
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.converter.scalars)

    //Okhttp
    implementation(platform(libs.okhttp.bom))    // BOM
    implementation(libs.okHttp3)                 // artifacts
    implementation(libs.logging.interceptor)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

}