package co.kr.parkjonghun.whatishedoingwithandroid.feature.top

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation.TopBottomBar
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation.TopDestination
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation.TopNavigationRail
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.navigation.rememberTopState
import co.kr.parkjonghun.whatishedoingwithandroid.news.newsScreen
import co.kr.parkjonghun.whatishedoingwithandroid.news.newsScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.post.postScreen
import co.kr.parkjonghun.whatishedoingwithandroid.profile.profileScreen
import kotlinx.collections.immutable.toPersistentList

const val topScreenRoute = "top"

fun NavGraphBuilder.topScreen(
    windowSizeClass: WindowSizeClass,
) {
    composable(topScreenRoute) {
        TopScreen(
            windowSizeClass = windowSizeClass,
        )
    }
}

@Composable
fun TopScreen(
    windowSizeClass: WindowSizeClass,
) {
    val mainState = rememberTopState(
        windowSizeClass = windowSizeClass,
    )

    val currentMainDestination: TopDestination =
        mainState.navController.currentBackStackEntryAsState().value?.destination?.let {
            mainState.routeToDestination(route = it.route)
        } ?: TopDestination.NEWS

    TopBody(
        mainNavController = mainState.navController,
        currentMainDestination = currentMainDestination,
        shouldShowNavRail = mainState.shouldShowNavRail,
        shouldShowBottomBar = mainState.shouldShowBottomBar,
        navigateToMain = mainState::navigateToMainDestination,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun TopBody(
    mainNavController: NavHostController,
    currentMainDestination: TopDestination,
    shouldShowNavRail: Boolean,
    shouldShowBottomBar: Boolean,
    navigateToMain: (TopDestination) -> Unit,
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
    ) {
        AnimatedVisibility(visible = shouldShowNavRail) {
            TopNavigationRail(
                mainRailItems = TopDestination.entries.toPersistentList(),
                onRailItemSelected = navigateToMain,
                selectedRailItem = currentMainDestination,
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(visible = shouldShowBottomBar) {
                    TopBottomBar(
                        mainTabs = TopDestination.entries.toPersistentList(),
                        onRailItemSelected = navigateToMain,
                        selectedRailItem = currentMainDestination,
                    )
                }
            },
        ) { padding ->
            NavHost(
                navController = mainNavController,
                startDestination = newsScreenRoute,
                modifier = Modifier,
            ) {
                newsScreen(
                    modifier = Modifier,
                    contentPadding = padding,
                )
                postScreen(
                    modifier = Modifier,
                    contentPadding = padding,
                )
                profileScreen(
                    modifier = Modifier,
                    contentPadding = padding,
                )
            }
        }
    }
}
