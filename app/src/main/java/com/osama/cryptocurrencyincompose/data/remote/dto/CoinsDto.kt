package com.osama.cryptocurrencyincompose.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.osama.cryptocurrencyincompose.domain.model.Coin

data class CoinsDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinsDto.toCoin():Coin{
    return Coin(
        id, isActive, name, rank, symbol
    )
}