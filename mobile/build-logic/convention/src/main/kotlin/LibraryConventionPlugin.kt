import dsl.androidLibrary
import dsl.library
import dsl.libs
import dsl.setupAndroid
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            androidLibrary {
                setupAndroid()
            }

            dependencies {
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("jetpack-junit"))
                testImplementation(libs.library("jetpack-espresso-core"))
                testImplementation(libs.library("mockk"))
            }
        }
    }
}
