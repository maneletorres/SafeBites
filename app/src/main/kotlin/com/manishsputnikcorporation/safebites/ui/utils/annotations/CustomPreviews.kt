package com.manishsputnikcorporation.safebites.ui.utils.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class UiModePreviews
