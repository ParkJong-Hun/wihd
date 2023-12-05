@file:Suppress("UnstableCollections")

/**
 * This is Continued use and improvement of deprecated Accompanist Web view.
 * https://google.github.io/accompanist/web/
 */

package co.kr.parkjonghun.whatishedoingwithandroid.ui.web

sealed class WebContent {
    data class Url(
        val url: String,
        val additionalHttpHeaders: Map<String, String> = emptyMap(),
    ) : WebContent()

    data class Data(
        val data: String,
        val baseUrl: String? = null,
        val encoding: String = "utf-8",
        val mimeType: String? = null,
        val historyUrl: String? = null,
    ) : WebContent()

    data class Post(
        val url: String,
        val postData: ByteArray,
    ) : WebContent() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Post

            if (url != other.url) return false
            return postData.contentEquals(other.postData)
        }

        override fun hashCode(): Int {
            var result = url.hashCode()
            result = 31 * result + postData.contentHashCode()
            return result
        }
    }

    @Suppress("unused")
    fun getCurrentUrl(): String? {
        return when (this) {
            is Url -> url
            is Data -> baseUrl
            is Post -> url
            is NavigatorOnly -> error("Unsupported")
        }
    }

    data object NavigatorOnly : WebContent()
}

@Suppress("unused")
internal fun WebContent.withUrl(url: String) = when (this) {
    is WebContent.Url -> copy(url = url)
    else -> WebContent.Url(url)
}
