package com.manishsputnikcorporation.safebites.ui.screens.home

import androidx.lifecycle.ViewModel
import com.manishsputnikcorporation.safebites.ui.screens.home.HomeViewModel.HomeUiState.*
import com.manishsputnikcorporation.safebites.ui.utils.fake.fakeProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _productsHomeUiState = MutableStateFlow<HomeUiState>(Idle)
    val productsHomeUiState: StateFlow<HomeUiState> = _productsHomeUiState

    private val _event: Channel<Event> = Channel()
    val event: Flow<Event> = _event.receiveAsFlow()

    fun loadData() {
        with (_productsHomeUiState) {
            update { Loading }
            update { Data(fakeProducts) }
        }
    }

    sealed interface HomeUiState {
        object Idle : HomeUiState
        object Loading : HomeUiState
        data class Data(val products: List<String>) : HomeUiState
    }

    sealed interface Event {
        data class Error(val errorMessage: String) : Event
    }
}