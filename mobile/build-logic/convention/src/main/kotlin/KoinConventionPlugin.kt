import dsl.implementation
import dsl.library
import dsl.libs
import dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.library("koin-compose"))
                testImplementation(libs.library("koin-test"))
            }
        }
    }
}
