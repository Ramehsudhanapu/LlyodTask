package com.ramesh.assessment.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ramesh.core.data.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetProductsByCategoryNameUseCase,

) : ViewModel() {

    val _uiStateCategory: MutableStateFlow<UiState<List<ProductResponse>>> =
        MutableStateFlow(UiState.Loading)


    // get the category data from the api
    fun getAllProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // Perform network operation in IO dispatcher
                try {
                    getCategoriesUseCase.execute(categoryName).onEach { category ->
                        withContext(Dispatchers.Main) { // Switch to Main thread for UI updates
                            _uiStateCategory.value = UiState.Success(category)
                        }
                    }.launchIn(this)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) { // Ensure error updates happen on Main thread
                        _uiStateCategory.value = UiState.Error(e.message.toString())
                    }
                }
            }
        }
    }

    }






