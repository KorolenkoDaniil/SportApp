package com.example.sportapp.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.models.youtube.domain.VideoEntity
import com.example.sportapp.ui.theme.style2


@Composable
fun VideoCard(video: VideoEntity) {
    Box(
        modifier = Modifier.padding(end = 24.dp)
    ) {
        Card(
            modifier = Modifier
                .size(height = 180.dp, width = 210.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            colors = cardColors(
                containerColor = Color.White
            ),

            ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.football),
                        contentDescription = "newsImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f),
                        contentScale = ContentScale.Fit
                    )
                    Box (modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp
                        )) {
                        Text(text = video.snippet.title, style = style2, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}
