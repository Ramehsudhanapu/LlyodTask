package com.ramesh.assessment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramesh.assessment.home.HomeViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

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
    fun `getAllProductsByCategory should emit Loading initially`() = runTest(testDispatcher) {
        // Given
        // No specific setup needed for this test

        // When
        // No action needed, we just check the initial state

        // Then
        val uiState = viewModel._uiStateCategory.value
        assertEquals(UiState.Loading, uiState)
    }
}