package com.example.sportapp.pagesAndWidgets.widgets.home

import Comments
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.news.domain.NewsListEntity
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets.NewsPageHeader

@Composable
fun NewsPageContent(
    oneNewsState: OneNewsSate,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    newsList: NewsListEntity,
    horizontalPaddings: Dp,
) {
    val currentNews = (oneNewsState as OneNewsSate.OneNewsContent).news
    val painterNewsImage = rememberAsyncImagePainter(currentNews.newsImage)

    LazyColumn {
        item {
            Box(Modifier.padding(horizontalPaddings)) {
                LazyColumn {
                    item { NewsPageHeader(currentNews.title, navController, currentNews.dateTime) }
                    item { Spacer(Modifier.height(12.dp)) }
                    item {
                        Image(
                            painter = painterNewsImage,
                            contentDescription = "newsImage",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(16.dp))
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    item { Spacer(Modifier.height(20.dp)) }
                    item { Text(text = "\t\t\t" + currentNews.articleText) }
                    item { Spacer(Modifier.height(20.dp)) }
                }
            }
        }

        item {
            NewsCardRow(navController, newsViewModel, newsList, horizontalPaddings)
            Spacer(Modifier.height(20.dp))
        }
        item {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { Comments(currentNews) }
        }
    }
}

