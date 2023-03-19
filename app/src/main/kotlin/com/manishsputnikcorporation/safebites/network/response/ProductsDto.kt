package com.manishsputnikcorporation.safebites.network.response

import com.squareup.moshi.Json

data class ProductsDto(
    val count: String,
    val page: Int,
    @field:Json(name = "page_count") val pageCount: Int,
    @field:Json(name = "page_size") val pageSize: Int,
    val products: List<ProductDto>,
    val skip: Int
)
