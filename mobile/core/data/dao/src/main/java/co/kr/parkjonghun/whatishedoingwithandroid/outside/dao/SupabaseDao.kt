package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.Github
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.postgrest.Postgrest

interface SupabaseDao {
    suspend fun signInWithGithub(): Unit?
    suspend fun signOut()
    fun getUser(): UserInfo?
}

internal class SupabaseDaoImpl : SupabaseDao {
    private val client = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = KEY,
    ) {
        install(Postgrest)
        install(GoTrue)
    }

    private val auth = client.gotrue

    override suspend fun signInWithGithub() = auth.signUpWith(Github)

    override suspend fun signOut() = auth.logout()

    override fun getUser() = auth.currentUserOrNull()

    companion object {
        private const val URL = "https://zonnknlwnkforradhexp.supabase.co"
        private val KEY: String = requireNotNull(System.getenv("WIHD_SUPABASE_KEY"))
    }
}
