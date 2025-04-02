package com.example.sportapp.pagesAndWidgets.widgets.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.news.domain.NewsListEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate

@Composable
fun NewsPageContent(
    oneNewsState: OneNewsSate,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    newsList: NewsListEntity
) {
    val currentNews = (oneNewsState as OneNewsSate.OneNewsContent).news
    val painterNewsImage = rememberAsyncImagePainter(currentNews.newsImage)

    Image(
        painter = painterNewsImage,
        contentDescription = "newsImage",
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .height(200.dp),
        contentScale = ContentScale.Crop
    )

    Spacer(Modifier.height(32.dp))
    Text(text = currentNews.articleText)
    Spacer(Modifier.height(32.dp))
    NewsCardRow(navController, newsViewModel, newsList)
    Spacer(Modifier.height(32.dp))
}

