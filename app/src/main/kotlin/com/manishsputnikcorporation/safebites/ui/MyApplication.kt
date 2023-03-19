package com.manishsputnikcorporation.safebites.ui

import android.app.Application
import com.manishsputnikcorporation.safebites.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }
}
