@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlinPlugin)
    alias(libs.plugins.gmsPlugin)
    alias(libs.plugins.daggerHiltPlugin)
    alias(libs.plugins.kspPlugin)
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.jetpack.core.ktx)
    implementation(libs.jetpack.lifecycle.runtime.ktx)
    implementation(libs.jetpack.activity.compose)
    implementation(platform(libs.jetpack.compose.bom))
    implementation(libs.jetpack.compose.ui)
    implementation(libs.jetpack.compose.ui.graphics)
    implementation(libs.jetpack.compose.ui.tooling.preview)
    implementation(libs.jetpack.compose.material3)
    implementation(platform(libs.firebase.bom))
    implementation(libs.jetpack.navigation.compose)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
    androidTestImplementation(platform(libs.jetpack.compose.bom))
    androidTestImplementation(libs.jetpack.compose.ui.test.junit4)
    debugImplementation(libs.jetpack.compose.ui.tooling)
    debugImplementation(libs.jetpack.compose.ui.test.manifest)
}