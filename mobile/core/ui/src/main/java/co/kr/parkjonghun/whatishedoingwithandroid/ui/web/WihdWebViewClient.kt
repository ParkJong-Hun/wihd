package co.kr.parkjonghun.whatishedoingwithandroid.ui.web

import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import co.kr.parkjonghun.whatishedoingwithandroid.ui.web.navigation.WebViewNavigator

/**
 * WihdWebViewClient
 *
 * As Wihd Web needs to set its own web client to function, it provides this intermediary
 * class that can be overriden if further custom behaviour is required.
 */
@Suppress("unused")
class WihdWebViewClient : WebViewClient() {
    lateinit var state: WebViewState
        internal set
    lateinit var navigator: WebViewNavigator
        internal set

    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        state.loadingState = LoadingState.Loading(0.0f)
        state.errorsForCurrentRequest.clear()
        state.pageTitle = null
        state.pageIcon = null

        state.lastLoadedUrl = url
    }

    override fun onPageFinished(view: WebView, url: String?) {
        super.onPageFinished(view, url)
        state.loadingState = LoadingState.Finished
    }

    override fun doUpdateVisitedHistory(view: WebView, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)

        navigator.canGoBack = view.canGoBack()
        navigator.canGoForward = view.canGoForward()
    }

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest?,
        error: WebResourceError?,
    ) {
        super.onReceivedError(view, request, error)

        if (error != null) {
            state.errorsForCurrentRequest.add(WebViewError(request, error))
        }
    }
}

/**
 * WihdWebChromeClient
 *
 * As Wihd Web needs to set its own web client to function, it provides this intermediary
 * class that can be overriden if further custom behaviour is required.
 */
class WihdWebChromeClient : WebChromeClient() {
    lateinit var state: WebViewState
        internal set

    override fun onReceivedTitle(view: WebView, title: String?) {
        super.onReceivedTitle(view, title)
        state.pageTitle = title
    }

    override fun onReceivedIcon(view: WebView, icon: Bitmap?) {
        super.onReceivedIcon(view, icon)
        state.pageIcon = icon
    }

    override fun onProgressChanged(view: WebView, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (state.loadingState is LoadingState.Finished) return
        state.loadingState = LoadingState.Loading(newProgress / 100.0f)
    }
}
