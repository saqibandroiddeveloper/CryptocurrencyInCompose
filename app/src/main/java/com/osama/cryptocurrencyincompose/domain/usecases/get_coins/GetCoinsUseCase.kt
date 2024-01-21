package com.osama.cryptocurrencyincompose.domain.usecases.get_coins

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.osama.cryptocurrencyincompose.data.remote.dto.toCoin
import com.osama.cryptocurrencyincompose.data.utils.Resource
import com.osama.cryptocurrencyincompose.domain.model.Coin
import com.osama.cryptocurrencyincompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = coinRepository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Error"))
        }

    }
}