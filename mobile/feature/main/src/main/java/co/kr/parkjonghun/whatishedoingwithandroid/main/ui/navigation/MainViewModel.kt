package co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sampleUseCase: SampleUseCase
) : ViewModel() {

    fun sample() {
        viewModelScope.launch {
            sampleUseCase.invoke()
        }
    }
}