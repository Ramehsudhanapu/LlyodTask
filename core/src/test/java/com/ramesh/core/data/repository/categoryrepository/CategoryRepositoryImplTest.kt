package com.ramesh.core.data.respository

import com.ramesh.core.data.datasource.remote.categoryRemoteDataSource.CategoryApiService
import com.ramesh.core.util.AppStrings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CategoryRepositoryImplTest {

    @Mock
    private lateinit var apiService: CategoryApiService

    private lateinit var repository: CategoryRepositoryImpl

    @Before
    fun setUp() {
        repository = CategoryRepositoryImpl(apiService)
    }

    @Test
    fun `getAllCategoriesApiCall should emit categories on success`() = runTest {
        // Given
        val categories = listOf("Electronics", "Clothing", "Books")
        `when`(apiService.getCategories()).thenReturn(categories)

        // When
        val result = repository.getAllCategoriesApiCall().first()

        // Then
        assertEquals(categories, result)
    }

    @Test
    fun `getAllCategoriesApiCall should throw exception on error`() = runTest {
        // Given
        val errorMessage = AppStrings.UNABLE_TO_FETCH_PRODUCTS
        `when`(apiService.getCategories()).thenThrow(RuntimeException())

        // When
        var exception: Throwable? = null
        repository.getAllCategoriesApiCall()
            .catch { e -> exception = e }
            .collect {}

        // Then
        assertTrue(exception is Exception)
        assertEquals(errorMessage, exception?.message)
    }
//    @Test
//    fun `getAllCategoriesApiCall should emit empty list when api returns empty list`() = runTest {
//        // Given
//        val emptyCategories = emptyList<String>()
//        `when`(apiService.getCategories()).thenReturn(emptyCategories)
//
//        // When
//        val result = repository.getAllCategoriesApiCall().first()
//
//        // Then
//        assertEquals(emptyCategories, result)
//    }
}