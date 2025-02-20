plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.codingexamui"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.codingexamui"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.transition)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)

    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    // JSON serialization library
    implementation(libs.kotlinx.serialization.json)

    // Coil library
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Timber library
    implementation(libs.timber)

    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation (libs.google.gson)
    kapt(libs.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.material)
    implementation(libs.retrofit.gson)
    implementation(libs.hiltNavigationCompose)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")  // Make sure to use the version compatible with your project

    // Other test dependencies
    testImplementation("junit:junit:4.13.2")
    //    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.mockk:mockk:1.13.16")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    testImplementation("com.google.truth:truth:1.1")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.accompanist:accompanist-pager:0.30.1")
    implementation ("androidx.compose.foundation:foundation:1.7.7")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")
}
