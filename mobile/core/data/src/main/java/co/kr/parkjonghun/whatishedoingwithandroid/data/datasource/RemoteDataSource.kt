package co.kr.parkjonghun.whatishedoingwithandroid.data.datasource

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

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
