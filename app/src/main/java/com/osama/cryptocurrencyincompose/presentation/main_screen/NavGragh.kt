package com.osama.cryptocurrencyincompose.presentation.main_screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.osama.cryptocurrencyincompose.presentation.Screens
import com.osama.cryptocurrencyincompose.presentation.coin_details.CoinDetailScreen
import com.osama.cryptocurrencyincompose.presentation.coin_list.CoinListScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Screens.CoinListScreen.route){
        composable(Screens.CoinListScreen.route){
            CoinListScreen(navController = navHostController)
        }
        composable(Screens.CoinDetailScreen.route + "/{coinId}"){
            CoinDetailScreen()
        }
    }
}