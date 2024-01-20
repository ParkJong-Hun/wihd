package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface RemoteDataSource {
    val currentUser: Flow<UserInfo?>
    suspend fun login(): Unit?
    suspend fun logout()
}

internal class RemoteDataSourceImpl(
    private val supabaseDao: SupabaseDao,
) : RemoteDataSource {
    override val currentUser: Flow<UserInfo?> = flow {
        while (true) {
            val currentUser = supabaseDao.getUser()
            emit(currentUser)
            delay(GET_USER_DELAY_MILLIS)
        }
    }

    override suspend fun login(): Unit? = supabaseDao.signInWithGithub()

    override suspend fun logout() = supabaseDao.signOut()

    companion object {
        private const val GET_USER_DELAY_MILLIS = 1000L
    }
}
