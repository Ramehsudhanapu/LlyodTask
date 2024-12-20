package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class GetProductByIDUseCase @Inject constructor(
  private val repository: CategoryRepository) : BaseUseCase<Int, Flow<Product>>() {

  override fun execute(params: Int): Flow<Product> {
    return repository.getSubCategoryByProductIDApiCall(params)

  }





}