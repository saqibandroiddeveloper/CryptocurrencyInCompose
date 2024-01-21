package com.osama.cryptocurrencyincompose.presentation.coin_details

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osama.cryptocurrencyincompose.data.utils.Constants
import com.osama.cryptocurrencyincompose.data.utils.Resource
import com.osama.cryptocurrencyincompose.domain.usecases.get_coin.GetCoinUseCase
import com.osama.cryptocurrencyincompose.presentation.coin_details.components.CoinDetailState
import com.osama.cryptocurrencyincompose.presentation.coin_list.components.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {
     private val _state = mutableStateOf(CoinDetailState())
     val state :State<CoinDetailState> = _state


    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId->
            getCoin(coinId)
        }
    }


    private fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach {result->
            when(result){
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occur"
                    )
                }
                is Resource.Loading -> {
                 _state.value = CoinDetailState(
                     isLoading = true
                 )
                }
                is Resource.Success ->{
                    _state.value = CoinDetailState(
                        coin = result.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}