plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.demo.cityguide"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.demo.cityguide"
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
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // ---------------- CORE ----------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // ---------------- LIFECYCLE ----------------
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.compose)

    // ---------------- ACTIVITY ----------------
    implementation(libs.androidx.activity.compose)

    // ---------------- COMPOSE BOM ----------------
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.compose.ui.tooling)

    // ---------------- NAVIGATION ----------------
    implementation(libs.navigation.compose)

    // ---------------- HILT ----------------
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp("com.google.dagger:hilt-compiler:2.51")
    ksp(libs.hilt.compiler)

    // ---------------- NETWORK ----------------
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // ---------------- SERIALIZATION ----------------
    implementation(libs.serialization.json)

    // ---------------- DATASTORE ----------------
    implementation(libs.datastore)

    // ---------------- FIREBASE (BOM) ----------------
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)

    // ---------------- IMAGES ----------------
    implementation(libs.coil)

    // ---------------- LOGGING ----------------
    implementation(libs.timber)

    // ---------------- TESTS ----------------
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
}