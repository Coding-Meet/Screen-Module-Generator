package com.example.screenmodulegenerator.product.data.network

import com.example.screenmodulegenerator.product.data.network.dto.ProductResponseDto
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProduct(): List<ProductResponseDto>
}
