package com.example.screenmodulegenerator.product.domain

import com.example.screenmodulegenerator.product.domain.model.Product

interface ProductRepository {
    suspend fun fetchProduct(): List<Product>
}
