package com.manishsputnikcorporation.safebites.di

import android.content.Context
import com.manishsputnikcorporation.safebites.BuildConfig.DEBUG
import com.manishsputnikcorporation.safebites.data.remote.ProductRemoteDataSource
import com.manishsputnikcorporation.safebites.data.remote.ProductRemoteDataSourceImpl
import com.manishsputnikcorporation.safebites.di.annotations.ApiUrl
import com.manishsputnikcorporation.safebites.network.CustomServicesExecutor
import com.manishsputnikcorporation.safebites.network.NetworkManager
import com.manishsputnikcorporation.safebites.network.NetworkManagerImpl
import com.manishsputnikcorporation.safebites.network.ProductsService
import com.manishsputnikcorporation.safebites.network.utils.OPENFOODFACTS_API_BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiUrlModule {

  @Provides @Singleton @ApiUrl fun provideBaseUrl() = OPENFOODFACTS_API_BASE_URL
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  @Provides
  @Singleton
  fun provideOkHttpClient() =
      OkHttpClient.Builder()
          .apply { if (DEBUG) addInterceptor(HttpLoggingInterceptor().setLevel(BODY)) }
          .build()

  @Provides
  @Singleton
  fun provideRetrofit(@ApiUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
      Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(MoshiConverterFactory.create())
          .client(okHttpClient)
          .build()

  @Provides
  @Singleton
  fun provideProductsServices(retrofit: Retrofit): ProductsService =
      retrofit.create(ProductsService::class.java)

  @Provides
  @Singleton
  fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager =
      NetworkManagerImpl(context)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

  @Binds
  @Singleton
  abstract fun bindCustomServicesExecutor(
      customServicesExecutor: CustomServicesExecutor.Default
  ): CustomServicesExecutor

  @Binds
  @Singleton
  abstract fun bindProductRemoteDataSource(
      productRemoteDataSource: ProductRemoteDataSourceImpl
  ): ProductRemoteDataSource
}
