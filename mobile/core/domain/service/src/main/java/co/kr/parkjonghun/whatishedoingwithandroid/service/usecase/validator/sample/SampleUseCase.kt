package co.kr.parkjonghun.whatishedoingwithandroid.service.usecase.validator.sample

import androidx.core.text.isDigitsOnly

interface SampleValidatorUseCase {
    /**
     * Check if a string is all digits
     * @param input String
     * @return digitOnly ? true : false
     */
    operator fun invoke(input: String): Boolean
}

object SampleValidatorUseCaseImpl : SampleValidatorUseCase {
    override operator fun invoke(input: String): Boolean {
        return input.isDigitsOnly()
    }
}
