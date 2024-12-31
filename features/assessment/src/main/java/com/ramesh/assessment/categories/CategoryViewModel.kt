package com.ramesh.assessment.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetAllCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getAllCategoriesUseCase: GetAllCategoriesUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<String>>> = _uiState

    // Add uiStateCategory (less recommended)
    val uiStateCategory: StateFlow<UiState<List<String>>> = _uiState

    fun getAllCategories() {
        viewModelScope.launch {
            getAllCategoriesUseCase.execute(Unit).onEach { catergory ->
                _uiState.value = UiState.Success(catergory)
            }.catch { e ->
                _uiState.value = UiState.Error(e.message.toString())
            }.launchIn(viewModelScope)

        }

    }
}