package com.example.screenmodulegenerator.product.presentation

import com.example.screenmodulegenerator.product.domain.model.Product
import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
)