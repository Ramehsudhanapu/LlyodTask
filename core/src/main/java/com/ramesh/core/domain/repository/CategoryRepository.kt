package com.ramesh.core.domain.repository


import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository  {

    fun getProductByCategoriesApiCall(categoryName:String): Flow<List<ProductResponse>>
    fun  getSubCategoryByProductIDApiCall(id: Int): Flow<Product>
    //suspend fun searchCategoryApiCall(query: String): Flow<ProductResponse>
    fun getAllCategoriesApiCall(): Flow<List<String>>



}