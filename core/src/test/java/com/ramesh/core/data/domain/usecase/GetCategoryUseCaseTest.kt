package com.ramesh.core.data.domain.usecase

import com.ramesh.core.data.model.Product
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.data.network.NewsApiService
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.domain.usecase.GetCategoriesUseCase
import com.ramesh.core.domain.usecase.GetCategoryByIDUseCase
import com.ramesh.core.util.UtilTests
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever
import kotlin.jvm.Throws


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCategoryUseCaseTest {
    @Mock
    private  lateinit var repository: CategoryRepository
    private  lateinit var getCategoriesUseCase:GetCategoriesUseCase
    @Before
     fun setUp()
    {
         getCategoriesUseCase = GetCategoriesUseCase(repository)

     }

    @Test
     fun `execute should return correct values`()= runTest {
         // Given
         val productResponse=ProductResponse(0, mutableListOf(Product("Product","Shirt")))
        whenever(repository.getCategoriesApiCall()).thenReturn(flowOf(productResponse))
        // when
         val result=getCategoriesUseCase.execute(Unit).first()
        // then
        assertEquals(productResponse,result)

    }
    @Test

     fun `execute should return error flow when exception occurred `()= runTest {
         // Given
         val expectException=RuntimeException("An a Error occurred")
        val Flow :Flow<ProductResponse> = flow{throw  expectException}
        given(repository.getCategoriesApiCall()).willReturn(Flow)
        // when
         val actualException= runCatching {
          getCategoriesUseCase.execute(Unit).first()

         }
        assertTrue(actualException.isFailure)

        //then
         assertEquals(expectException,actualException.exceptionOrNull())







    }




}