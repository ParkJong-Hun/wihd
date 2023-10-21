import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.android")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.dagger")
            }
        }
    }
}