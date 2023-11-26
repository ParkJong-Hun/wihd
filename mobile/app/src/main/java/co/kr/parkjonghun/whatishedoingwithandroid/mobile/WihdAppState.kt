package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
fun rememberWihdAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): WihdAppState {
    return remember(
        navController,
        windowSizeClass,
    ) {
        WihdAppState(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}

@Stable
class WihdAppState(
    val windowSizeClass: WindowSizeClass,
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar
}