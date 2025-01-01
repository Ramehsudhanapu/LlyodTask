package com.ramesh.core.domain.repository

import kotlinx.coroutines.flow.Flow
interface CategoryRepository  {
    fun getAllCategoriesApiCall(): Flow<List<String>>

}