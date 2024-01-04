/**
 * This is Continued use and improvement of deprecated Accompanist Web view.
 * https://google.github.io/accompanist/web/
 */

package co.kr.parkjonghun.whatishedoingwithandroid.ui.web

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import androidx.compose.runtime.Immutable

/**
 * A wrapper class to hold errors from the WebView.
 */
@Immutable
data class WebViewError(
    /**
     * The request the error came from.
     */
    val request: WebResourceRequest?,
    /**
     * The error that was reported.
     */
    val error: WebResourceError,
)
