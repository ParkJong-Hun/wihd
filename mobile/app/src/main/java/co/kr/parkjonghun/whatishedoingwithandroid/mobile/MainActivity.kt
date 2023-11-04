package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}