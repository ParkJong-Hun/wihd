import dsl.implementation
import dsl.library
import dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class FeatureBundlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.detekt")
            }

            dependencies {
                implementation(libs.library("koin-compose"))
            }
        }
    }
}
