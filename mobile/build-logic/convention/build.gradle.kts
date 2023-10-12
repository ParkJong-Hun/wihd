import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "co.kr.parkjonghun.whatishedoingwithandroid.mobile.buildlogic"

repositories {
    google()
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.plugins.androidGradlePlugin)
    compileOnly(libs.plugins.androidGradleLibraryPlugin)
    compileOnly(libs.plugins.kotlinPlugin)
    compileOnly(libs.plugins.gmsPlugin)
    compileOnly(libs.plugins.daggerHiltPlugin)
    compileOnly(libs.plugins.kspPlugin)
}

gradlePlugin {
    plugins {
        register("core") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("compose") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("dagger") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.dagger"
            implementationClass = "DaggerConventionPlugin"
        }
        register("firebase") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.firebase"
            implementationClass = "FirebaseConventionPlugin"
        }
        register("test") {
            id = "parkjonghun.whatishedoingwithandroid.mobile.firebase"
            implementationClass = "TestConventionPlugin"
        }
    }
}
