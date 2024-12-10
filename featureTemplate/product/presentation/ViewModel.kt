package {{packageName}}.{{featureName}}.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import {{packageName}}.{{featureName}}.domain.{{className}}Repository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class {{className}}ViewModel(private val repository: {{className}}Repository) : ViewModel() {

    private val _uiState = MutableStateFlow({{className}}UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetch{{className}}()
    }

    private fun fetch{{className}}() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                val result = repository.fetch{{className}}()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        {{featureName}}s = result
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }
        }
    }
}