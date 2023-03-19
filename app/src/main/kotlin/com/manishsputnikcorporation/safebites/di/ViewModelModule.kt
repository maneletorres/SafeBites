package com.manishsputnikcorporation.safebites.di

import com.manishsputnikcorporation.safebites.domain.error.*
import com.manishsputnikcorporation.safebites.domain.usecase.base.dispatchers.DefaultDispatcherProvider
import com.manishsputnikcorporation.safebites.domain.usecase.base.dispatchers.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

  @Binds
  @ViewModelScoped
  abstract fun bindSafeFlowUseCaseDelegate(
      safeFlowUseCaseExceptionDelegate: SafeFlowUseCaseDelegate.Default
  ): SafeFlowUseCaseDelegate

  @Binds
  @ViewModelScoped
  abstract fun bindGlobalErrorManager(
      globalErrorManager: GlobalErrorManagerImpl
  ): GlobalErrorManager

  @Binds
  @ViewModelScoped
  abstract fun bindGlobalErrorMapper(globalErrorMapper: GlobalErrorMapperImpl): GlobalErrorMapper

  @Binds
  @ViewModelScoped
  abstract fun bindDispatcherProvider(
      dispatcherProvider: DefaultDispatcherProvider
  ): DispatcherProvider
}
