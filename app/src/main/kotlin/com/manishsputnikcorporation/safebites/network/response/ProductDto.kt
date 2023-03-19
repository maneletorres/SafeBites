package com.manishsputnikcorporation.safebites.network.response

import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.squareup.moshi.Json

data class ProductDto(
    val id: String,
    @field:Json(name = "generic_name") val name: String?,
    @field:Json(name = "image_url") val imageUrl: String
)

fun ProductDto.asDomainModel(): ProductModel =
    ProductModel(id = id, name = name ?: "", imageUrl = imageUrl)
