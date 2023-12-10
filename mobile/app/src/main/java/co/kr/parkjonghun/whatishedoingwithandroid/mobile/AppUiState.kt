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
@Stable
data class AppUiState(
    val windowSizeClass: WindowSizeClass,
    val appNavController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = appNavController
            .currentBackStackEntryAsState().value?.destination

    fun navigateToHoge() {
        // TODO
    }
}

@Composable
fun rememberAppUiState(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController = rememberNavController(),
): AppUiState {
    return remember(
        appNavController,
        windowSizeClass,
    ) {
        AppUiState(
            windowSizeClass = windowSizeClass,
            appNavController = appNavController,
        )
    }
}
