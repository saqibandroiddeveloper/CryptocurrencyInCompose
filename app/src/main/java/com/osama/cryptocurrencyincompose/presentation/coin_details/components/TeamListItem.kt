package com.osama.cryptocurrencyincompose.presentation.coin_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.osama.cryptocurrencyincompose.data.remote.dto.TeamMember

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier  = Modifier
){
       Column(
           modifier = modifier,
           horizontalAlignment = Alignment.CenterHorizontally,
       ) {
          Text(
              text = teamMember.name,
              style = MaterialTheme.typography.headlineMedium
          )
           Spacer(modifier = Modifier.height(4.dp))
           Text(
               text = teamMember.position,
               style = MaterialTheme.typography.bodyMedium,
               fontStyle = FontStyle.Italic
           )
       }
}