package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDaoImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.datastore.UserDataStoreDaoImpl
import org.koin.dsl.module

internal val daoModule = module {
    single<UserDataStoreDao> {
        UserDataStoreDaoImpl()
    }

    single<SupabaseDao> {
        SupabaseDaoImpl()
    }
}
