package com.manishsputnikcorporation.safebites.domain.error

import com.manishsputnikcorporation.safebites.domain.error.model.GlobalErrorHandlerModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface GlobalErrorManager {

  suspend fun emitError(
      throwable: Throwable,
      onGenericError: (suspend () -> Unit)? = null,
      onUnauthorized: (suspend () -> Unit)? = null,
      onNetworkAvailable: (suspend () -> Unit)? = null,
      dismissAction: (() -> Unit)? = null
  )
}

class GlobalErrorManagerImpl
@Inject
constructor(private val defaultErrorMapper: GlobalErrorMapper) : GlobalErrorManager {

  private val _event: MutableSharedFlow<GlobalErrorHandlerModel> = MutableSharedFlow()
  val event: SharedFlow<GlobalErrorHandlerModel> = _event.asSharedFlow()

  override suspend fun emitError(
      throwable: Throwable,
      onGenericError: (suspend () -> Unit)?,
      onUnauthorized: (suspend () -> Unit)?,
      onNetworkAvailable: (suspend () -> Unit)?,
      dismissAction: (() -> Unit)?
  ) {
    _event.emit(
        GlobalErrorHandlerModel(
            defaultErrorMapper.map(throwable),
            onGenericError,
            onUnauthorized,
            onNetworkAvailable,
            dismissAction))
  }
}
