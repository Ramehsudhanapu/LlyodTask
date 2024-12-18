package com.ramesh.assessment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramesh.core.domain.usecase.GetCategoriesUseCase
import com.ramesh.core.domain.usecase.SearchCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ramesh.core.data.UiState
import com.ramesh.core.data.model.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val searchCategoryUseCase: SearchCategoryUseCase
) : ViewModel() {

    private val _UiStateCategory: MutableStateFlow<UiState<ProductResponse>> =
        MutableStateFlow(UiState.Loading)

    val UiStateCategory: StateFlow<UiState<ProductResponse>> = _UiStateCategory
    private val _query = MutableStateFlow("")
    private val query: StateFlow<String> get() = _query

    // get the category data from the api
    fun getCategoryApiCall() {
        getCategoriesUseCase.execute(Unit).onEach { catergory ->
            _UiStateCategory.value = UiState.Success(catergory)
        }.catch { e ->
            _UiStateCategory.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)

    }

    // search the category data from the api
    fun searchCategoryApiCall(query: String) {
        _query.value = query
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    searchCategoryUseCase.execute(_query.value)
                        .catch {
                            _UiStateCategory.value = UiState.Error(it.message.toString())
                        }
                        .collect { product ->
                            _UiStateCategory.value = UiState.Success(product)
                        }
                } catch (e: Exception) {
                    _UiStateCategory.value = UiState.Error(e.message.toString())
                }
            }
        }
    }

}



