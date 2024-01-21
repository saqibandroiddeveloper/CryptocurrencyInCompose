package com.osama.cryptocurrencyincompose.presentation.coin_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.osama.cryptocurrencyincompose.presentation.Screens
import com.osama.cryptocurrencyincompose.presentation.coin_list.components.CoinListItem
import com.osama.cryptocurrencyincompose.presentation.components.Toolbar

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Toolbar(title = "All Cryptocurrency", isSearch = true) {
                viewModel.onSearchClick(
                    isSearch = !state.isSearch
                )
            }
//            AnimatedVisibility(
//                visible = state.isSearch,
//                enter = fadeIn() + slideInVertically(),
//                exit = fadeOut() + slideOutVertically()
//            ) {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                placeholder = { Text(text = "Search") },
                modifier = Modifier.fillMaxWidth()
            )
//            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = {
                            navController.navigate(Screens.CoinDetailScreen.route + "/${coin.id}")
                        }
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}