plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.ui"

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}