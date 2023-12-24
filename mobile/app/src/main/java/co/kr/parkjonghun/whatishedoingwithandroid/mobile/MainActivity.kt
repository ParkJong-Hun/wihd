package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.net.Uri
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
            uri.getFUCKINGQueryParameter(KEY_ACCESS_TOKEN)?.let { accessToken ->
                TokenDto(
                    accessToken = accessToken,
                    providerToken = requireNotNull(uri.getFUCKINGQueryParameter(KEY_PROVIDER_TOKEN)),
                    refreshToken = requireNotNull(uri.getFUCKINGQueryParameter(KEY_REFRESH_TOKEN)),
                    expiresAt = requireNotNull(
                        uri.getFUCKINGQueryParameter(KEY_EXPIRES_AT)?.toLong()
                    ),
                    expiresIn = requireNotNull(
                        uri.getFUCKINGQueryParameter(KEY_EXPIRES_IN)?.toLong()
                    ),
                    tokenType = requireNotNull(uri.getFUCKINGQueryParameter(KEY_TOKEN_TYPE)),
                )
            }
        }
    }

    private fun Uri.getFUCKINGQueryParameter(key: String): String? {
        val queries = this.toString().substringAfter('#')
        val queryParameter = queries
            ?.split('&')
            ?.map { it.split("=") }
            ?.firstOrNull { it.first() == key }
        return queryParameter?.last()
    }

    companion object {
        // TODO check build variant
        private const val IS_DEBUG = true

        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_PROVIDER_TOKEN = "provider_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_EXPIRES_AT = "expires_at"
        private const val KEY_EXPIRES_IN = "expires_in"
        private const val KEY_TOKEN_TYPE = "token_type"
        private const val KEY_ERROR = "error"
        private const val KEY_ERROR_CODE = "error_code"
        private const val KEY_ERROR_DESCRIPTION = "error_description"
    }
}
