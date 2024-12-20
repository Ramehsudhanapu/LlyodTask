package com.ramesh.core.data.respository

import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.model.Product
import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiServices) :
    CategoryRepository {
    override fun getProductByCategoriesApiCall(categoryName:String): Flow<List<ProductResponse> > = flow {
        try {
            val response = apiService.getProductsByCategory(categoryName)
            emit( response)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception("Unable to fetch products. Please try again later.")
        }
    }

    override fun getSubCategoryByProductIDApiCall(id: Int): Flow<Product> = flow {
        try {
            val product = apiService.getProductById(id)
            emit(product)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception("Unable to fetch product. Please try again later.")
        }
    }

    override fun getAllCategoriesApiCall(): Flow<List<String>> = flow {
        try {
            val categories = apiService.getCategories()
            emit(categories)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception("Unable to fetch categories. Please try again later.")
        }
    }
}