import dsl.android
import dsl.implementation
import dsl.ksp
import dsl.library
import dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            android {
                packagingOptions {
                    resources {
                        excludes += "META-INF/gradle/incremental.annotation.processors"
                    }
                }
            }
            dependencies {
                implementation(libs.library("dagger-hilt"))
                // https://issuetracker.google.com/issues/237567009
                implementation(libs.library("jetpack-fragment"))
                ksp(libs.library("dagger-hilt-compiler"))
            }
        }
    }
}
