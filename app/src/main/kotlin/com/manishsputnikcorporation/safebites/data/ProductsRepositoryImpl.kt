package com.manishsputnikcorporation.safebites.data

import com.manishsputnikcorporation.safebites.data.remote.ProductRemoteDataSource
import com.manishsputnikcorporation.safebites.domain.common.DomainError
import com.manishsputnikcorporation.safebites.domain.common.DomainResponse
import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.manishsputnikcorporation.safebites.domain.repository.ProductsRepository
import com.manishsputnikcorporation.safebites.network.response.asDomainModel
import com.manishsputnikcorporation.safebites.network.utils.eitherFailure
import com.manishsputnikcorporation.safebites.network.utils.eitherSuccess
import com.manishsputnikcorporation.safebites.utils.mapFailure
import com.manishsputnikcorporation.safebites.utils.mapSuccess
import javax.inject.Inject

class ProductsRepositoryImpl
@Inject
constructor(private val productsRemoteDataSource: ProductRemoteDataSource) : ProductsRepository {

  override fun getProducts(): DomainResponse<List<ProductModel>> =
      productsRemoteDataSource
          .getProducts()
          .mapSuccess { productsDto ->
            return eitherSuccess(productsDto.products.map { it.asDomainModel() })
          }
          .mapFailure { error ->
            return eitherFailure(DomainError(error.statusCode.toString(), error.raw))
          }
}
