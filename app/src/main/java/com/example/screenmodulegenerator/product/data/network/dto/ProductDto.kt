package com.example.screenmodulegenerator.product.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponseDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
)
