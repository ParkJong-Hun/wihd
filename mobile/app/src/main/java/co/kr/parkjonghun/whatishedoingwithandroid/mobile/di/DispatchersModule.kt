package co.kr.parkjonghun.whatishedoingwithandroid.mobile.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single(qualifier = named("io")) { Dispatchers.IO }
    single(qualifier = named("default")) { Dispatchers.Default }
    single(qualifier = named("main")) { Dispatchers.Main }
    single(qualifier = named("unconfined")) { Dispatchers.Unconfined }
    single(qualifier = named("test")) { Dispatchers.Unconfined }
}
