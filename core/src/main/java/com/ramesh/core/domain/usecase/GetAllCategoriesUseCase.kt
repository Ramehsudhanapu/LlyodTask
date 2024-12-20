package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.Categories
import com.ramesh.core.domain.repository.CategoryRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class GetAllCategoriesUseCase  @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseUseCase<Unit, Flow<List<String>>>() {
    override fun execute(params: Unit): Flow<List<String>> {
        return categoryRepository.getAllCategoriesApiCall()
    }
}