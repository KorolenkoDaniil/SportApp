package com.example.sportapp.pages.widgets.shared

import android.util.Log
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
import com.example.sportapp.ui.theme.style15
import com.example.sportapp.ui.theme.style16
import com.example.sportapp.ui.theme.style2
import java.time.format.DateTimeFormatter

@Composable
fun NewsCard(news: NewsEntity, navController: NavHostController) {

    val painterNewsImage = rememberAsyncImagePainter(news.newsImage)


    val format = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")
    val formattedDate = news.dateTime.format(format)

    Box(
        modifier = Modifier.padding(end = 24.dp)
    ) {
        Card(
            modifier = Modifier
                .size(height = 216.dp, width = 240.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
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
                        Text(text = news.title, style = style2, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = news.sport, style = style15, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = formattedDate, style = style16, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}