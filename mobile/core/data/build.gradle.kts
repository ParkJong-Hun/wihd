plugins {
    alias(libs.plugins.wihd.library)
    alias(libs.plugins.wihd.dagger)
    alias(libs.plugins.wihd.detekt)
    alias(libs.plugins.wihd.serialization)
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.jetpack.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.jetpack.datastore.preferences)
    implementation(libs.ktor)
    implementation(libs.supabase)
    testImplementation(libs.junit)
    androidTestImplementation(libs.jetpack.junit)
    androidTestImplementation(libs.jetpack.espresso.core)
}
