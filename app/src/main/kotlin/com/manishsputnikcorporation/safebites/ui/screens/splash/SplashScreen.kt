package com.manishsputnikcorporation.safebites.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manishsputnikcorporation.safebites.R
import com.manishsputnikcorporation.safebites.ui.theme.SafeBitesTheme
import com.manishsputnikcorporation.safebites.ui.utils.annotations.UiModePreviews
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigation: () -> Unit = {}) {
  LaunchedEffect(key1 = true) {
    // TODO: to implement resource load.
    delay(3000)
    onNavigation()
  }

  Splash()
}

@Composable
fun Splash() {
  Surface {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
          Image(
              painter = painterResource(id = R.drawable.ic_launcher_foreground),
              contentDescription = stringResource(id = R.string.logo_description),
              modifier =
                  Modifier.size(150.dp, 150.dp)
                      .clip(CircleShape)
                      .border(2.dp, Color.Black, CircleShape)
                      .background(Color.Gray),
              contentScale = ContentScale.Crop)
          Spacer(modifier = Modifier.height(8.dp))
          Text(
              text = stringResource(id = R.string.welcome_message),
              fontSize = 30.sp,
              fontWeight = FontWeight.Bold)
        }
  }
}

@UiModePreviews
@Composable
fun SplashScreenPreview() {
  SafeBitesTheme { Splash() }
}
