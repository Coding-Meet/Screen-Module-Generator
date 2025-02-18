package com.example.screenmodulegenerator.home

sealed class HomeUiEvent {
    data object OnButtonClick : HomeUiEvent()
}