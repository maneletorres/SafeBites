package com.manishsputnikcorporation.safebites.ui.utils.extensions

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import timber.log.Timber

suspend fun ScaffoldState.handleEventError(
    tag: String,
    internalErrorMessage: String,
    friendlyErrorMessage: String,
    actionLabel: String
) {
  Timber.tag(tag).d(internalErrorMessage)
  snackbarHostState.showCustomSnackbar(
      friendlyErrorMessage = friendlyErrorMessage, actionLabel = actionLabel)
}

suspend fun SnackbarHostState.showCustomSnackbar(
    friendlyErrorMessage: String,
    actionLabel: String
) {
  showSnackbar(
      message = friendlyErrorMessage, actionLabel = actionLabel, duration = SnackbarDuration.Long)
}
