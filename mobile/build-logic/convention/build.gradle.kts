import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "co.kr.parkjonghun.whatishedoingwithandroid.mobile.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
    // https://github.com/google/dagger/issues/3068#issuecomment-1470534930
    implementation(libs.javapoet)
}

gradlePlugin {
    plugins {
        register("android") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.android"
            implementationClass = "AndroidConventionPlugin"
        }
        register("compose") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("dagger") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.dagger"
            implementationClass = "DaggerConventionPlugin"
        }
        register("feature") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.feature"
            implementationClass = "FeatureBundlePlugin"
        }
        register("firebase") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.firebase"
            implementationClass = "FirebaseConventionPlugin"
        }
        register("kotlin") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin"
            implementationClass = "KotlinConventionPlugin"
        }
        register("library") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("detekt") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("serialization") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.serialization"
            implementationClass = "KotlinSerializationConventionPlugin"
        }
    }
}
