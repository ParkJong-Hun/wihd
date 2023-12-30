package co.kr.parkjonghun.whatishedoingwithandroid.base.exception

/**
 * Exception thrown by this app.
 */
abstract class WihdException : RuntimeException()

/**
 * Exception thrown by this app domain layer.
 */
abstract class InternalErrorException : WihdException()

/**
 * Exception thrown by this app data layer.
 */
abstract class ExternalErrorException : WihdException()

/**
 * Undefined exception.
 */
class UnknownErrorException(val throwable: Throwable) : WihdException()
