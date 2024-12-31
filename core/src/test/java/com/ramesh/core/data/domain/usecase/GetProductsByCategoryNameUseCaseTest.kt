package com.ramesh.core.data.domain.usecase
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.domain.repository.ProductRepository
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import com.ramesh.core.util.DummyData
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
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import java.lang.RuntimeException
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetProductByCategoriesUseCaseTest {
    @Mock
    private lateinit var repository: ProductRepository
    private lateinit var getProductByCategoriesUseCase: GetProductsByCategoryNameUseCase

    @Before
    fun setUp() {
        getProductByCategoriesUseCase = GetProductsByCategoryNameUseCase(repository)
    }

    @Test
    fun `execute should return correct values`() = runTest {
        // Given
        val categoryName = "jewelery"
        val productResponse = DummyData.createDummyProductResponseItem()
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(flowOf(productResponse))

        // When
        val result = getProductByCategoriesUseCase.execute(categoryName).first()

        // Then
        assertEquals(productResponse, result)
    }

    @Test
    fun `execute should return correct values when repository returns different data`() = runTest {
        // Given
        val categoryName = "jewelery"
        val productResponse = DummyData.createDummyProductResponseItemWithOneProduct()
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(flowOf(productResponse))

        // When
        val result = getProductByCategoriesUseCase.execute(categoryName).first()

        // Then
        assertEquals(productResponse, result)
    }

    @Test
    fun `execute should return correct values when repository returns different data null`() = runTest {
        // Given
        val categoryName = "jewelery"
        val productResponse = DummyData.createDummyProductResponseItemWithOneProductNull()
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(flowOf(productResponse))

        // When
        val result = getProductByCategoriesUseCase.execute(categoryName).first()

        // Then
        assertEquals(productResponse, result)
    }

    @Test
    fun `execute should return empty list when category has no products`() = runTest {
        // Given
        val categoryName = "empty_category"
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(flowOf(emptyList()))

        // When
        val result = getProductByCategoriesUseCase.execute(categoryName).first()

        // Then
        assertTrue(result.isEmpty())
    }

    @Test
    fun `execute should return error flow when exception occurred`() = runTest {
        // Given
        val categoryName = "jewelery"
        val expectException = RuntimeException("An error occurred")
        val errorFlow: Flow<List<ProductResponse>> = flow { throw expectException }
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(errorFlow)

        // When
        val actualException = runCatching {
            getProductByCategoriesUseCase.execute(categoryName).first()
        }

        // Then
        assertTrue(actualException.isFailure)
        assertEquals(expectException, actualException.exceptionOrNull())
    }



    @Test
    fun `execute should return correct values when repository returns different data new`() = runTest {
        // Given
        val categoryName = "jewelery"
        val expectResult = listOf(
            DummyData.createDummyProductResponse(
                id = null,
                title = null,
                price = null,
                description = "Another Test Product",
                category = "Test Product2",
                image = null,
            )
        )
        given(repository.getProductByCategoriesApiCall(categoryName)).willReturn(flowOf(expectResult))

        // When
        val result = getProductByCategoriesUseCase.execute(categoryName).first()

        // Then
        assertEquals(expectResult, result)
    }
}