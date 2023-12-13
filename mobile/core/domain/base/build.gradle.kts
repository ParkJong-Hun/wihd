plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.hilt)
    alias(libs.plugins.wihd.detekt)
    alias(libs.plugins.wihd.parcelize)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.domain.base"

dependencies {
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}
