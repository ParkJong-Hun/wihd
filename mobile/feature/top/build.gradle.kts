plugins {
    alias(libs.plugins.wihd.bundle.feature)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.feature.top"

dependencies {
    implementation(project(":core:domain:service"))
    implementation(project(":core:ui:system"))
    implementation(project(":core:ui:component"))
    implementation(project(":feature:news"))
    implementation(project(":feature:post"))
    implementation(project(":feature:profile"))
    implementation(libs.jetpack.navigation.compose)
    implementation(libs.jetpack.compose.material3.windowsizeclass)
    implementation(libs.timber)
}
