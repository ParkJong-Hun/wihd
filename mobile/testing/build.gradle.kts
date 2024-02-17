plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.kotlin)
    alias(libs.plugins.wihd.test)
    alias(libs.plugins.wihd.detekt)
    alias(libs.plugins.wihd.compose)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.mobile.testing"

dependencies {
    implementation(project(":core:ui:system"))
    implementation(libs.junit)
    implementation(libs.jetpack.junit)
    implementation(libs.jetpack.compose.ui.test.junit4)
    implementation(libs.roborazzi)
    implementation(libs.roborazzi.compose)
    implementation(libs.roborazzi.junit.rule)
    implementation(libs.robolectric)
    implementation(libs.accompanist.testharness)
}
