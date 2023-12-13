plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.koin)
    alias(libs.plugins.wihd.detekt)
    alias(libs.plugins.wihd.parcelize)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.domain.service"

dependencies {
    implementation(project(":core:domain:base"))
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}
