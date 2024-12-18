package com.ramesh.core.domain.repository

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface CategoryRepository  {

    fun getCategoriesApiCall(): Flow<ProductResponse>
    fun  getCategoryByIDApiCall(id: Int): Flow<Product>
    suspend fun searchCategoryApiCall(query: String): Flow<ProductResponse>



}