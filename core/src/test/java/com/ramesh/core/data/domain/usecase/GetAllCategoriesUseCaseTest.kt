package com.ramesh.core.data.domain.usecase
import com.ramesh.core.domain.repository.CategoryRepository
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetAllCategoriesUseCaseTest {

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    private lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Before
    fun setUp() {
        getAllCategoriesUseCase = GetAllCategoriesUseCase(categoryRepository)
    }

    @Test
    fun `execute should return categories from repository`() = runTest {
        // Given
        val expectedCategories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
        `when`(categoryRepository.getAllCategoriesApiCall()).thenReturn(flow { emit(expectedCategories) })

        // When
        val result: List<List<String>> = getAllCategoriesUseCase.execute(Unit).toList()

        // Then
        assertEquals(listOf(expectedCategories), result)
    }

    @Test
    fun `execute should return empty list when repository returns empty list`() = runTest {
        // Given
        val expectedCategories = emptyList<String>()
        `when`(categoryRepository.getAllCategoriesApiCall()).thenReturn(flow { emit(expectedCategories) })

        // When
        val result: List<List<String>> = getAllCategoriesUseCase.execute(Unit).toList()

        // Then
        assertEquals(listOf(expectedCategories), result)
    }

    @Test
    fun `execute should throw exception when repository throws exception`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        `when`(categoryRepository.getAllCategoriesApiCall()).thenReturn(flow { throw Exception(errorMessage) })

        // When
        try {
            getAllCategoriesUseCase.execute(Unit).toList()
        } catch (e: Exception) {
            // Then
            assertEquals(errorMessage, e.message)
        }
    }
}