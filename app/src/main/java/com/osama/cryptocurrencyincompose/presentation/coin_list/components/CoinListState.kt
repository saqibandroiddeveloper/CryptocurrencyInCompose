package com.osama.cryptocurrencyincompose.presentation.coin_list.components

import com.osama.cryptocurrencyincompose.domain.model.Coin

data class CoinListState(
    val isLoading:Boolean = false,
    val coins:List<Coin> = emptyList(),
    val error:String = "",
    val isSearch:Boolean = false
)
