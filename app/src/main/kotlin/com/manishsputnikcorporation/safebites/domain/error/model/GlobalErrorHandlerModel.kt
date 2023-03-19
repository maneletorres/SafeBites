package com.manishsputnikcorporation.safebites.domain.error.model

import com.manishsputnikcorporation.safebites.domain.error.GlobalErrorType

data class GlobalErrorHandlerModel(
    val globalErrorType: GlobalErrorType,
    val onGenericError: (suspend () -> Unit)? = null,
    val onUnauthorized: (suspend () -> Unit)? = null,
    val onNetworkAvailable: (suspend () -> Unit)? = null,
    val dismissAction: (() -> Unit)? = null
)
