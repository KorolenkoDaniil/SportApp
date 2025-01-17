package com.example.sportapp.shared

import android.util.Log
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.news.domain.NewsEntity
import com.example.sportapp.ui.theme.style2

@Composable
fun NewsCard(news: NewsEntity, navController: NavHostController) {

    val painterNewsImage = rememberAsyncImagePainter(news.newsImage)

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

            onClick = {
                val newsDateTime = news.dateTime
                Log.d("ttt", "pageeee  $newsDateTime" )

                navController.navigate("news/$newsDateTime")
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
                            .clip(shape = RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                    Box(modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = news.title, style = style2, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}

