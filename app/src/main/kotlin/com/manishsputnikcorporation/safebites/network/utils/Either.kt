package com.manishsputnikcorporation.safebites.network.utils

sealed class Either<out T, out E> {

  data class Success<out D>(val data: D) : Either<D, Nothing>()

  data class Failure<out E>(val error: E) : Either<Nothing, E>()
}

fun eitherEmpty(): Either.Success<Unit> = Either.Success(Unit)

fun <D> eitherSuccess(data: D): Either.Success<D> = Either.Success(data)

fun <E> eitherFailure(error: E): Either.Failure<E> = Either.Failure(error)
