package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine

interface State

interface ErrorState : State {
    val throwable: Throwable
}
