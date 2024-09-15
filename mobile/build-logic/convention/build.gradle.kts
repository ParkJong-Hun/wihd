import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
}

group = "co.kr.parkjonghun.whatishedoingwithandroid.mobile.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinJvmCompile by tasks
compileKotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_17)

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.bundles.plugins)
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
        register("koin") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.test"
            implementationClass = "TestConventionPlugin"
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
        register("parcelize") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.parcelize"
            implementationClass = "KotlinParcelizeConventionPlugin"
        }
        register("kover") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.convention.kover"
            implementationClass = "KoverConventionPlugin"
        }
    }
}
