plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.moe.spyl"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.moe.spyl"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


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

    // --- Navigation / Windowing (for tablets, foldables, desktops) ---
    implementation(libs.androidx.navigation.compose)       // Navigation in Compose
    implementation(libs.androidx.window)                   // Jetpack WindowManager (folds/metrics)

    // --- Unit & Instrumented tests ---
    testImplementation(libs.junit)                         // JVM unit tests
    androidTestImplementation(libs.androidx.junit)         // AndroidX JUnit runner
    androidTestImplementation(libs.androidx.espresso.core) // UI tests (Views/interop)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4) // Compose testing

    // --- Debug-only tooling (exclude from release) ---
    debugImplementation(libs.androidx.ui.tooling)          // Live previews/inspector
    debugImplementation(libs.androidx.ui.test.manifest)    // Compose test manifest stub
}