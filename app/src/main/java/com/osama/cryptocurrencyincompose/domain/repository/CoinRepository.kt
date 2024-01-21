package com.osama.cryptocurrencyincompose.domain.repository

import com.osama.cryptocurrencyincompose.data.remote.dto.CoinDetailDto
import com.osama.cryptocurrencyincompose.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins():List<CoinsDto>

    suspend fun getCoinById(coinId:String):CoinDetailDto

}