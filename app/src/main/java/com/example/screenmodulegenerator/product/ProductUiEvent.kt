package com.example.screenmodulegenerator.product

sealed class ProductUiEvent {
    data object OnButtonClick : ProductUiEvent()
}