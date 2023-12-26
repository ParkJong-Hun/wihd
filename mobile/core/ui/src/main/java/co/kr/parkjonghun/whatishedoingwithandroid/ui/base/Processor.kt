package co.kr.parkjonghun.whatishedoingwithandroid.ui.base

import androidx.compose.runtime.State

typealias Processor<U, I> = Pair<State<U>, I>
