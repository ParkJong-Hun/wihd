import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class FeatureBundlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.library")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.kotlin")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.compose")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.detekt")
                apply("parkjonghun.whatishedoingwithandroid.mobile.convention.test")
            }
        }
    }
}
