package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import co.kr.parkjonghun.whatishedoingwithandroid.component.molecule.custom.LoadingMask
import co.kr.parkjonghun.whatishedoingwithandroid.feature.login.LoginScreen
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.topScreen
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.topScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.navigation.AppNavigationState
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.navigation.rememberAppNavigationState
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import co.kr.parkjonghun.whatishedoingwithandroid.system.theme.MobileTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Top level composable.
 */
@OptIn(KoinExperimentalAPI::class)
@Suppress("ModifierMissing")
@Composable
fun App(
    windowSizeClass: WindowSizeClass,
    newTokenDto: TokenDto?,
) {
    KoinAndroidContext {
        val appNavigationState: AppNavigationState = rememberAppNavigationState(
            windowSizeClass = windowSizeClass,
        )
        val (appState, appIntent) = rememberAppProcessor()

        // Launch init action.
        LaunchedEffect(true) {
            newTokenDto
                ?.let { appIntent.keepToken(it) }
                ?: run { appIntent.init() }
        }

        MobileTheme {
            Surface(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                AppBody(
                    windowSizeClass = appNavigationState.windowSizeClass,
                    appNavController = appNavigationState.appNavController,
                    isShowInit = appState.isShowInitScreen,
                    isShowLoading = appState.isShowLoading,
                    isShowLogin = appState.isShowLoginScreen,
                    isShowTop = appState.isShowTop,
                    isShowError = appState.isShowError to appState.error,
                )
            }
        }
    }
}

@Composable
private fun AppBody(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController,
    isShowInit: Boolean,
    isShowLoading: Boolean,
    isShowLogin: Boolean,
    isShowTop: Boolean,
    isShowError: Pair<Boolean, Throwable?>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        if (isShowTop) {
            WihdNavHost(
                windowSizeClass = windowSizeClass,
                appNavController = appNavController,
            )
        } else if (isShowLogin) {
            LoginScreen()
        } else if (isShowInit) {
            // TODO Show init screen.
        }

        LoadingMask(
            isLoading = isShowLoading,
            modifier = Modifier.fillMaxSize()
        )

        if (isShowError.first) {
            // TODO Show error.
        }
    }
}

@Composable
private fun WihdNavHost(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController,
) {
    NavHost(
        navController = appNavController,
        startDestination = topScreenRoute,
    ) {
        topScreen(
            windowSizeClass = windowSizeClass,
        )
    }
}
