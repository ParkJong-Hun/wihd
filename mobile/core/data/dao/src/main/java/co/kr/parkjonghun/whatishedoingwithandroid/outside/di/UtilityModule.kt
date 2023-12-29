package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.KeyManager
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.KeyManagerImpl
import org.koin.dsl.module

val utilityModule = module {
    single<KeyManager> {
        KeyManagerImpl()
    }
}
