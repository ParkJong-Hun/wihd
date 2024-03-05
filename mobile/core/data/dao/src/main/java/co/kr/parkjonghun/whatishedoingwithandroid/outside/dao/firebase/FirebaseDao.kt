package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.firebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

interface FirebaseDao {
    fun setUserId(id: String)

    fun logEvent(name: String)
    fun logEvent(
        name: String,
        params: Map<String, Any>? = null,
    )

    fun logScreen(
        screenName: String,
    )
}

class FirebaseDaoImpl(
    context: Context,
) : FirebaseDao {
    private val analytics = FirebaseAnalytics.getInstance(context)

    override fun setUserId(id: String) {
        analytics.setUserId(id)
    }

    override fun logEvent(name: String) {
        analytics.logEvent(name, null)
    }

    override fun logEvent(
        name: String,
        params: Map<String, Any>?,
    ) {
        analytics.logEvent(name) {
            params?.forEach { (key, value) ->
                when (value) {
                    is Int -> param(key, value.toLong())
                    is Long -> param(key, value)

                    is Float -> param(key, value.toDouble())
                    is Double -> param(key, value)

                    is Boolean -> param(key, value.toString())

                    is String -> param(key, value)
                    else -> param(key, value.toString())
                }
            }
        }
    }

    override fun logScreen(screenName: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        }
    }
}
