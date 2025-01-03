package com.ramesh.core.data.datasource.remote.categoryRemoteDataSource

import retrofit2.http.GET

interface CategoryApiService {
    @GET("products/categories")
    suspend fun getCategories(): List<String>
}