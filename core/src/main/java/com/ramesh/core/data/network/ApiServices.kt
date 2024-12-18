package com.ramesh.core.data.network

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {
    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String
    ): ProductResponse








}