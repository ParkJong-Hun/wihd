package co.kr.parkjonghun.whatishedoingwithandroid.outside.di

import org.koin.dsl.module

val dataModule = module {
    includes(
        daoModule,
        dataSourceModule,
        utilityModule,
    )
}
