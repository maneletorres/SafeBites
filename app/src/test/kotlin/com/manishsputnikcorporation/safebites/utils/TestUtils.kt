package com.manishsputnikcorporation.safebites.utils

import com.manishsputnikcorporation.safebites.domain.common.DomainError
import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.manishsputnikcorporation.safebites.network.utils.Either
import com.manishsputnikcorporation.safebites.network.utils.eitherFailure
import com.manishsputnikcorporation.safebites.network.utils.eitherSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

val fakeDomainError = eitherFailure(DomainError("fakeErrorCode", "fakeErrorMessage"))

val fakeFlowDomainError = flow { emit(fakeDomainError) }

fun getDomainProductList(): Flow<Either<List<ProductModel>, DomainError>> = flow {
  emit(eitherSuccess(fakeProductModelList))
}
