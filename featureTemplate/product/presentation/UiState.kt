package {{packageName}}.{{featureName}}.presentation

import {{packageName}}.{{featureName}}.domain.model.{{className}}
import androidx.compose.runtime.Immutable

@Immutable
data class {{className}}UiState(
    val isLoading: Boolean = false,
    val {{featureName}}s: List<{{className}}> = emptyList(),
)