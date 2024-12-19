package com.ramesh.core.data.repository.category

import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.data.respository.CategoryRepositoryImpl
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.util.UtilTests
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryImplTest {

    @Mock
    private lateinit var apiServices: ApiServices
    private lateinit var categoryRepositoryImpl: CategoryRepositoryImpl

    @Before
    fun setUp() {
        categoryRepositoryImpl = CategoryRepositoryImpl(apiServices)
    }

    @Test
    fun `getCategoriesApiCall should return the correct data`() = runTest {
        // Given
        val expectResult = UtilTests.dummyProductResponse
        whenever(apiServices.getProducts()).thenReturn(expectResult)

        // when
        val actualresult = categoryRepositoryImpl.getCategoriesApiCall().first()
        // Then
        assertEquals(expectResult, actualresult)

    }

    @Test
    fun `getCategoriesApiCall should  return error  flow when exceptions occur`() = runTest {

        // Given
        val expectException = RuntimeException("An error exist")
        given(apiServices.getProducts()).willThrow((expectException))
        // when
        val actualException = runCatching { categoryRepositoryImpl.getCategoriesApiCall().first() }
        assertTrue(actualException.isFailure)
        Assert.assertEquals(expectException, actualException.exceptionOrNull())

    }

    @Test
    fun `getCategoryByIDApiCall should return the correct data`() = runTest {
        // Given
        val expectResult = UtilTests.dummyProduct
        val productId = 1
        whenever(apiServices.getProductById(productId)).thenReturn(expectResult)

        // when
        val actuallResult = categoryRepositoryImpl.getCategoryByIDApiCall(productId).first()

        // then
        assertEquals(actuallResult, expectResult)
    }

    @Test
     fun `getCategoryByIDApiCall should return error flow when exception occur` ()= runTest {
         // Given
          val expectedException=RuntimeException("An error Occurred")
        val productId=1
         given(apiServices.getProductById(productId)).willThrow(expectedException)
         // when
            val actualException=  runCatching {
                categoryRepositoryImpl.getCategoryByIDApiCall(1).first()
            }
        // then
        assertTrue(actualException.isFailure)
        assertEquals(expectedException,actualException.exceptionOrNull())


    }

    @Test
    fun `searchCategoryApiCall should be return correct data`() = runTest {
        //Given
        val expectedResult= UtilTests.dummyProductResponse
        val  query ="Test"
        whenever(apiServices.searchProduct(query)).thenReturn(expectedResult)

        // when
        val actualResult= categoryRepositoryImpl.searchCategoryApiCall(query).first()

         // then
        assertEquals(expectedResult,actualResult)

    }

    @Test
    fun  `searchCategoryApiCall should return error flow when  exception occur `()= runTest {
        // Given
        val expectedException =RuntimeException("An error occurred")
        val query="Test"
         given(apiServices.searchProduct(query)).willThrow(expectedException)

        // when
         val actualException= runCatching {
             categoryRepositoryImpl.searchCategoryApiCall(query).first()

         }

        assertTrue(actualException.isFailure)
        assertEquals(expectedException,actualException.exceptionOrNull())


    }


}