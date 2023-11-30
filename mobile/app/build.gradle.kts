plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.android")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.firebase")
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
    implementation(project(":feature:main"))
    implementation(project(":feature:news"))
    implementation(project(":feature:post"))
    implementation(project(":feature:profile"))


    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))

    implementation(libs.jetpack.core.splashscreen)
    implementation(libs.jetpack.navigation.compose)
    implementation(libs.jetpack.hilt.navigation)
    implementation(libs.jetpack.compose.material3.windowsizeclass)
}