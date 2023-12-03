import dsl.detektPlugins
import dsl.library
import dsl.libs
import dsl.setupDetekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }

            setupDetekt(extensions.getByType<DetektExtension>())

            dependencies {
                detektPlugins(libs.library("detekt-formatting"))
                detektPlugins(libs.library("twitter-compose-rules"))
            }
        }
    }
}
