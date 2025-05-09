package com.example.sportapp.widgets.matches.matchesList.matchesCard

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.soccer.api.domain.MatchEntity
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.DatePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.ImagePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.ScorePart
import com.example.sportapp.widgets.matches.matchesList.matchesCard.cardParts.TextPart
import java.time.ZonedDateTime

@Composable
fun MatchCard(
    logoURLA: String?,
    logoURLB: String?,
    item: MatchEntity,
    //контроллер страниц матчи-информация про матчи (для перехода к информации про выбранный матч)
    matchesMatchInfoNavController: NavHostController,
    pageNumber: Int
) {

    val currentTime = ZonedDateTime.now()

    Box(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),

            onClick = {
                val matchId = item.id
                Log.d("ttt", "pageeee  $pageNumber" )
                matchesMatchInfoNavController.navigate("matchInfo/$matchId/$pageNumber")
            }

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextPart(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(), name = item.teamAShortName
                )
                ImagePart(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(), url = logoURLA
                )

                if (item.localDateTimeMatchStart.isBefore(currentTime) || item.localDateTimeMatchStart.isEqual(currentTime)) {
                    ScorePart(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(), item
                    )
                }
                if (item.localDateTimeMatchStart.isAfter(currentTime)) {

                    Log.d("datePart", item.date)
                    DatePart(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(), item.localDateTimeMatchStart
                    )
                }

                ImagePart(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(), url = logoURLB
                )
                TextPart(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(), name = item.teamBShortName
                )


//                if (item.matchStatus == 1 || item.matchStatus == 2)
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    when (item.matchStatus) {
//                        1 -> Image(
//                            painter = painterResource(R.drawable.live),
//                            contentDescription = "",
//                            modifier = Modifier.size(20.dp)
//                        )
//                        2 -> Image(
//                            painter = painterResource(R.drawable.arrowtoright),
//                            contentDescription = "",
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }
            }
        }
    }
}