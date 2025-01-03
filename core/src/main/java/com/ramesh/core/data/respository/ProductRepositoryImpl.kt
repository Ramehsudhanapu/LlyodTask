package com.ramesh.core.data.respository

import com.ramesh.core.data.datasource.remote.productRemoteDataSource.ProductApiService
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.repository.ProductRepository
import com.ramesh.core.util.AppStrings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(private val apiService: ProductApiService) :
    ProductRepository {
    override fun getProductByCategoriesApiCall(categoryName:String): Flow<List<ProductResponse> > = flow {
        try {
            val response = apiService.getProductsByCategory(categoryName)
            emit( response)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception(AppStrings.UNABLE_TO_FETCH_PRODUCTS)
        }
    }
    override fun getSubCategoryByProductIDApiCall(id: Int): Flow<Product> = flow {
        try {
            val product = apiService.getProductById(id)
            emit(product)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception(AppStrings.UNABLE_TO_FETCH_PRODUCTS)
        }
    }

}