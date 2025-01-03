package com.ramesh.assessment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramesh.assessment.detail.DetailViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetProductByIDUseCase
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getProductByIdUseCase: GetProductByIDUseCase

    private lateinit var viewModel: DetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailViewModel(getProductByIdUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @Test
    fun `getProductByIdApiCall should emit Loading initially`() = runTest(testDispatcher) {
        // Given
        // No specific setup needed for this test

        // When
        // No action needed, we just check the initial state

        // Then
        val uiState = viewModel.uiStateProduct.value
        assertEquals(UiState.Loading, uiState)
    }
}