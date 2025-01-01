package com.ramesh.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramesh.assessment.categories.CategoryViewModel
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
class CategoryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    private lateinit var viewModel: CategoryViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CategoryViewModel(getAllCategoriesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `getAllCategories should call execute on use case`() = runTest(testDispatcher) {
        // Given
        val categories = listOf("electronics", "jewelery")
        `when`(getAllCategoriesUseCase.execute(Unit)).thenReturn(flow { emit(categories) })

        // When
        viewModel.getAllCategories()
        advanceUntilIdle()

        // Then
        verify(getAllCategoriesUseCase).execute(Unit)
    }
}