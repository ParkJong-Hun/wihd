@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.android")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.firebase")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
}

android {
    namespace = "co.kr.parkjonghun.whatishedoingwithandroid.mobile"

    defaultConfig {
        applicationId = "co.kr.parkjonghun.whatishedoingwithandroid.mobile"
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.jetpack.navigation.compose)
}