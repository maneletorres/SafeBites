package com.manishsputnikcorporation.safebites.network

import com.manishsputnikcorporation.safebites.network.response.ProductsDto
import retrofit2.Call
import retrofit2.http.GET

interface ProductsService {

  @GET("cgi/search.pl?action=process&json=true")
  fun getProducts(
  // @Path("") apiKey: String,
  // @Query("") lang: String = "en"
  ): Call<ProductsDto> // TODO:
}
