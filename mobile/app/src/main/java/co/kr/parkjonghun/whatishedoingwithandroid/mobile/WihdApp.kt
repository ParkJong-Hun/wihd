package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import co.kr.parkjonghun.whatishedoingwithandroid.data.di.dataSourceModule
import co.kr.parkjonghun.whatishedoingwithandroid.inside.di.repositoryModule
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.mainScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.main.mainScreenRoute
import co.kr.parkjonghun.whatishedoingwithandroid.service.statemachine.di.stateMachineModule
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.di.useCaseModule
import co.kr.parkjonghun.whatishedoingwithandroid.ui.theme.MobileTheme
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication

/**
 * Top level composable.
 */
@Suppress("ModifierMissing")
@Composable
fun WihdApp(
    windowSizeClass: WindowSizeClass,
) {
    KoinApplication(application = {
        androidLogger()

        modules(
            dataSourceModule,
            repositoryModule,
            stateMachineModule,
            useCaseModule,
        )
    }) {
        val appUiState: AppUiState = rememberAppUiState(
            windowSizeClass = windowSizeClass,
        )

        MobileTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                WihdNavHost(appUiState)
            }
        }
    }
}

@Composable
private fun WihdNavHost(
    appUiState: AppUiState,
) {
    NavHost(
        navController = appUiState.appNavController,
        startDestination = mainScreenRoute,
    ) {
        mainScreen(
            windowSizeClass = appUiState.windowSizeClass,
        )
    }
}
