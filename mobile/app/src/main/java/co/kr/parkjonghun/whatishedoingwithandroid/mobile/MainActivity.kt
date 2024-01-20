package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.content.Intent
import android.os.Bundle
import android.os.Process
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.util.CrashReportingTree
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import org.koin.android.ext.android.get
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        plant(if (IS_DEBUG) DebugTree() else CrashReportingTree())

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        get<SupabaseDao>().handleAfterAuth(intent)

        setContent {
            App(
                windowSizeClass = calculateWindowSizeClass(this),
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // Unreachable.
        Process.killProcess(Process.myPid())
    }

    companion object {
        // TODO check build variant
        private const val IS_DEBUG = true
    }
}
