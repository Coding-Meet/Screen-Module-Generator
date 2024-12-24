package {{packageName}}.{{featureName}}

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class {{className}}ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow({{className}}UiState())
    val uiState: StateFlow<{{className}}UiState> = _uiState

    fun handleEvent(event: {{className}}UiEvent) {
        when (event) {
            is {{className}}UiEvent.OnButtonClick -> {
            _uiState.value = {{className}}UiState("Button Clicked!")
        }
        }
    }
}