import dsl.androidApplication
import dsl.androidTestImplementation
import dsl.library
import dsl.libs
import dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.dropbox.dependency-guard")
            }

            androidApplication {
                setupAndroid()
            }

            dependencies {
                androidTestImplementation(libs.library("junit"))
                androidTestImplementation(libs.library("mockk-android"))
            }
        }
    }
}
