package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.mainScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.mainScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme

/**
 * Top level composable.
 */
@Composable
fun WihdApp(
    windowSizeClass: WindowSizeClass,
) {
    val appState: AppState = rememberAppState(
        windowSizeClass = windowSizeClass,
    )

    MobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            WihdNavHost(appState)
        }
    }
}

@Composable
private fun WihdNavHost(
    appState: AppState,
) {
    NavHost(
        navController = appState.appNavController,
        startDestination = mainScreenRoute,
    ) {
        mainScreen(
            windowSizeClass = appState.windowSizeClass,
        )
    }
}
