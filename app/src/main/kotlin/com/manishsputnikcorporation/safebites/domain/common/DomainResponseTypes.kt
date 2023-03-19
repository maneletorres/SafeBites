package com.manishsputnikcorporation.safebites.domain.common

import com.manishsputnikcorporation.safebites.network.utils.Either

data class DomainError(val code: String, val message: String)

typealias DomainResponse<T> = Either<T, DomainError>
