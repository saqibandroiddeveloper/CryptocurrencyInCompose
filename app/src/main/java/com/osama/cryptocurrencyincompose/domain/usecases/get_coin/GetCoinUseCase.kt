package com.osama.cryptocurrencyincompose.domain.usecases.get_coin

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.osama.cryptocurrencyincompose.data.remote.dto.toCoin
import com.osama.cryptocurrencyincompose.data.remote.dto.toCoinDetails
import com.osama.cryptocurrencyincompose.data.utils.Resource
import com.osama.cryptocurrencyincompose.domain.model.Coin
import com.osama.cryptocurrencyincompose.domain.model.CoinDetail
import com.osama.cryptocurrencyincompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val coinRepository: CoinRepository) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(coinId:String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = coinRepository.getCoinById(coinId).toCoinDetails()
            emit(Resource.Success(coin))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?: "Unexpected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Error"))
        }

    }
}