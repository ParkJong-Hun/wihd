package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.AESKeyManager
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.AESKeyManagerImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.SecurityUtil
import co.kr.parkjonghun.whatishedoingwithandroid.outside.utility.key.SecurityUtilImpl
import org.koin.dsl.module

val utilityModule = module {
    single<AESKeyManager> {
        AESKeyManagerImpl()
    }

    single<SecurityUtil> {
        SecurityUtilImpl(get())
    }
}
