import Configs.NAMESPACE

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "presentation".NAMESPACE
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        minSdk = Configs.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appCompat)
    implementation(libs.google.material)

    testImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.androidxJunit)

    implementation (libs.kotlinx.coroutines.android)

    implementation (libs.androidx.lifecycle.runtime)

    implementation(libs.facebook.shimmer)

    implementation (libs.okhttp3.okhttp)
}