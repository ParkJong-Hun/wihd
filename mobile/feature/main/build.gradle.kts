plugins {
    id("parkjonghun.whatishedoingwithandroid.mobile.convention.feature")
}

android.namespace = "co.kr.parkjonghun.whatishedoingwithandroid.feature.main"

dependencies {
    implementation(project(":core:domain"))
}