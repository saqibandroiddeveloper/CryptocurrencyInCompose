package com.osama.cryptocurrencyincompose.domain.model

import com.osama.cryptocurrencyincompose.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank:Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
