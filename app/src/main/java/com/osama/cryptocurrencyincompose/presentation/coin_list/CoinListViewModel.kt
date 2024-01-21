package com.osama.cryptocurrencyincompose.presentation.coin_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osama.cryptocurrencyincompose.data.utils.Resource
import com.osama.cryptocurrencyincompose.domain.usecases.get_coins.GetCoinsUseCase
import com.osama.cryptocurrencyincompose.presentation.coin_list.components.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()
    private val _state = MutableStateFlow(CoinListState())


    @OptIn(FlowPreview::class)
    val state = searchText
        .debounce(1000L)
        .combine(_state) { text, state ->
            if (text.isBlank()) {
                state
            } else {
                CoinListState(coins = state.coins.filter {
                    it.doesMatchingSearchQuery(text)
                })
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value
        )

    init {
        getCoins()
    }


    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occur"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CoinListState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = CoinListState(
                        coins = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSearchClick(isSearch: Boolean) {
        _state.value = CoinListState(
            isSearch = isSearch
        )
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}