import dsl.androidApplication
import dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                setupAndroid()
            }
        }
    }
}