import dsl.api
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("unused")
class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }
            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile::class.java) {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

//            android {
//                kotlinOptions {
//                    jvmTarget = JavaVersion.VERSION_17.toString()
//
//                    val metricsPath = "${project.buildDir.absolutePath}/compose_compiler_metrics"
//                    if (project.findProperty("composeCompilerMetrics") == "true") {
//                        freeCompilerArgs =
//                            listOf(
//                                *freeCompilerArgs.toTypedArray(),
//                                "-P",
//                                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$metricsPath",
//                            )
//                    }
//                    if (project.findProperty("composeCompilerReports") == "true") {
//                        freeCompilerArgs =
//                            listOf(
//                                *freeCompilerArgs.toTypedArray(),
//                                "-P",
//                                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$metricsPath",
//                            )
//                    }
//                }
//            }
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
