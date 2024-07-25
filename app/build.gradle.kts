import Configs.NAMESPACE

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.androidx.navigation.safeArgs.kotlin)
}

android {
    namespace = Configs.APPLICATION_ID
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        applicationId = Configs.APPLICATION_ID
        minSdk = Configs.MIN_SDK
        targetSdk = Configs.TARGET_SDK
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

hilt {
    enableAggregatingTask = true
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(Modules.COMMON))
    implementation(project(Modules.COMMON_IMPL))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.PRESENTATION_IMPL))
    implementation(project(Modules.SECURITY))
    implementation(project(Modules.RESOURCE))
    implementation(project(Modules.WIRING))
    implementation(project(Modules.DATA))
    implementation(project(Modules.HOME))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appCompat)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintLayout)

    testImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.androidxJunit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation (libs.google.dagger.hilt.android)
    kapt (libs.google.dagger.hilt.compiler)

    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    implementation (libs.androidx.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.runtime)

    implementation (libs.androidx.fragment)

    implementation (libs.kotlinx.coroutines.android)

    implementation (libs.viewBinding.delegate)

    implementation (libs.hawk)
}