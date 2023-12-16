package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.sample.SampleValidatorUseCase
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.sample.SampleValidatorUseCaseImpl
import org.koin.dsl.module

internal val validatorModule = module {
    single<SampleValidatorUseCase> {
        SampleValidatorUseCaseImpl
    }
}