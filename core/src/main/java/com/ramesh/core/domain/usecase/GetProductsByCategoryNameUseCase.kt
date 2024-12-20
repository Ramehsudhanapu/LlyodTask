package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class GetProductsByCategoryNameUseCase @Inject constructor(
    private val repository: CategoryRepository
) : BaseUseCase<String, Flow<List<ProductResponse>>>() {
    override fun execute(params: String): Flow<List<ProductResponse>> {
        return repository.getProductByCategoriesApiCall(params)
    }



 }


