package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository) :
BaseUseCaseSuspend<String, Flow<ProductResponse>>()

{
    override suspend fun execute(params: String): Flow<ProductResponse> {
        return repository.searchCategoryApiCall(params)
    }


}
