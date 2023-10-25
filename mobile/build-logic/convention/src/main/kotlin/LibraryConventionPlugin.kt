import com.google.devtools.ksp.gradle.KspTaskMetadata
import dsl.android
import dsl.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            android {
                setupAndroid()
                sourceSets {
                    getByName("main") {
                        assets.srcDirs("src/androidMain/assets")
                        java.srcDirs("src/androidMain/kotlin", "src/commonMain/kotlin")
                        res.srcDirs("src/androidMain/res")
                    }
                    getByName("test") {
                        assets.srcDirs("src/androidUnitTest/assets")
                        java.srcDirs("src/androidUnitTest/kotlin", "src/commonTest/kotlin")
                        res.srcDirs("src/androidUnitTest/res")
                    }
                }
            }
            tasks.withType<KspTaskMetadata>().configureEach {
                notCompatibleWithConfigurationCache("Configuration cache not supported due to serialization")
            }
        }
    }
}