package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.DataStoreDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.DataStoreDaoImpl
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDao
import co.kr.parkjonghun.whatishedoingwithandroid.outside.dao.SupabaseDaoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val daoModule = module {
    single<DataStoreDao> {
        DataStoreDaoImpl(context = androidContext())
    }

    single<SupabaseDao> {
        SupabaseDaoImpl()
    }
}
