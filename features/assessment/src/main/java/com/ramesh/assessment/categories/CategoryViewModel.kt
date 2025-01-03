package com.ramesh.assessment.categories
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getAllCategoriesUseCase: GetAllCategoriesUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<String>>> = _uiState

    fun getAllCategories() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // Switch to IO thread for the network operation
                try {
                    getAllCategoriesUseCase.execute(Unit).onEach { category ->
                        withContext(Dispatchers.Main) { // Switch back to Main thread for UI updates
                            _uiState.value = UiState.Success(category)
                        }
                    }.launchIn(this)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) { // Ensure error updates happen on Main thread
                        _uiState.value = UiState.Error(e.message.toString())
                    }
                }
            }
        }
    }
}