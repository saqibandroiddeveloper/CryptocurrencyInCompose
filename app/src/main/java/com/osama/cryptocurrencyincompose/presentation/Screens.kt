package com.osama.cryptocurrencyincompose.presentation

sealed class Screens(val route:String) {

    object CoinListScreen:Screens("coin_list_screen")
    object CoinDetailScreen:Screens("coin_detail_screen")
}