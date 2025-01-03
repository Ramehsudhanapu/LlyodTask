
package com.ramesh.assessment.detail
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.BaseViewModel
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.usecase.GetProductByIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase:  GetProductByIDUseCase,

    ) : BaseViewModel() {

    private val _uiStateProduct: MutableStateFlow<UiState<Product>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct: StateFlow<UiState<Product>> = _uiStateProduct

    // Define a CoroutineExceptionHandler
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiStateProduct.value = UiState.Error(handleException(exception))
    }

    fun getProductByIdApiCall(id: Int) {
        viewModelScope.launch {
            withContext  ( exceptionHandler+Dispatchers.IO) { // Perform network operation on IO dispatcher
                try {
                    getProductByIdUseCase.execute(id).onEach { product ->
                        withContext(Dispatchers.Main) { // Update UI state on Main thread
                            _uiStateProduct.value = UiState.Success(product)
                        }
                    }
                        .catch { exception ->
                            // Handle exceptions on the main thread
                            withContext(Dispatchers.Main) {
                                _uiStateProduct.value = UiState.Error(handleException(exception))
                            }
                        }
                        .launchIn(this)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) { // Handle errors on Main thread
                        _uiStateProduct.value = UiState.Error(e.message.toString())
                    }
                }
            }
        }
    }



}