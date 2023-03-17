package com.manishsputnikcorporation.safebites.ui.screens.generic

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.manishsputnikcorporation.safebites.R

@Composable
fun GenericLoading(modifier: Modifier = Modifier) {
  Surface(contentColor = MaterialTheme.colors.primary) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      CircularProgressIndicator()
    }
  }
}

@Composable
fun EmptyContent(@StringRes message: Int) {
  Surface(contentColor = MaterialTheme.colors.primary) {
    Box(modifier = Modifier.fillMaxSize().padding(32.dp), contentAlignment = Alignment.Center) {
      Text(text = stringResource(id = message))
    }
  }
}

@Composable
fun GenericPlaceholder(modifier: Modifier = Modifier) {
  Box(modifier = Modifier.height(200.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
    Image(
        painter = painterResource(id = R.drawable.ic_placeholder),
        contentDescription = "",
        modifier = modifier.width(50.dp).height(50.dp))
  }
}
