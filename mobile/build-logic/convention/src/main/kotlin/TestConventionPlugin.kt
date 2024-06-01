import dsl.library
import dsl.libs
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.github.takahirom.roborazzi")
            }

            dependencies {
                testImplementation(libs.library("koin-test"))
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("junit-vintage-engine")) // to run Roboletric on the JUnitPlatform
                testImplementation(libs.library("jetpack-junit"))
                testImplementation(libs.library("jetpack-espresso-core"))
                testImplementation(libs.library("jetpack-compose-ui-test-junit4"))
                testImplementation(libs.library("mockk"))
                testImplementation(libs.library("turbine"))
                testImplementation(libs.library("roborazzi"))
                testImplementation(libs.library("roborazzi-compose"))
                testImplementation(libs.library("roborazzi-junit-rule"))
                testImplementation(libs.library("robolectric"))
            }
        }
    }
}
