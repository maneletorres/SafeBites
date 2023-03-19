package com.manishsputnikcorporation.safebites.domain.usecase.base.flow

import androidx.annotation.CheckResult
import com.manishsputnikcorporation.safebites.domain.usecase.base.dispatchers.DispatcherProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<T, R>(protected open val dispatcherProvider: DispatcherProvider) {

  protected open fun dispatcher(): CoroutineContext = dispatcherProvider.io()

  @CheckResult fun prepare(input: T) = prepareFlow(input).flowOn(dispatcher())

  protected abstract fun prepareFlow(input: T): Flow<R>
}

abstract class GenericFlowUseCase<T>(protected open val dispatcherProvider: DispatcherProvider) {

  protected open fun dispatcher(): CoroutineContext = dispatcherProvider.io()

  @CheckResult
  fun <R> prepare(input: T, klaas: Class<R>) = prepareFlow(input, klaas).flowOn(dispatcher())

  @CheckResult inline fun <reified R> prepare(input: T) = prepare(input, R::class.java)

  abstract fun <R> prepareFlow(input: T, klaas: Class<R>): Flow<R?>
}
