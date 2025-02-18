package {{packageName}}.{{featureName}}

sealed class {{className}}UiEvent {
    data object OnButtonClick : {{className}}UiEvent()
}