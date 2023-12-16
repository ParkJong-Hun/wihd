plugins {
    alias(libs.plugins.wihd.bundle.feature)
    alias(libs.plugins.wihd.koin)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.feature.news"

dependencies {
    implementation(project(":core:domain:service"))
    implementation(project(":core:ui"))
    implementation(libs.jetpack.navigation.compose)
    implementation(libs.jetpack.compose.material3.windowsizeclass)
    implementation(libs.timber)
}
