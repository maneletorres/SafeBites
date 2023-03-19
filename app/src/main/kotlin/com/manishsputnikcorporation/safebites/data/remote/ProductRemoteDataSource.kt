package com.manishsputnikcorporation.safebites.data.remote

import com.manishsputnikcorporation.safebites.domain.error.exception.NetworkException
import com.manishsputnikcorporation.safebites.domain.error.exception.ServiceException
import com.manishsputnikcorporation.safebites.network.CustomError
import com.manishsputnikcorporation.safebites.network.CustomServicesExecutor
import com.manishsputnikcorporation.safebites.network.ProductsService
import com.manishsputnikcorporation.safebites.network.response.ProductsDto
import com.manishsputnikcorporation.safebites.network.utils.Either
import com.manishsputnikcorporation.safebites.utils.extensions.mapSuccess
import javax.inject.Inject
import retrofit2.Call

interface ProductRemoteDataSource {

  @Throws(NetworkException::class, ServiceException::class)
  fun getProducts(): Either<ProductsDto, CustomError>
}

class ProductRemoteDataSourceImpl
@Inject
constructor(
    private val customService: CustomServicesExecutor,
    private val service: ProductsService
) : ProductRemoteDataSource {

  @Throws(NetworkException::class, ServiceException::class)
  override fun getProducts(): Either<ProductsDto, CustomError> {
    val call: Call<ProductsDto> = service.getProducts()
    val response = customService.execute(call)
    return response.mapSuccess { it.data }
  }
}
