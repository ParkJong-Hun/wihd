plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.android")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}