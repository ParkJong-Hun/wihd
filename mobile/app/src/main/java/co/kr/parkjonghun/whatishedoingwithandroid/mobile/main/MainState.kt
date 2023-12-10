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
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination.NEWS
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination.POST
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination.PROFILE
import co.kr.parkjonghun.whatishedoingwithandroid.news.navigateToNewsScreen
import co.kr.parkjonghun.whatishedoingwithandroid.post.navigateToPostScreen
import co.kr.parkjonghun.whatishedoingwithandroid.profile.navigateToProfileScreen
import co.kr.parkjonghun.whatishedoingwithandroid.ui.extension.Route

/**
 *  state of the Main screen.
 */
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

    fun navigateToMainDestination(
        item: MainDestination,
    ) {
        with(navController) {
            when (item) {
                NEWS -> navigateToNewsScreen()
                POST -> navigateToPostScreen()
                PROFILE -> navigateToProfileScreen()
            }
        }
    }

    fun routeToDestination(route: Route): MainDestination {
        return when (route) {
            // TODO const val on other module
            "news" -> NEWS
            "post" -> POST
            "profile" -> PROFILE

            // impossible
            else -> NEWS
        }
    }
}

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
