package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) : BaseUseCase<Unit, Flow<ProductResponse>>() {
    override fun execute(params: Unit): Flow<ProductResponse> {
        return repository.getCategoriesApiCall()
    }

}


