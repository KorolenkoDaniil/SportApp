package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.support2026.features.comments.presentation.CommentsState
import com.example.sportapp.support2026.features.comments.presentation.CommentsViewModel
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements.comments.CommentItem

@Composable
fun Comments(
    commentsViewModel: CommentsViewModel,
    currentNews: NewsDetails,
    email: String,
    modifier: Modifier = Modifier
) {
    val uiState by commentsViewModel.state.collectAsState()
    val commentsList by commentsViewModel.allLoadedComments.collectAsState()
    val isLoading by commentsViewModel.loading.collectAsState()
    val hasMorePages by commentsViewModel.hasMorePages.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(listState, commentsList.size, isLoading, hasMorePages) {
        snapshotFlow {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val hasScrolled = listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0
            lastVisibleIndex to hasScrolled
        }

    }

    when {
        commentsList.isEmpty() && uiState is CommentsState.Load -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.height(50.dp))
            }
        }

        commentsList.isEmpty() && uiState is CommentsState.Error -> {
            Text(
                text = "Ошибка загрузки комментариев",
                style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center),
                modifier = modifier.fillMaxWidth()
            )
        }

        commentsList.isEmpty() -> {
            Text(
                text = "Комментариев к этой новости пока нету\n поделитесь своим мнением",
                style = TextStyle(color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center),
                modifier = modifier.fillMaxWidth()
            )
        }

        else -> {
            LazyColumn(state = listState, modifier = modifier.fillMaxWidth()) {
                items(
                    items = commentsList,
                    key = { it.commentId }
                ) { comment ->
                    CommentItem(comment)
                }

                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.height(50.dp))
                        }
                    }
                }
            }
        }
    }
}


