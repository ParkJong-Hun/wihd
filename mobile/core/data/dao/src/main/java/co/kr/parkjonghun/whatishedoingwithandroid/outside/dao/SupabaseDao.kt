package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao

import android.content.Intent
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.ExternalAuthAction
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.handleDeeplinks
import io.github.jan.supabase.gotrue.providers.Github
import io.github.jan.supabase.postgrest.Postgrest
import timber.log.Timber

object SupabaseDao {
    private const val SERVER_URL = "https://zonnknlwnkforradhexp.supabase.co"
    private const val SERVER_KEY: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpvbm5rbmx3bmtmb3JyYWRoZXhwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDIwMzY1ODEsImV4cCI6MjAxNzYxMjU4MX0.9ttr_RtSeoj9uHc6gY9iX4HlVmv4k9-DNFpEUrYN4y4"
    private const val OAUTH_CALLBACK_SCHEME = "wihd"
    private const val OAUTH_CALLBACK_HOST = "auth.callback"

    private val client = createSupabaseClient(
        supabaseUrl = SERVER_URL,
        supabaseKey = SERVER_KEY,
    ) {
        install(Postgrest)
        install(Auth) {
            flowType = FlowType.PKCE
            scheme = OAUTH_CALLBACK_SCHEME
            host = OAUTH_CALLBACK_HOST
            defaultExternalAuthAction = ExternalAuthAction.CUSTOM_TABS
        }
    }

    private val auth get() = client.auth

    suspend fun signInWithGithub() = auth.signUpWith(Github)

    suspend fun signOut() = auth.signOut()

    fun handleAfterAuth(intent: Intent) = client.handleDeeplinks(
        intent = intent,
        onSessionSuccess = {
            Timber.d("Login Success !\n${it.user?.toString()}")
        },
    )

    fun getUser() = auth.currentUserOrNull()
}
