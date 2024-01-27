plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.test)
    alias(libs.plugins.wihd.detekt)
    alias(libs.plugins.wihd.parcelize)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.domain.base"

dependencies {
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
}
