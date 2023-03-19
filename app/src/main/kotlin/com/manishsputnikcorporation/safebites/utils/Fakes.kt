package com.manishsputnikcorporation.safebites.utils

import com.manishsputnikcorporation.safebites.network.response.ProductDto
import com.manishsputnikcorporation.safebites.network.response.ProductsDto
import com.manishsputnikcorporation.safebites.network.response.asDomainModel

val fakeProductsDto =
    ProductsDto(
        count = "306555",
        page = 1,
        pageCount = 3,
        pageSize = 3,
        products =
            listOf(
                ProductDto(
                    "3017620422003",
                    "Crema de Avellanas con cacao",
                    "https://images.openfoodfacts.org/images/products/301/762/042/2003/front_es.445.400.jpg"),
                ProductDto(
                    "5449000000996",
                    "Bebida refrescante aromatizada",
                    "https://images.openfoodfacts.org/images/products/544/900/000/0996/front_es.667.400.jpg"),
                ProductDto(
                    "3046920029759",
                    "Tableta de chocolate negro con un 90% de cacao mínimo",
                    "https://images.openfoodfacts.org/images/products/304/692/002/9759/front_es.255.400.jpg")),
        skip = 0)

val fakeProductModelList = fakeProductsDto.products.map { it.asDomainModel() }
