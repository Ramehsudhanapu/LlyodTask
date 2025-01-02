package com.ramesh.core.data.respository

import com.ramesh.core.data.datasource.remote.ApiServices
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(private val apiService: ApiServices) :
    CategoryRepository {


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