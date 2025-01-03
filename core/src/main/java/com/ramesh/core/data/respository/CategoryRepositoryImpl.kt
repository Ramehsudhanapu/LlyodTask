package com.ramesh.core.data.respository

import com.ramesh.core.data.datasource.remote.categoryRemoteDataSource.CategoryApiService
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.util.AppStrings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(private val apiService: CategoryApiService) :
    CategoryRepository {
    override fun getAllCategoriesApiCall(): Flow<List<String>> = flow {
        try {
            val categories = apiService.getCategories()
            emit(categories)
        } catch (e: Exception) {
            // Handle errors here, e.g., emit an empty list or a specific error state
            throw Exception(AppStrings.UNABLE_TO_FETCH_PRODUCTS)
        }
    }
}