package com.ramesh.core.data.respository

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiServices
) : CategoryRepository {
    override fun getCategoriesApiCall(): Flow<ProductResponse> {
        return flow {
            emit(apiService.getProducts())
        }
    }

    override fun getCategoryByIDApiCall(id: Int): Flow<Product> {
        return flow {
            emit(apiService.getProductById(id))
        }
    }

    override suspend fun searchCategoryApiCall(query: String): Flow<ProductResponse> {
        TODO("Not yet implemented")
    }


}



