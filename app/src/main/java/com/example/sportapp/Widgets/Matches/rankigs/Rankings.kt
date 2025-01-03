package com.example.sportapp.widgets.matches.rankigs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportapp.api.viewModels.RankingsState
import com.example.sportapp.shared.Loading


@Composable
fun RankingsContent(rankingsState: RankingsState) {

    Column {

        //строка шапки страницы
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // team
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Team")
            }

            //подстрока шапки таблицы
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Pts")
                Text(text = "P")
                Text(text = "W")
                Text(text = "D")
                Text(text = "L")
                Text(text = "GD")
            }
        }


        when (rankingsState) {


            is RankingsState.RankingsContent -> {
                LazyColumn {
                    itemsIndexed(rankingsState.rankings) { index, item ->
                        RankingCard(item, index)
                    }
                }
            }

            RankingsState.Load -> {
                Loading()
            }

            is RankingsState.Error -> {
                Text("Error loading rankings")
            }
        }
    }
}