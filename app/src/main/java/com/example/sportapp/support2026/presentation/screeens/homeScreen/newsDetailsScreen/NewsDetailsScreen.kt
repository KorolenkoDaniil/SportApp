package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.support2026.features.comments.presentation.CommentsViewModel
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsState
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsViewModel
import com.example.sportapp.support2026.features.user.domain.entity.User
import com.example.sportapp.support2026.presentation.screeens.ScreenWrapper
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.InteractiveButtons
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.TopBar
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.BottomSheet
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.CommentsOverlay
import com.example.sportapp.support2026.presentation.screeens.sharedWidgets.LoadingWidget
import com.example.sportapp.support2026.presentation.ui.CornerRadius_16dp
import com.example.sportapp.support2026.presentation.ui.theme.News_title_style

@Composable
fun NewsDetailsScreen (
    navController: NavHostController,
    newsId: Int,
    user: User?,
    isAuthenticated: Boolean,
    newsDetailsViewModel: NewsDetailsViewModel = hiltViewModel(),
    commentsViewModel: CommentsViewModel = hiltViewModel()
){

    val newsDetailsState by newsDetailsViewModel.state.collectAsState()
    val newsDetailsLoadingState by newsDetailsViewModel.loading.collectAsState()
    val overlayVisible = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {

        val email = user?.email ?: ""
        if (!newsDetailsLoadingState) {
            newsDetailsViewModel.loadDetails(newsId, email)
        }

        commentsViewModel.loadComments(
            newsId = newsId,
            email = email,
            isNextPage = false
        )
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
                        modifier = Modifier.fillMaxSize().padding(4.dp)
                    ) {
                        item {
                            Text(
                                text = "  ${newDetails.title}",
                                style = MaterialTheme.typography.News_title_style,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        item {
                            Box(
                                modifier = Modifier.height(16.dp).fillMaxWidth()
                            )
                        }

                        item {
                            Image(
                                painter = painter,
                                contentDescription = "newsImage",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(CornerRadius_16dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        item {
                            Box(
                                modifier = Modifier.height(16.dp).fillMaxWidth()
                            )
                        }

                        item {
                            Text(text = "${newDetails.text}")
                        }

                        item {
                            Box(
                                modifier = Modifier.height(16.dp).fillMaxWidth()
                            )
                        }

                        item {
                            InteractiveButtons(
                                overlay = overlayVisible,
                                currentNews = newDetails,
                                user = user,
                                newsDetailsViewModel = newsDetailsViewModel
////                                CommentsCount = TODO(),
////                                isDarkTheme = TODO()
                            )
                        }
                    }

                    BottomSheet(
                        showSheet = overlayVisible.value,
                        onDismiss = { overlayVisible.value = false }
                    ) {
                        CommentsOverlay(
                            overlayVisible = overlayVisible,
                            commentsViewModel = commentsViewModel,
                            currentNews = newDetails,
                            email =  user?.email ?: "",
                            isAuthenticated = isAuthenticated
                        )
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
