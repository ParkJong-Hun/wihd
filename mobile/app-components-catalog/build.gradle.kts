plugins {
    alias(libs.plugins.wihd.android)
    alias(libs.plugins.wihd.kotlin)
    alias(libs.plugins.wihd.compose)
    alias(libs.plugins.wihd.detekt)
}

android {
    namespace = "co.kr.parkjonghun.whatishedoingwithandroid.components"

    defaultConfig {
        applicationId = "co.kr.parkjonghun.whatishedoingwithandroid.components"
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
                "proguard-rules.pro",
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
    implementation(project(":core:ui"))
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}
