package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine

/**
 *  State in the domain layer.
 */
interface State

/**
 *  State with throwable.
 */
interface ErrorState : State {
    val throwable: Throwable
}
