package com.example.sportapp.widgets.matchInfo.matchCard.specialEventsCards.playerSubstitulation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R


@Composable
fun ColumnOfPolygons () {
    Column(
        Modifier
            .width(10.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.polygon_2),
            contentDescription = "greenPolygon",
            modifier = Modifier
                .size(7.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Image(
            painter = painterResource(R.drawable.polygon_1),
            contentDescription = "redPolygon",
            modifier = Modifier
                .size(7.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}