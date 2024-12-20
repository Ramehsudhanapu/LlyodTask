package com.ramesh.core.data.repository.category

import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.data.network.ApiServices
import com.ramesh.core.data.respository.CategoryRepositoryImpl
import com.ramesh.core.util.UtilTests
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

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
    fun `getProductByCategoriesApiCall should return the correct data`() = runTest {
        // Given
        val expectedResult = listOf(UtilTests.dummyProductResponse)
        val categoryName = "test"
        whenever(apiServices.getProductsByCategory(categoryName)).thenReturn(expectedResult)

        // When
        val actualResult = categoryRepositoryImpl.getProductByCategoriesApiCall(categoryName).first()

        // Then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `getProductByCategoriesApiCall should return error flow when exceptions occur`() = runTest {
        // Given
        val expectedException = IOException("Please try again later.")
        val categoryName = "jewelery"
        given(apiServices.getProductsByCategory(categoryName)).willThrow(expectedException)

        // When
        val actualResult = runCatching { categoryRepositoryImpl.getProductByCategoriesApiCall(categoryName).first() }

        // Then
        assertTrue(actualResult.isFailure)
        Assert.assertEquals(expectedException.message, actualResult.exceptionOrNull()?.message)
    }

//    @Test
//    fun `getSubCategoryByProductIDApiCall should return the correct data`() = runTest {
//        // Given
//        val expectedResult = UtilTests.dummyProduct
//        val productId = 1
//        whenever(apiServices.getProductById(productId)).thenReturn(expectedResult)
//
//        // When
//        val actualResult = categoryRepositoryImpl.getSubCategoryByProductIDApiCall(productId).first()
//
//        // Then
//        assertEquals(expectedResult, actualResult)
//    }

    @Test
    fun `getSubCategoryByProductIDApiCall should return error flow when exception occur`() = runTest {
        // Given
        val expectedException = IOException("An error Occurred")
        val productId = 1
        given(apiServices.getProductById(productId)).willThrow(expectedException)

        // When
        val actualResult = runCatching {
            categoryRepositoryImpl.getSubCategoryByProductIDApiCall(productId).first()
        }

        // Then
        assertTrue(actualResult.isFailure)
        Assert.assertEquals(expectedException.message, actualResult.exceptionOrNull()?.message)
    }
    @Test
    fun `getProductsByCategory should return error flow when http exception occur`() = runTest {
        // Given
        val expectedException = HttpException(Response.error<List<ProductResponse>>(404, okhttp3.ResponseBody.create(null, "")))
        val categoryName = "jewelery"
        given(apiServices.getProductsByCategory(categoryName)).willThrow(expectedException)

        // When
        val actualResult = runCatching { categoryRepositoryImpl.getProductByCategoriesApiCall(categoryName).first() }

        // Then
        assertTrue(actualResult.isFailure)
        Assert.assertEquals(expectedException, actualResult.exceptionOrNull()?.cause)
    }
    @Test
    fun `getProductById should return error flow when http exception occur`() = runTest {
        // Given
        val expectedException = HttpException(Response.error<ProductResponse>(404, okhttp3.ResponseBody.create(null, "")))
        val productId = 1
        given(apiServices.getProductById(productId)).willThrow(expectedException)

        // When
        val actualResult = runCatching { categoryRepositoryImpl.getSubCategoryByProductIDApiCall(productId).first() }

        // Then
        assertTrue(actualResult.isFailure)
        Assert.assertEquals(expectedException, actualResult.exceptionOrNull()?.cause)
    }
}