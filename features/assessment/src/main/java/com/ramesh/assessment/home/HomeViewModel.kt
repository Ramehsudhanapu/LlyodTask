package com.ramesh.assessment.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.BaseViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.domain.usecase.GetProductsByCategoryNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ramesh.core.data.model.ProductResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetProductsByCategoryNameUseCase,

) : BaseViewModel() {

    val _uiStateCategory: MutableStateFlow<UiState<List<ProductResponse>>> =
        MutableStateFlow(UiState.Loading)

    // Define a CoroutineExceptionHandler
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiStateCategory.value = UiState.Error(handleException(exception))
    }
    // get the category data from the api
    fun getAllProductsByCategory(categoryName: String) {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            getCategoriesUseCase.execute(categoryName)
                .flowOn(Dispatchers.IO) // Perform network operation in IO dispatcher
                .onEach { category ->
                    // Switch to the main thread for UI updates
                    withContext(Dispatchers.Main) {
                        _uiStateCategory.value = UiState.Success(category)
                    }
                }
                .catch { exception ->
                    // Handle exceptions on the main thread
                    withContext(Dispatchers.Main) {
                        _uiStateCategory.value = UiState.Error(handleException(exception))
                    }
                }
                .launchIn(this) // Collect flow in the current coroutine
        }
    }


}






