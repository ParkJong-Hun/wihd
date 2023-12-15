package co.kr.parkjonghun.whatishedoingwithandroid.mobile.util

import android.util.Log
import timber.log.Timber


class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        // FirebaseCrashlytics.log(priority, tag, message)
        if (t != null) {
            if (priority == Log.ERROR) {
                // FirebaseCrashlytics.logError(t)
            } else if (priority == Log.WARN) {
                // FirebaseCrashlytics.logWarning(t)
            }
        }
    }
}