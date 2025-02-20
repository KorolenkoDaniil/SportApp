package com.example.sportapp.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.ui.theme.style16
import com.example.sportapp.ui.theme.style17
import com.example.sportapp.ui.theme.style18
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NewsCard(news: NewsEntity, navController: NavHostController) {

    val painterNewsImage = rememberAsyncImagePainter(news.newsImage)

    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val dateTime = LocalDateTime.parse(news.dateTime, formatter)

    val pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy mm:HH")

    val newsDateTime = dateTime.format(pattern)

    Box(
        modifier = Modifier.padding(end = 24.dp)
    ) {
        Card(
            modifier = Modifier
                .size(height = 212.dp, width = 240.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            colors = cardColors(
                containerColor = Color.White
            ),

            onClick = {
                navController.navigate("news/${news.dateTime}")
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Image(
                        painter = painterNewsImage,
                        contentDescription = "newsImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f)
                            .clip(shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(Modifier.height(12.dp))
                    Column (modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = news.title, style = style16, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Spacer(Modifier.height(12.dp))
                        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = news.sport, style = style17, maxLines = 2, overflow = TextOverflow.Ellipsis)
                            Text(text = newsDateTime , style = style18, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
        }
    }
}

