package co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.kr.parkjonghun.whatishedoingwithandroid.news.navigateToNewsScreen
import co.kr.parkjonghun.whatishedoingwithandroid.post.navigateToPostScreen
import co.kr.parkjonghun.whatishedoingwithandroid.profile.navigateToProfileScreen
import co.kr.parkjonghun.whatishedoingwithandroid.ui.extension.Route

/**
 *  state of the Main screen.
 */
@Stable
class TopNavigationState(
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
        item: TopDestination,
    ) {
        with(navController) {
            when (item) {
                TopDestination.NEWS -> navigateToNewsScreen()
                TopDestination.POST -> navigateToPostScreen()
                TopDestination.PROFILE -> navigateToProfileScreen()
            }
        }
    }

    fun routeToDestination(route: Route): TopDestination {
        return when (route) {
            // TODO const val on other module
            "news" -> TopDestination.NEWS
            "post" -> TopDestination.POST
            "profile" -> TopDestination.PROFILE

            // impossible
            else -> TopDestination.NEWS
        }
    }
}

@Composable
fun rememberTopState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): TopNavigationState {
    return remember(
        navController,
        windowSizeClass,
    ) {
        TopNavigationState(
            windowSizeClass = windowSizeClass,
            navController = navController,
        )
    }
}
