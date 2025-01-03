package com.ramesh.core.data.datasource.remote.productRemoteDataSource

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products/category/{categoryname}")
    suspend fun getProductsByCategory(@Path("categoryname") categoryName: String):List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}