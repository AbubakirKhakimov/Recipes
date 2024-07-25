import Configs.NAMESPACE

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "security".NAMESPACE
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

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            buildConfigField ("String", "RECIPES_API_BASE_URL", "\"https://www.themealdb.com/api/json/v1/1/\"")
        }
        release {
            buildConfigField ("String", "RECIPES_API_BASE_URL", "\"https://www.themealdb.com/api/json/v1/1/\"")
        }
    }
}

dependencies {

    testImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.androidxJunit)
}