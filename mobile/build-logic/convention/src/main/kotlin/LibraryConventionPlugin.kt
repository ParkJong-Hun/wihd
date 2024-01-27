import dsl.androidLibrary
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.setupAndroid
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
                implementation(libs.library("koin-compose"))
            }
        }
    }
}
