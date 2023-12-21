package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface RemoteDataSource {
    fun login()
    fun logout()
    fun getUser(): UserInfo?
}

internal class RemoteDataSourceImpl(
    private val supabaseDao: SupabaseDao,
    private val coroutineScope: CoroutineScope,
) : RemoteDataSource {
    override fun login() {
        coroutineScope.launch {
            supabaseDao.signInWithGithub()
        }
    }

    override fun logout() {
        coroutineScope.launch {
            supabaseDao.signOut()
        }
    }

    override fun getUser(): UserInfo? {
        return supabaseDao.getUser()
    }
}
