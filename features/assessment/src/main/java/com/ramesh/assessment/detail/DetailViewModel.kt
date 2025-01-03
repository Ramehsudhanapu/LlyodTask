
package com.ramesh.assessment.detail
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.assessment.utility.UiState
import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.usecase.GetProductByIDUseCase
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
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase:  GetProductByIDUseCase,

    ) : ViewModel() {

    private val _uiStateProduct: MutableStateFlow<UiState<Product>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct: StateFlow<UiState<Product>> = _uiStateProduct



    fun getProductByIdApiCall(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { // Perform network operation on IO dispatcher
                try {
                    getProductByIdUseCase.execute(id).onEach { product ->
                        withContext(Dispatchers.Main) { // Update UI state on Main thread
                            _uiStateProduct.value = UiState.Success(product)
                        }
                    }.launchIn(this)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) { // Handle errors on Main thread
                        _uiStateProduct.value = UiState.Error(e.message.toString())
                    }
                }
            }
        }
    }



}