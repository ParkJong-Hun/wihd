package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.report.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.report.screen.ReportScreen
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.report.screen.ReportScreenImpl
import org.koin.dsl.module

internal val reportModule = module {
    single<ReportScreen> {
        ReportScreenImpl(
            analyticsProvider = get(),
        )
    }
}
