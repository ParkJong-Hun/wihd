plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.ui"

dependencies {
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}