package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.report

import co.kr.parkjonghun.whatishedoingwithandroid.service.gateway.provider.AnalyticsProvider

interface ReportScreenUseCase {
    operator fun invoke()
}

class ReportScreenUseCaseImpl(
    private val analyticsProvider: AnalyticsProvider
) : ReportScreenUseCase {
    override fun invoke(screenName: String) {
        analyticsProvider.logEvent("screen_view", mapOf("screen_name" to screenName))
    }
}
