package co.kr.parkjonghun.whatishedoingwithandroid.inside.exception

import co.kr.parkjonghun.whatishedoingwithandroid.base.exception.ExternalErrorException
import co.kr.parkjonghun.whatishedoingwithandroid.service.model.user.Token

class InvalidTokenException internal constructor(val token: Token) : ExternalErrorException()
