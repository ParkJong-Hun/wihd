import dsl.android
import dsl.debugImplementation
import dsl.implementation
import dsl.library
import dsl.libs
import dsl.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            dependencies {
                implementation(platform(libs.library("jetpack-compose-bom")))
                implementation(libs.library("jetpack-activity-compose"))
                implementation(libs.library("jetpack-core-ktx"))
                implementation(libs.library("jetpack-compose-ui"))
                implementation(libs.library("jetpack-compose-ui-graphics"))
                implementation(libs.library("jetpack-compose-material3"))
                implementation(libs.library("jetpack-compose-ui-tooling-preview"))
                implementation(libs.library("jetpack-lifecycle-runtime-ktx"))
                implementation(libs.library("coil-compose"))
                implementation(libs.library("koin-compose"))
                debugImplementation(libs.library("jetpack-compose-ui-tooling"))
                debugImplementation(libs.library("jetpack-compose-ui-test-manifest"))
            }
        }
    }
}
