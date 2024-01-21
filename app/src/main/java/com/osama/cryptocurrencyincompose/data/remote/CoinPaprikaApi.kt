package com.osama.cryptocurrencyincompose.data.remote

import com.osama.cryptocurrencyincompose.data.remote.dto.CoinDetailDto
import com.osama.cryptocurrencyincompose.data.remote.dto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("v1/coins")
    suspend fun getCoins(): List<CoinsDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String):CoinDetailDto

}