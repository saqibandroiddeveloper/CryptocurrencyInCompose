package com.osama.cryptocurrencyincompose.presentation.coin_details.components

import com.osama.cryptocurrencyincompose.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading:Boolean = false,
    val coin:CoinDetail? = null,
    val error:String = ""
)
