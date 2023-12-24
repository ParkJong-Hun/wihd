package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.util.CrashReportingTree
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.TokenDto
import timber.log.Timber
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        plant(if (IS_DEBUG) DebugTree() else CrashReportingTree())

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            App(
                context = applicationContext,
                windowSizeClass = calculateWindowSizeClass(this),
                newTokenDto = tryToGetToken(),
            )
        }
    }

    private fun tryToGetToken(): TokenDto? {
        Timber.d(intent.data?.queryParameterNames?.toString())
        return intent.data?.let { uri ->
            uri.getQueryParameter(KEY_ERROR)?.let { error ->
                val errorCode = uri.getQueryParameter(KEY_ERROR_CODE)
                val errorDescription = uri.getQueryParameter(KEY_ERROR_DESCRIPTION)
                Timber.e("auth error: $error, errorCode: $errorCode, errorDescription: $errorDescription")
                AlertDialog.Builder(this)
                    .setMessage(resources.getString(R.string.auth_error_description))
                    .setPositiveButton(resources.getString(R.string.auth_error_ok)) { _, _ -> }
                    .create()
                    .show()
            }
            uri.getQueryParameter("access_token")?.let { accessToken ->
                @Suppress("UnusedPrivateProperty")
                val providerToken = uri.getQueryParameter("provider_token")
                val refreshToken = uri.getQueryParameter("refresh_token")
                val expiresIn = uri.getQueryParameter("expires_in")?.toLong()
                val tokenType = uri.getQueryParameter("token_type")
                TokenDto(
                    accessToken = accessToken,
                    refreshToken = requireNotNull(refreshToken),
                    expiresIn = requireNotNull(expiresIn),
                    tokenType = requireNotNull(tokenType),
                )
            }
        }
    }

    companion object {
        // TODO check build variant
        private const val IS_DEBUG = true

        private const val KEY_ERROR = "error"
        private const val KEY_ERROR_CODE = "error_code"
        private const val KEY_ERROR_DESCRIPTION = "error_description"
    }
}
