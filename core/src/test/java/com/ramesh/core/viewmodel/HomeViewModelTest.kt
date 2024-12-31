package com.ramesh.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramesh.assessment.home.HomeViewModel
import com.ramesh.core.data.model.ProductResponse
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getProductsByCategoryNameUseCase: GetProductsByCategoryNameUseCase

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getProductsByCategoryNameUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllProductsByCategory should call execute on use case with correct category name`() = runTest(testDispatcher) {
        // Given
        val categoryName = "electronics"
        val products = listOf(
            ProductResponse("1", "Product 1", 10, "Description 1", 10.33, "electronics"),
            ProductResponse("2", "Product 2", 20, "Description 2", 11.33, "electronics")
        )
        `when`(getProductsByCategoryNameUseCase.execute(categoryName)).thenReturn(flow { emit(products) })

        // When
        viewModel.getAllProductsByCategory(categoryName)
        advanceUntilIdle()

        // Then
        verify(getProductsByCategoryNameUseCase).execute(categoryName)
    }
}