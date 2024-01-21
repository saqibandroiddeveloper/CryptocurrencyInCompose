package com.osama.cryptocurrencyincompose.data.repository

import com.osama.cryptocurrencyincompose.data.remote.CoinPaprikaApi
import com.osama.cryptocurrencyincompose.data.remote.dto.CoinDetailDto
import com.osama.cryptocurrencyincompose.data.remote.dto.CoinsDto
import com.osama.cryptocurrencyincompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi):CoinRepository {
    override suspend fun getCoins(): List<CoinsDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}