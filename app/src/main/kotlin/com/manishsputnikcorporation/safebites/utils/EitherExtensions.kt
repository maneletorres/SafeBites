package com.manishsputnikcorporation.safebites.utils

import com.manishsputnikcorporation.safebites.network.utils.Either
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// region Side-effects
@OptIn(ExperimentalContracts::class)
inline fun <T, E> Either<T, E>.onSuccess(action: (T) -> Unit): Either<T, E> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

  if (this is Either.Success) action(data)
  return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T, E> Either<T, E>.onFailure(action: (E) -> Unit): Either<T, E> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

  if (this is Either.Failure) action(error)
  return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T1, E, T2> Either<T1, E>.mapSuccess(action: (T1) -> T2): Either<T2, E> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

  return when (this) {
    is Either.Failure -> Either.Failure(error)
    is Either.Success -> Either.Success(action(data))
  }
}

@OptIn(ExperimentalContracts::class)
inline fun <T, E1, E2> Either<T, E1>.mapFailure(action: (E1) -> E2): Either<T, E2> {
  contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

  return when (this) {
    is Either.Failure -> Either.Failure(action(error))
    is Either.Success -> Either.Success(data)
  }
}
// endregion
