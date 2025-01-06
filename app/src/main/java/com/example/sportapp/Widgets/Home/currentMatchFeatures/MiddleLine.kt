package com.example.sportapp.widgets.home.currentMatchFeatures

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.domain.MatchEntity
import com.example.sportapp.ui.theme.style4
import com.example.sportapp.ui.theme.style5

@Composable
fun MiddleLine(
    nearestMatch: MatchEntity
) {

    val painterLogoA = rememberAsyncImagePainter(nearestMatch.logoUrlA)
    val painterLogoB = rememberAsyncImagePainter(nearestMatch.logoUrlB)

    val currentCardText = ""

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterLogoA,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
            )

            Text(
                text = nearestMatch.teamAAcronym,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.Center,
                style = style4
            )
        }

        Text(
            text = "4 : 0",
            modifier = Modifier.align(Alignment.CenterVertically),
            style = style5
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterLogoB,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp),
            )

            Text(
                text = nearestMatch.teamBAcronym,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(100.dp),
                style = style4
            )
        }
    }
}
