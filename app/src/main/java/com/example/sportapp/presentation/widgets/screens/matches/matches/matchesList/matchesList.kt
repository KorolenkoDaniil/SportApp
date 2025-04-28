package com.example.sportapp.presentation.widgets.screens.matches.matches.matchesList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.match.MatchDayEntity
import com.example.sportapp.CleanArchitexture.domain.models.match.RankingEntity
import com.example.sportapp.presentation.widgets.screens.matches.matches.matchesList.matchesCard.MatchCard


//функция, коуторая отображет список матчей
@Composable
fun MatchesList(
    //состояние горизонтальбной прокрутки
    pageState: PagerState,
    //список матч дэев
    matchDays: List<MatchDayEntity>,
    //список ранкингов
    rankings: List<RankingEntity>,
    //контроллер страниц матчи-информация про матчи
    matchesMatchInfoNavController: NavHostController,
    horizontalPaddings: Dp
) {

    //ассоциацитивный список ранкингов по имени
    val logoUrlMap = rankings.associateBy { it.name }

    HorizontalPager(

        //состояние горизонтального списка
        state = pageState,
        modifier = Modifier.fillMaxSize()

    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = horizontalPaddings),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {

            //список вертикальный матчей
            LazyColumn {
                itemsIndexed(matchDays[pageState.currentPage].matches) { _, item ->

                    val teamALogoUrl = logoUrlMap[item.teamAAcronym]?.logoUrl
                    val teamBLogoUrl = logoUrlMap[item.teamBAcronym]?.logoUrl

                    //карточка 1 матча
                    MatchCard(
                        teamALogoUrl,
                        teamBLogoUrl,
                        item,
                        matchesMatchInfoNavController,
                        page
                    )
                }
            }
        }
    }
}