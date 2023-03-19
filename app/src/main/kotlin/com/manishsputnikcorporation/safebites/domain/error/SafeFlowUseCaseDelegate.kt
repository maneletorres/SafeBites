package com.manishsputnikcorporation.safebites.domain.error

import android.util.Log
import com.manishsputnikcorporation.safebites.domain.usecase.base.flow.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

interface SafeFlowUseCaseDelegate {

  val globalErrorManager: GlobalErrorManager

  fun <T, R> FlowUseCase<T, R>.safePrepare(
      input: T,
      onGenericError: (suspend () -> Unit)? = null,
      onUnauthorized: (suspend () -> Unit)? = null,
      onNetworkAvailable: (suspend () -> Unit)? = null,
      dismissAction: (() -> Unit)? = null
  ): Flow<R> =
      prepare(input).catch {
        Log.e(this@safePrepare.javaClass.simpleName, it.toString())
        globalErrorManager.emitError(
            it, onGenericError, onUnauthorized, onNetworkAvailable, dismissAction)
      }

  fun <T, R> FlowUseCase<Unit, R>.safePrepare(
      onGenericError: (suspend () -> Unit)? = null,
      onUnauthorized: (suspend () -> Unit)? = null,
      onNetworkAvailable: (suspend () -> Unit)? = null,
      dismissAction: (() -> Unit)? = null
  ): Flow<R> =
      prepare(Unit).catch {
        Log.e(this@safePrepare.javaClass.simpleName, it.toString())
        globalErrorManager.emitError(
            it, onGenericError, onUnauthorized, onNetworkAvailable, dismissAction)
      }

  class Default @Inject constructor(override val globalErrorManager: GlobalErrorManager) :
      SafeFlowUseCaseDelegate
}
