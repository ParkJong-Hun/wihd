package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    init {
        sample()
    }

    private fun sample() {
        viewModelScope.launch {
            _uiState.value = sampleUseCase.invoke().stuff
        }
    }
}