package co.kr.parkjonghun.whatishedoingwithandroid.domain.statemachine

import android.os.Parcelable

/**
 *  State in the domain layer.
 */
interface State : Parcelable

/**
 *  State with throwable.
 */
interface ErrorState : State {
    val throwable: Throwable
}
