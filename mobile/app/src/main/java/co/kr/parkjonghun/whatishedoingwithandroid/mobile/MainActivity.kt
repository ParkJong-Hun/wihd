package co.kr.parkjonghun.whatishedoingwithandroid.mobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Process
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.kr.parkjonghun.whatishedoingwithandroid.mobile.util.CrashReportingTree
import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.repository.dto.presentation.AuthCodeDto
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
                windowSizeClass = calculateWindowSizeClass(this),
                newAuthCodeDto = tryToGetAuthCode(),
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // Unreachable.
        Process.killProcess(Process.myPid())
    }

    private fun tryToGetAuthCode(): AuthCodeDto? {
        return intent.data?.let { uri ->
            uri.getQueryParameter(KEY_ERROR)?.let { error ->
                val errorCode = uri.getQueryParameter(KEY_ERROR_CODE)
                val errorDescription = uri.getQueryParameter(KEY_ERROR_DESCRIPTION)
                Timber.e("auth error: $error, errorCode: $errorCode, errorDescription: $errorDescription")
                showAppErrorDialog()
            }
            uri.getQueryParameter(KEY_AUTH_CODE)?.let { authCode ->
                AuthCodeDto(authCode)
            }
        }
    }

    private fun Uri.getFUCKINGQueryParameter(key: String): String? {
        val queries = this.toString().substringAfter('#')
        val queryParameter = queries
            .split('&')
            .map { it.split("=") }
            .firstOrNull { it.first() == key }
        return queryParameter?.last()
    }

    private fun showAppErrorDialog() {
        AlertDialog.Builder(this)
            .setMessage(resources.getString(R.string.auth_error_description))
            .setPositiveButton(resources.getString(R.string.auth_error_ok)) { _, _ -> }
            .create()
            .show()
    }

    companion object {
        // TODO check build variant
        private const val IS_DEBUG = true

        private const val KEY_AUTH_CODE = "code"
        private const val KEY_ERROR = "error"
        private const val KEY_ERROR_CODE = "error_code"
        private const val KEY_ERROR_DESCRIPTION = "error_description"
    }
}
