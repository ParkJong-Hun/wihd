package co.kr.parkjonghun.whatishedoingwithandroid.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import co.kr.parkjonghun.whatishedoingwithandroid.domain.usecase.SampleUseCase
import co.kr.parkjonghun.whatishedoingwithandroid.main.ui.navigation.MainNavigationRailItem
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

    fun onRailItemSelected(
        mainNavController: NavController,
        item: MainNavigationRailItem,
    ) {
        // TODO navigate to rail item's screen
    }

    fun routeToItem(item: String): MainNavigationRailItem? {
        return when (item) {
            // TODO const val on other module
            "news" -> MainNavigationRailItem.NEWS
            "post" -> MainNavigationRailItem.POST
            "profile" -> MainNavigationRailItem.PROFILE
            else -> null
        }
    }

    private fun sample() {
        viewModelScope.launch {
            _uiState.value = sampleUseCase.invoke().stuff
        }
    }
}