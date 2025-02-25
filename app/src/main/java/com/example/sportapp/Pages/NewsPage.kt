package com.example.sportapp.pages

import AppActivityViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.Screen
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.NewsSate
import com.example.sportapp.models.viewModels.OneNewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.shared.CommonError
import com.example.sportapp.shared.Loading
import com.example.sportapp.widgets.home.NewsCardRow

@Composable
fun NewsPage(
    appActivity: AppActivityViewModel,
    newsDateTime: String,
    newsState: NewsSate,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,

) {

    val oneNewsViewModel: OneNewsActivityViewModel = viewModel()
    oneNewsViewModel.loadOneNewsData(newsDateTime)
    val oneNewsState by oneNewsViewModel.getState().collectAsState()
    appActivity.changePageName("One News")


    when (oneNewsState) {

        is OneNewsSate.OneNewsContent -> {

            when (newsState) {

                is NewsSate.NewsContent -> {

                    val currentNews = (oneNewsState as OneNewsSate.OneNewsContent).news

                    val painterNewsImage = rememberAsyncImagePainter(currentNews.newsImage)

                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        LazyColumn(Modifier.fillMaxSize())
                        {
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
                            item {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(32.dp))
                            }
                            item {
                                Box {
                                    Text(text = currentNews.articleText)
                                }
                            }

                            item {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(32.dp))
                            }

                            item { NewsCardRow( navController, newsViewModel, newsState.news) }

                            item {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(32.dp))
                            }
                        }
                    }
                }

                is NewsSate.Error -> {
                    Log.d("tttNews", "ошибка newsPage")
                    CommonError(newsViewModel, Screen.News.route, navController)
                }

                is NewsSate.Load -> Loading()

            }
        }


        is OneNewsSate.Error -> CommonError(oneNewsViewModel, Screen.News.route, navController)

        is OneNewsSate.Load -> Loading()
    }

}