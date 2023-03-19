package com.manishsputnikcorporation.safebites.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishsputnikcorporation.safebites.domain.error.SafeFlowUseCaseDelegate
import com.manishsputnikcorporation.safebites.domain.model.ProductModel
import com.manishsputnikcorporation.safebites.domain.usecase.home.LoadProductsUseCase
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.HomeUiState.*
import com.manishsputnikcorporation.safebites.utils.extensions.onFailure
import com.manishsputnikcorporation.safebites.utils.extensions.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val loadProductsUseCase: LoadProductsUseCase,
    private val safeFlowUseCase: SafeFlowUseCaseDelegate
) : ViewModel(), SafeFlowUseCaseDelegate by safeFlowUseCase {

  private val _productsHomeUiState = MutableStateFlow<HomeUiState>(Idle)
  val productsHomeUiState: StateFlow<HomeUiState> = _productsHomeUiState

  private val _event: Channel<Event> = Channel()
  val event: Flow<Event> = _event.receiveAsFlow()

  fun loadData() {
    loadProductsUseCase
        .safePrepare(Unit)
        .onStart { _productsHomeUiState.update { Loading } }
        .onEach {
          it.onSuccess { products -> _productsHomeUiState.update { Data(products) } }
              .onFailure { error -> _event.send(Event.Error(error.message)) }
        }
        .launchIn(viewModelScope)
  }

  // region State & Events
  sealed interface HomeUiState {
    object Idle : HomeUiState
    object Loading : HomeUiState
    data class Data(val products: List<ProductModel>) : HomeUiState
  }

  sealed interface Event {
    data class Error(val errorMessage: String) : Event
  }
  // endregion
}
