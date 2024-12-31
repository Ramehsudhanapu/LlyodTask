package com.ramesh.core.domain.usecase

import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class GetProductByIDUseCase @Inject constructor(
  private val repository: ProductRepository
 ) : BaseUseCase<Int, Flow<Product>>() {
  override fun execute(params: Int): Flow<Product> {
    return repository.getSubCategoryByProductIDApiCall(params)

  }

}