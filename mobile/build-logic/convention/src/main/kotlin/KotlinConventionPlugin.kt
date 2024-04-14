import dsl.android
import dsl.api
import dsl.implementation
import dsl.kotlinOptions
import dsl.library
import dsl.libs
import dsl.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
                kotlinOptions.jvmTarget = "17"
            }

            android {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
                }
            }
            dependencies {
                implementation(libs.library("kotlinx-coroutines-core"))
                implementation(libs.library("kotlinx-datetime"))
                api(libs.library("kotlinx-collections-immutable"))
                testImplementation(libs.library("kotlinx-coroutines-test"))
                testImplementation(libs.library("kotlin-test"))
            }
        }
    }
}
