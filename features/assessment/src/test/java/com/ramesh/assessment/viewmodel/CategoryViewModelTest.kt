package com.ramesh.assessment.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramesh.assessment.categories.CategoryViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
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
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return CategoryViewModel(getAllCategoriesUseCase) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
        // Use TestViewModelStoreOwner here
        viewModel = ViewModelProvider(TestViewModelStoreOwner(), factory)[CategoryViewModel::class.java]
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @Test
    fun `getAllCategories should emit Loading initially`() = runTest(testDispatcher) {
        // Given
        // No specific setup needed for this test

        // When
        // No action needed, we just check the initial state

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(UiState.Loading, uiState)
    }
}