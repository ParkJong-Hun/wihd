package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.util.CrashReportingTree
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // TODO check build variant
        val isDebug = true
        val tree = if (isDebug) DebugTree() else CrashReportingTree()
        plant(tree)

        setContent {
            App(
                context = applicationContext,
                windowSizeClass = calculateWindowSizeClass(this),
            )
        }
    }
}
