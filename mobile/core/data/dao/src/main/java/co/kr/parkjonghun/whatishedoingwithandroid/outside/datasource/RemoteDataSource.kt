package co.kr.parkjonghun.whatishedoingwithandroid.outside.datasource

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

/**
 * API DAOs integration source.
 */
interface RemoteDataSource

internal class RemoteDataSourceImpl : RemoteDataSource {
    val client = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = KEY,
    ) {
        install(Postgrest)
        install(GoTrue)
    }

    companion object {
        private const val URL = "https://zonnknlwnkforradhexp.supabase.co"
        private val KEY: String = requireNotNull(System.getenv("WIHD_SUPABASE_KEY"))
    }
}
