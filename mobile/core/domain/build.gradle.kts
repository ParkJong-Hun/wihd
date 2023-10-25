plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.domain"

dependencies {
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}