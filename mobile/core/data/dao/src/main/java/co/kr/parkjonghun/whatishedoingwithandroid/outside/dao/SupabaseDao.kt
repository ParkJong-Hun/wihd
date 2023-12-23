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
        supabaseUrl = SERVER_URL,
        supabaseKey = SERVER_KEY,
    ) {
        install(Postgrest)
        install(GoTrue) {
            scheme = OAUTH_CALLBACK_SCHEME
            host = OAUTH_CALLBACK_HOST
        }
    }

    private val auth = client.gotrue

    override suspend fun signInWithGithub() = auth.signUpWith(Github)

    override suspend fun signOut() = auth.logout()

    override fun getUser() = auth.currentUserOrNull()

    companion object {
        private const val SERVER_URL = "https://zonnknlwnkforradhexp.supabase.co"
        private const val SERVER_KEY: String =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpvbm5rbmx3bmtmb3JyYWRoZXhwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDIwMzY1ODEsImV4cCI6MjAxNzYxMjU4MX0.9ttr_RtSeoj9uHc6gY9iX4HlVmv4k9-DNFpEUrYN4y4"

        private const val OAUTH_CALLBACK_SCHEME = "https"
        private const val OAUTH_CALLBACK_HOST = "zonnknlwnkforradhexp.supabase.co"
    }
}
