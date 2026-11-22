package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportapp.support2026.features.comments.presentation.CommentsViewModel
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements.Comments
import com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements.SendCommentRow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CommentsOverlay(
//    showBar: MutableState<Boolean>,
    overlayVisible: MutableState<Boolean>,
    commentsViewModel: CommentsViewModel,
    currentNews: NewsDetails,
    email : String,
    isAuthenticated: Boolean,
//
//    CommentsCount: MutableState<Int>
) {
    if (overlayVisible.value) {

//        showBar.value = false

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "комментарии",
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 12.dp)
                )


                Comments(
                    commentsViewModel = commentsViewModel,
                    currentNews = currentNews,
                    email = email,
                    modifier = Modifier.weight(1F)

                )

//                if (!isAuthenticated) {
                    SendCommentRow(
                        commentsViewModel = commentsViewModel,
                        currentNews = currentNews,
                        email = email
                    )
//                }
            }

        }
    }
}
