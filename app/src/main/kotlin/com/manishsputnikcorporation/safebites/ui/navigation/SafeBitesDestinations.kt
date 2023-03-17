package com.manishsputnikcorporation.safebites.ui.navigation

/** Contract for information needed on every SafeBites navigation destination */
// TODO: to rename to AppScreens?
interface SafeBitesDestinations {
  val route: String
}

/** SafeBites app navigation destinations */
object SplashScreen : SafeBitesDestinations {
  override val route: String = "splash_screen"
}

object Home : SafeBitesDestinations {
  override val route: String = "home"
}
