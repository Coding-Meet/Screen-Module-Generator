package com.example.screenmodulegenerator.product

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    fun handleEvent(event: ProductUiEvent) {
        when (event) {
            is ProductUiEvent.OnButtonClick -> {
                _uiState.value = ProductUiState("Button Clicked!")
            }
        }
    }
}