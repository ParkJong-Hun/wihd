package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import co.kr.parkjonghun.whatishedoingwithandroid.data.di.dataSourceModule
import co.kr.parkjonghun.whatishedoingwithandroid.feature.login.LoginScreen
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.topScreen
import co.kr.parkjonghun.whatishedoingwithandroid.feature.top.topScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.inside.di.repositoryModule
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.navigation.AppNavigationState
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.navigation.rememberAppNavigationState
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.di.useCaseModule
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication

/**
 * Top level composable.
 */
@Suppress("ModifierMissing")
@Composable
fun App(
    windowSizeClass: WindowSizeClass,
) {
    KoinApplication(application = {
        androidLogger()
        modules(
            dataSourceModule,
            repositoryModule,
            useCaseModule,
        )
    }) {
        val appNavigationState: AppNavigationState = rememberAppNavigationState(
            windowSizeClass = windowSizeClass,
        )
        val (appState, appIntent) = rememberAppUiState()

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
                    isShowLoading = appState.value.isShowLoading,
                    isShowError = appState.value.isShowError to appState.value.error,
                    isShowTop = appState.value.isShowSomething,
                    onClickLogin = appIntent::login,
                    onClickErrorOk = appIntent::confirmErrorDialog,
                )
            }
        }
    }
}

@Composable
private fun AppBody(
    windowSizeClass: WindowSizeClass,
    appNavController: NavHostController,
    isShowLoading: Boolean,
    isShowError: Pair<Boolean, Throwable?>,
    isShowTop: Boolean,
    onClickLogin: () -> Unit,
    onClickErrorOk: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        if (isShowTop) {
            WihdNavHost(
                windowSizeClass = windowSizeClass,
                appNavController = appNavController,
            )
        } else {
            LoginScreen(
                onClickLogin = onClickLogin,
                modifier = modifier,
            )
        }

        if (isShowLoading) {
            // TODO Show animation.
        }

        // TODO handle error
        if (isShowError.first) {
            Dialog(onDismissRequest = onClickErrorOk) {
                Text("error occurs\n${isShowError.second}")
            }
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
