package com.osama.cryptocurrencyincompose.domain.model

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String
) {
    fun doesMatchingSearchQuery(query:String):Boolean{
        return name.contains(query,ignoreCase = true)
    }
}
