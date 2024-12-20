package com.ramesh.core.data.network
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiServices {
    companion object {
        const val BASE_URL = "https://fakestoreapi.com"; //https://dummyjson.com/


    }

//    @GET("products")
//    suspend fun getProducts(): ProductResponse
//    @GET("products/{id}")
//    suspend fun getProductById(@Path("id") id: Int): Product
//
//    @GET("products/search")
//    suspend fun searchProduct(@Query("q") query: String): ProductResponse

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{categoryname}")
    suspend fun getProductsByCategory(@Path("categoryname") categoryName: String):List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}