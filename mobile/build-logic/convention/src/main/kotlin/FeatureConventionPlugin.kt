import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("arkjonghun.whatishedoingwithandroid.mobile.convention.android")
                apply("arkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
                apply("arkjonghun.whatishedoingwithandroid.mobile.convention.compose")
                apply("arkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
            }
        }
    }
}