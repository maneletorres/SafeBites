package com.manishsputnikcorporation.safebites.network

import android.util.Log
import com.manishsputnikcorporation.safebites.domain.error.exception.NetworkException
import com.manishsputnikcorporation.safebites.domain.error.exception.ServiceException
import com.manishsputnikcorporation.safebites.network.utils.Either
import com.manishsputnikcorporation.safebites.network.utils.Either.*
import com.squareup.moshi.JsonDataException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import okhttp3.Headers
import okhttp3.Request
import retrofit2.Call

interface CustomServicesExecutor {

  @Throws(NetworkException::class, ServiceException::class)
  fun <T> execute(request: Call<T>): Either<CustomResponse<T>, CustomError>

  class Default @Inject constructor(private val networkManager: NetworkManager) :
      CustomServicesExecutor {

    private inline val logTag: String
      get() = javaClass.simpleName

    override fun <T> execute(request: Call<T>): Either<CustomResponse<T>, CustomError> =
        try {
          networkManager.checkInternetConnection()

          with(request.execute()) {
            if (isSuccessful) Success(CustomResponse(body(), code(), headers()))
            else {
              val raw: String = errorBody()?.string().orEmpty()
              Failure(CustomError(raw, code(), headers()))
            }
          }
        } catch (ex: JsonDataException) {
          Log.e(logTag, ex.toString()) // TODO: to review!
          throw ServiceException(ex.message ?: "", (request.request() as Request).url.toString())
        } catch (ex: SocketTimeoutException) {
          Log.e(logTag, ex.toString()) // TODO: to review!
          throw ServiceException(ex.message ?: "", (request.request() as Request).url.toString())
        } catch (ex: IOException) {
          Log.e(logTag, ex.toString()) // TODO: to review!
          throw ServiceException(ex.message ?: "", (request.request() as Request).url.toString())
        }
  }
}

data class CustomResponse<T>(val optData: T?, val statusCode: Int, val headers: Headers) {
  val data: T
    get() = optData!!
}

data class CustomError(val raw: String, val statusCode: Int, val headers: Headers)
