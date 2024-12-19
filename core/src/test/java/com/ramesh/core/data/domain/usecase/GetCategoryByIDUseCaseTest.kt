package com.ramesh.core.data.domain.usecase

import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.domain.usecase.GetCategoryByIDUseCase
import com.ramesh.core.util.UtilTests
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoTestRule
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCategoryByIDUseCaseTest {

    @Mock
     private  lateinit var   repository:CategoryRepository
     private  lateinit var  getCategoryByIDUseCaseTest: GetCategoryByIDUseCase

     @Before
     fun setUp()
     {
         getCategoryByIDUseCaseTest= GetCategoryByIDUseCase(repository)

     }
    @Test
    fun `execute should return correct data`() = runTest {
        // Given
        val expectResult = UtilTests.dummyProduct
        val product = 1
        `when`(repository.getCategoryByIDApiCall(product)).thenReturn(flow { emit(expectResult) })

        // When
        val actualResult = getCategoryByIDUseCaseTest.execute(product).first()

        // Then
        assertEquals(expectResult, actualResult)
    }
    @Test
    fun `execute should return correct data when repository returns different data`() = runTest {
        // Given
        val expectResult = Product("Test Product2", "Another Test Product")
        val product = 2
        `when`(repository.getCategoryByIDApiCall(product)).thenReturn(flow { emit(expectResult) })

        // When
        val actualResult = getCategoryByIDUseCaseTest.execute(product).first()

        // Then
        assertEquals(expectResult, actualResult)
    }
    @Test(expected = NoSuchElementException::class)
    fun `execute should throw exception when flow is empty`() = runTest {
        // Given
        val product = 3
        `when`(repository.getCategoryByIDApiCall(product)).thenReturn(flow {  })

        // When
        getCategoryByIDUseCaseTest.execute(product).first()

        // Then
        // Expecting NoSuchElementException to be thrown

    }


}