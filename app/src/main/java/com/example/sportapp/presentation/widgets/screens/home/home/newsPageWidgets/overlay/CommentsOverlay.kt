package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.CommentsViewModel
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.overlayElements.Comments
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.overlayElements.SendCommentRow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CommentsOverlay(
    showBar: MutableState<Boolean>,
    overlayVisible: MutableState<Boolean>,
    authModel: AuthViewModel,
    currentNews: NewsEntity,
    CommentsCount: MutableState<Int>
) {

    val commentsViewModel = CommentsViewModel()
    val page = remember { mutableStateOf(1) }


    val itemList = remember { mutableStateListOf<CommentEntity>() }




    if (overlayVisible.value) {

        showBar.value = false


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .clickable { onDismiss(overlayVisible, showBar) },
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
                    currentNews = currentNews,
                    commentsViewModel,
                    page,
                    itemList,
                    authModel.currentUser.value!!,
                    Modifier.weight(1F)
                )

                SendCommentRow(authModel, commentsViewModel ,currentNews, itemList, CommentsCount)
            }

        }
    }
}

fun onDismiss(overlay: MutableState<Boolean>, showBar: MutableState<Boolean>) {
    overlay.value = false
    showBar.value = true
}