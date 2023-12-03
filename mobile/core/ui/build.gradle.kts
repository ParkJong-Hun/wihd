plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.compose)
    alias(libs.plugins.wihd.detekt)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.ui"

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}
