package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
    MobileTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            WihdNavHost(windowSizeClass)
        }
    }
}

@Composable
private fun WihdNavHost(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = appNavController,
        startDestination = mainScreenRoute,
    ) {
        mainScreen(
            windowSizeClass = windowSizeClass,
        )
    }
}