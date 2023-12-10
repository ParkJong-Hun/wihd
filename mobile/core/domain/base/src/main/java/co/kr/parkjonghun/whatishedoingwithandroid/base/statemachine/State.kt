package co.kr.parkjonghun.whatishedoingwithandroid.base.statemachine

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
