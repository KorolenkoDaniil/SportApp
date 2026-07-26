package com.example.sportapp.support2026.presentation.screeens.newsDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsState
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsViewModel
import com.example.sportapp.support2026.presentation.screeens.newsDetailsScreen.widgets.TopBar
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper
import com.example.sportapp.support2026.presentation.screeens.sharedWidgets.LoadingWidget
import com.example.sportapp.support2026.presentation.ui.CornerRadius_16

@Composable
fun NewsDetailsScreen (
    navController: NavHostController,
    newsId: Int,
    newsDetailsViewModel: NewsDetailsViewModel = hiltViewModel()
){

    val newsDetailsState by newsDetailsViewModel.state.collectAsState()
    val newsDetailsLoadingState by newsDetailsViewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        if (!newsDetailsLoadingState) {
            newsDetailsViewModel.loadDetails(newsId)
        }
    }

    ScreenWrapper(
        topBar = {
            TopBar(navController)
        },
        content = {

            when (val state = newsDetailsState){
                is NewsDetailsState.Load -> {
                    LoadingWidget()
                }
                is NewsDetailsState.NewsDetailsContent -> {
                    val newDetails = state.newsDetails
                    val painter = rememberAsyncImagePainter(newDetails.imageUrl)

                    LazyColumn(
                        // Добавляем weight(1f), чтобы LazyColumn занял всё оставшееся пространство в ColumnWrapper
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Text(
                                text = "Title: ${newDetails.title}",
                                style = TextStyle(
                                    textAlign = TextAlign.End,
                                    color = MaterialTheme.colorScheme.onBackground
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            Image(
                                painter = painter,
                                contentDescription = "newsImage",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(CornerRadius_16)),
                                contentScale = ContentScale.Crop
                            )
                        }



                        item {
                            Text(text = "OneNewsPage $newDetails")
                        }
                    }

                }
                is NewsDetailsState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Ошибка: ${state.e.localizedMessage}")
                    }
                }

            }
        }
    )
}

//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun NewsPageContent(
//    oneNewsState: OneNewsSate,
//    navController: NavHostController,
//    newsViewModel: NewsActivityViewModel,
//    horizontalPaddings: Dp,
//    authModel: AuthViewModel,
//    showBar: MutableState<Boolean>,
//) {
//
//    //TODO поднять дату и время выше
////    val isDarkTheme by appActivityViewModel.appTheme.collectAsState()
//
//    val isDarkTheme = authModel.themeIsWhite.collectAsState().value
//
//    val icon_arrow = if (isDarkTheme) { R.drawable.arrow_small_left_1 } else { R.drawable.w_left_arrow }
//
//
//    val currentNews = (oneNewsState as OneNewsSate.OneNewsContent).news
//    val painterNewsImage = rememberAsyncImagePainter(currentNews.newsImage)
//
//    val overlayVisible = remember { mutableStateOf(false) }
//
//    val format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
//    val formattedDate = currentNews.dateTime.format(format)
//
//    val CommentsCount = remember { mutableStateOf(currentNews.commentsCount) }
//
//    LazyColumn (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
//        item {
//            Box(Modifier.padding(horizontal = horizontalPaddings)) {
//
//                Column {
//
//                    NewsPageHeader(currentNews.title, navController, icon_arrow)
//
//                    Spacer(Modifier.height(4.dp))
//
//                    Text(
//                        text = formattedDate,
//                        style = TextStyle(textAlign = TextAlign.End, color = MaterialTheme.colorScheme.onBackground),
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    Spacer(Modifier.height(2.dp))
//
//                    Image(
//                        painter = painterNewsImage,
//                        contentDescription = "newsImage",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(RoundedCornerShape(16.dp))
//                            .height(200.dp),
//                        contentScale = ContentScale.Crop
//                    )
//
//                    Spacer(Modifier.height(20.dp))
//
//                    Text(text = "\t\t\t" + currentNews.articleText, style = TextStyle(
//                        color = MaterialTheme.colorScheme.onBackground
//                    ))
//
//                    Spacer(Modifier.height(20.dp))
//
//                    val user = authModel.currentUser.collectAsState().value ?: return@Box
//
//                    InteractiveButtons(overlayVisible, currentNews, user, CommentsCount, isDarkTheme)
//
//                    Spacer(Modifier.height(20.dp))
//                }
//            }
//        }
//
//        item {
//            NewsCardRow(navController, newsViewModel, horizontalPaddings)
//            Spacer(Modifier.height(20.dp))
//        }
//    }
//
//
//    BottomSheet(
//        showSheet = overlayVisible.value,
//        onDismiss = { overlayVisible.value = false }
//    ) {
//        CommentsOverlay(showBar, overlayVisible, authModel, currentNews, CommentsCount)
//    }
//
//
//
//
//}
