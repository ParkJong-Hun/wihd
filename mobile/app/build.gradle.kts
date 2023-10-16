@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlinPlugin)
    alias(libs.plugins.kspPlugin)
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.firebase")
}

android {
    namespace = "co.kr.parkjonghun.whatishedoingwithandroid.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "co.kr.parkjonghun.whatishedoingwithandroid.mobile"
        minSdk = 29
        // noinspection EditedTargetSdkVersion
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0.01"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.jetpack.navigation.compose)
}