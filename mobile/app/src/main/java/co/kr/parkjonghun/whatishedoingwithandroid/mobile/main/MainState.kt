package co.kr.parkjonghun.whatishedoingwithandroid.mobile.main

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination

/**
 *  Global state of this app.
 */
@Composable
fun rememberMainState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): MainState {
    return remember(
        navController,
        windowSizeClass,
    ) {
        MainState(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}

@Stable
class MainState(
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

    fun navigateToMain(
        item: MainDestination,
    ) {
        // TODO navigate to rail item's screen
    }

    fun routeToDestination(route: Route): MainDestination {
        return when (route) {
            // TODO const val on other module
            "news" -> MainDestination.NEWS
            "post" -> MainDestination.POST
            "profile" -> MainDestination.PROFILE

            // impossible
            else -> MainDestination.NEWS
        }
    }
}

typealias Route = String?