package co.kr.parkjonghun.whatishedoingwithandroid.data.datasource

import android.content.Context
import co.kr.parkjonghun.whatishedoingwithandroid.data.extension.sampleDataStore
import co.kr.parkjonghun.whatishedoingwithandroid.data.extension.userDataStore
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val context: Context,
) {
    private val sampleDataStore get() = context.sampleDataStore
    private val userDataStore get() = context.userDataStore
}
