package com.example.sportapp.presentation.widgets.screens.matches.matches.rankigs

import AppActivityViewModel
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
import androidx.compose.ui.graphics.Color
import com.example.sportapp.models.viewModels.RankingsState
import com.example.sportapp.presentation.widgets.common.shared.Loading


@Composable
fun RankingsContent(rankingsState: RankingsState, appActivity: AppActivityViewModel) {
    appActivity.changePageName("Rankings")

    val colors = arrayOf(
        Color(0xFF0000FF), // Синий
        Color(0xFFFFA500), // Оранжевый
        Color.Green, // еленый
        Color(0xFFFF0000)  // Красный
    )

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
                        if (index in 0..4)
                            RankingCard(item, index, colors[0])
                        else if (index in 5..6)
                            RankingCard(item, index, colors[1])
                        else if (index == 7)
                            RankingCard(item, index, colors[2])
                        else if (index in (rankingsState.rankings.size - 3)..rankingsState.rankings.size)
                            RankingCard(item, index, Color.Red)
                        else
                            RankingCard(item, index, Color.Transparent)
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