package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 *  Global state of this app.
 */
@Composable
fun rememberAppState(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController = rememberNavController(),
): AppState {
    return remember(
        appNavController,
        windowSizeClass,
    ) {
        AppState(
            windowSizeClass = windowSizeClass,
            appNavController = appNavController,
        )
    }
}

@Stable
class AppState(
    val windowSizeClass: WindowSizeClass,
    val appNavController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = appNavController
            .currentBackStackEntryAsState().value?.destination

    fun navigateToHoge(
    ) {
        // TODO
    }
}