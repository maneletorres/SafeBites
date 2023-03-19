package com.manishsputnikcorporation.safebites.domain.repository

import com.manishsputnikcorporation.safebites.domain.common.DomainResponse
import com.manishsputnikcorporation.safebites.domain.model.ProductModel

interface ProductsRepository {

  fun getProducts(): DomainResponse<List<ProductModel>>
}
