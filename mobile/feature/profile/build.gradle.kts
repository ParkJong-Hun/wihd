plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.feature")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.feature.profile"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(libs.jetpack.hilt.navigation)
    implementation(libs.jetpack.compose.material3.windowsizeclass)
    implementation(libs.timber)
}
