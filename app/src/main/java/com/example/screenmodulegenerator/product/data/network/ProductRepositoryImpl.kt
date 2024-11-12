package com.example.screenmodulegenerator.product.data.network

import com.example.screenmodulegenerator.product.data.mappers.toDomainModel
import com.example.screenmodulegenerator.product.domain.ProductRepository
import com.example.screenmodulegenerator.product.domain.model.Product


class ProductRepositoryImpl(private val api: ProductService) : ProductRepository {
    override suspend fun fetchProduct(): List<Product> {
        val response = api.getProduct() // Assume this method fetches data from API
        return response.map { it.toDomainModel() }
    }
}
