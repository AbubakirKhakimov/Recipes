import Configs.NAMESPACE

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "home".NAMESPACE
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
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.COMMON))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.SECURITY))
    implementation(project(Modules.RESOURCE))
    implementation(project(Modules.WIRING))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appCompat)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintLayout)

    testImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.androidxJunit)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation (libs.google.dagger.hilt.android)
    kapt (libs.google.dagger.hilt.compiler)

    implementation (libs.androidx.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.runtime)

    implementation (libs.androidx.fragment)

    implementation (libs.kotlinx.coroutines.android)

    implementation (libs.viewBinding.delegate)

    implementation (libs.bumptech.glide)

    implementation (libs.lottie)

    implementation (libs.facebook.shimmer)
}