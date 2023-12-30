plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.compose)
    alias(libs.plugins.wihd.detekt)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.ui"

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.supabase.auth)
    implementation(libs.supabase.auth.ui)
}
