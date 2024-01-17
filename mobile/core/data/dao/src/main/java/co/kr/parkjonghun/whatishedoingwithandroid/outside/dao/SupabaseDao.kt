package co.kr.parkjonghun.whatishedoingwithandroid.outside.dao

import co.kr.parkjonghun.whatishedoingwithandroid.outside.model.AuthCodeInfo
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Github
import io.github.jan.supabase.gotrue.user.UserInfo
import io.github.jan.supabase.gotrue.user.UserSession
import io.github.jan.supabase.postgrest.Postgrest
import timber.log.Timber

interface SupabaseDao {
    suspend fun signInWithGithub(): Unit?
    suspend fun signOut()
    suspend fun getUser(authCodeInfo: AuthCodeInfo): UserInfo
    suspend fun getSession(authCode: AuthCodeInfo): UserSession
}

internal class SupabaseDaoImpl : SupabaseDao {
    private val client = createSupabaseClient(
        supabaseUrl = SERVER_URL,
        supabaseKey = SERVER_KEY,
    ) {
        install(Postgrest)
        install(Auth) {
            flowType = FlowType.PKCE
            scheme = OAUTH_CALLBACK_SCHEME
            host = OAUTH_CALLBACK_HOST
        }
    }

    private val auth get() = client.auth

    override suspend fun signInWithGithub() = auth.signUpWith(Github)

    override suspend fun signOut() = auth.signOut()

    override suspend fun getUser(authCodeInfo: AuthCodeInfo): UserInfo {
        return retrieveUserInfo(authCodeInfo)
    }

    override suspend fun getSession(authCode: AuthCodeInfo): UserSession {
        return auth.exchangeCodeForSession(authCode.authCode)
    }

    private suspend fun retrieveUserInfo(authCodeInfo: AuthCodeInfo): UserInfo {
        return runCatching { auth. }
            .onSuccess {
                Timber.tag(LOG_TAG)
                    .d("retreiving userInfo with access token is successful")
                Timber.tag(LOG_TAG)
                    .v("$it")
            }
            .onFailure {
                Timber.tag(LOG_TAG)
                    .e("retrieving userInfo with access token is failed.\n${it.stackTraceToString()}")
            }
            .recover { auth.retrieveUser(authCodeInfo.refreshToken) }
            .onSuccess {
                Timber.tag(LOG_TAG)
                    .d("retreiving userInfo with refresh token is successful")
                Timber.tag(LOG_TAG)
                    .v("$it")
            }
            .onFailure {
                Timber.tag(LOG_TAG)
                    .e("retrieving userInfo with refresh token is failed.\n${it.stackTraceToString()}")
            }
            .getOrThrow()
    }

    companion object {
        private const val SERVER_URL = "https://zonnknlwnkforradhexp.supabase.co"
        private const val SERVER_KEY: String =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpvbm5rbmx3bmtmb3JyYWRoZXhwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDIwMzY1ODEsImV4cCI6MjAxNzYxMjU4MX0.9ttr_RtSeoj9uHc6gY9iX4HlVmv4k9-DNFpEUrYN4y4"

        private const val OAUTH_CALLBACK_SCHEME = "wihd"
        private const val OAUTH_CALLBACK_HOST = "auth.callback"

        private const val LOG_TAG = "Supabase"
    }
}
