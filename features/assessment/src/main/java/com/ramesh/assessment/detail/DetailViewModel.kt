
package com.ramesh.assessment.detail
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramesh.core.data.UiState
import com.ramesh.core.data.model.Product
import com.ramesh.core.domain.usecase.GetProductByIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase:  GetProductByIDUseCase,

    ) : ViewModel() {

    private val _uiStateProduct: MutableStateFlow<UiState<Product>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct: StateFlow<UiState<Product>> = _uiStateProduct



    fun getProductByIdApiCall(id: Int) {
        getProductByIdUseCase.execute(id).onEach {
            _uiStateProduct.value = UiState.Success(it)
        }.catch { e ->
            _uiStateProduct.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }



}