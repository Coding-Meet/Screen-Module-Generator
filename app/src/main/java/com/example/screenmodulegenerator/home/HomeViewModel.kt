package com.example.screenmodulegenerator.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun handleEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnButtonClick -> {
                _uiState.value = HomeUiState("Button Clicked!")
            }
        }
    }
}