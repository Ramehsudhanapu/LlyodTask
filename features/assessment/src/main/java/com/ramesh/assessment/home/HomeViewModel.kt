package com.ramesh.assessment.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ramesh.core.data.model.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetProductsByCategoryNameUseCase,

) : ViewModel() {

    val _uiStateCategory: MutableStateFlow<UiState<List<ProductResponse>>> =
        MutableStateFlow(UiState.Loading)

    val UiStateCategory: StateFlow<UiState<List<ProductResponse>>> = _uiStateCategory
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    // get the category data from the api
    fun getAllProductsByCategory(categoryName: String) {
        getCategoriesUseCase.execute(categoryName).onEach { catergory ->
            _uiStateCategory.value = UiState.Success(catergory)
        }.catch { e ->
            _uiStateCategory.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)

    }

    }






