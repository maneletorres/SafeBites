package com.manishsputnikcorporation.safebites.domain.error

import com.manishsputnikcorporation.safebites.domain.error.GlobalErrorType.*
import com.manishsputnikcorporation.safebites.domain.error.exception.NetworkException
import com.manishsputnikcorporation.safebites.domain.error.exception.UnauthorizedException
import java.util.concurrent.CancellationException
import javax.inject.Inject

interface GlobalErrorMapper {

  fun map(throwable: Throwable): GlobalErrorType
}

class GlobalErrorMapperImpl @Inject constructor() : GlobalErrorMapper {

  override fun map(throwable: Throwable): GlobalErrorType =
      when (throwable) {
        is UnauthorizedException -> UNAUTHORIZED
        is NetworkException -> NETWORK_UNAVAILABLE
        is CancellationException,
        is IllegalStateException -> SILENT
        else -> GENERIC_ERROR
      }
}
