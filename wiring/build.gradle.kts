import Configs.NAMESPACE

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "wiring".NAMESPACE
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

    implementation(project(Modules.COMMON))
    implementation(project(Modules.COMMON_IMPL))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.PRESENTATION_IMPL))
    implementation(project(Modules.SECURITY))

    testImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.androidxJunit)

    implementation (libs.google.dagger.hilt.android)
    kapt (libs.google.dagger.hilt.compiler)
}