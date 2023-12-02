package co.kr.parkjonghun.whatishedoingwithandroid.mobile.main

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
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainBottomBar
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainDestination
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.navigation.MainNavigationRail
import co.kr.parkjonghun.whatishedoingwithandroid.news.newsScreen
import co.kr.parkjonghun.whatishedoingwithandroid.news.newsScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.post.postScreen
import co.kr.parkjonghun.whatishedoingwithandroid.profile.profileScreen

const val mainScreenRoute = "main"

fun NavGraphBuilder.mainScreen(
    windowSizeClass: WindowSizeClass,
) {
    composable(mainScreenRoute) {
        MainScreen(
            windowSizeClass = windowSizeClass,
        )
    }
}

@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass,
) {
    val mainState = rememberMainState(
        windowSizeClass = windowSizeClass
    )

    val currentMainDestination: MainDestination =
        mainState.navController.currentBackStackEntryAsState().value
            ?.destination
            ?.let { mainState.routeToDestination(route = it.route) }
            ?: MainDestination.NEWS

    MainBody(
        mainNavController = mainState.navController,
        currentMainDestination = currentMainDestination,
        shouldShowNavRail = mainState.shouldShowNavRail,
        shouldShowBottomBar = mainState.shouldShowBottomBar,
        navigateToMain = mainState::navigateToMainDestination,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainBody(
    mainNavController: NavHostController,
    currentMainDestination: MainDestination,
    shouldShowNavRail: Boolean,
    shouldShowBottomBar: Boolean,
    navigateToMain: (MainDestination) -> Unit,
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        AnimatedVisibility(visible = shouldShowNavRail) {
            MainNavigationRail(
                mainRailItems = MainDestination.entries,
                onRailItemSelected = navigateToMain,
                selectedRailItem = currentMainDestination,
            )
        }
        Scaffold(
            bottomBar = {
                AnimatedVisibility(visible = shouldShowBottomBar) {
                    MainBottomBar(
                        mainTabs = MainDestination.entries,
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