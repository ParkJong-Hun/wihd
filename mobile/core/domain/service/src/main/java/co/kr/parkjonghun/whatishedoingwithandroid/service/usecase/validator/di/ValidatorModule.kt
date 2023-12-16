package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.di

import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.sample.SampleValidator
import co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.sample.SampleValidatorImpl
import org.koin.dsl.module

internal val validatorModule = module {
    single<SampleValidator> {
        SampleValidatorImpl
    }
}
