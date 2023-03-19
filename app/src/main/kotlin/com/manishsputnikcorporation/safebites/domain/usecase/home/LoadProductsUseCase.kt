package com.manishsputnikcorporation.safebites.domain.usecase.home

import com.manishsputnikcorporation.safebites.domain.common.DomainError
import com.manishsputnikcorporation.safebites.domain.error.GlobalErrorMapper
import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.manishsputnikcorporation.safebites.domain.repository.ProductsRepository
import com.manishsputnikcorporation.safebites.domain.usecase.base.dispatchers.DispatcherProvider
import com.manishsputnikcorporation.safebites.domain.usecase.base.flow.FlowUseCase
import com.manishsputnikcorporation.safebites.domain.usecase.base.flow.FlowUseCaseExceptionDelegate
import com.manishsputnikcorporation.safebites.network.utils.Either
import com.manishsputnikcorporation.safebites.network.utils.eitherFailure
import com.manishsputnikcorporation.safebites.network.utils.eitherSuccess
import com.manishsputnikcorporation.safebites.utils.extensions.onFailure
import com.manishsputnikcorporation.safebites.utils.extensions.onSuccess
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadProductsUseCase
@Inject
constructor(
    private val productsRepository: ProductsRepository,
    override val globalErrorMapper: GlobalErrorMapper,
    dispatcherProvider: DispatcherProvider
) :
    FlowUseCase<Unit, Either<List<ProductModel>, DomainError>>(dispatcherProvider),
    FlowUseCaseExceptionDelegate {

  override fun prepareFlow(input: Unit): Flow<Either<List<ProductModel>, DomainError>> = flow {
    productsRepository
        .getProducts()
        .onSuccess { emit(eitherSuccess(it)) }
        .onFailure { emit(eitherFailure(it)) }
  }
}
