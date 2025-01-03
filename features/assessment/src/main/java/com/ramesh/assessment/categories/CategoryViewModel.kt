package com.ramesh.assessment.categories
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.BaseViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel

class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<String>>> = _uiState

    // Define a CoroutineExceptionHandler
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error(handleException(exception))
    }

    fun getAllCategories() {
        // Use exceptionHandler with launch
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            getAllCategoriesUseCase.execute(Unit)
                .flowOn(Dispatchers.IO) // Ensure flow is executed in IO dispatcher
                .onEach { category ->
                    // Switch to the main thread for UI updates
                    withContext(Dispatchers.Main) {
                        _uiState.value = UiState.Success(category)
                    }
                }
                .catch { exception ->
                    // Ensure the error handling also happens on the main thread
                    withContext(Dispatchers.Main) {
                        _uiState.value = UiState.Error(handleException(exception))
                    }
                }
                .launchIn(this) // Collect flow in the current coroutine
        }
    }



}
