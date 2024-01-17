package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.AuthCodeInfo
import io.github.jan.supabase.gotrue.user.UserInfo

interface RemoteDataSource {
    suspend fun login(): Unit?
    suspend fun logout()
    suspend fun getUser(authCodeInfo: AuthCodeInfo): UserInfo
}

internal class RemoteDataSourceImpl(
    private val supabaseDao: SupabaseDao,
) : RemoteDataSource {
    override suspend fun login(): Unit? = supabaseDao.signInWithGithub()

    override suspend fun logout() = supabaseDao.signOut()

    override suspend fun getUser(authCodeInfo: AuthCodeInfo) = supabaseDao.getUser(authCodeInfo)
}
