plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.detekt")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.jetpack.datastore.preferences)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}