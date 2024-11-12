package com.example.screenmodulegenerator.product.data.mappers

import com.example.screenmodulegenerator.product.data.network.dto.ProductResponseDto
import com.example.screenmodulegenerator.product.domain.model.*

fun ProductResponseDto.toDomainModel(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        category = this.category,
        image = this.image
    )
}
