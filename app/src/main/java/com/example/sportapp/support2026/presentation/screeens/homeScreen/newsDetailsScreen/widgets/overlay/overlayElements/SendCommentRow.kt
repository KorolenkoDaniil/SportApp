package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.support2026.features.comments.presentation.CommentsViewModel
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails


//TODO сделать общую строку отправки

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SendCommentRow(
    commentsViewModel: CommentsViewModel,
    currentNews: NewsDetails,
    email: String,
) {

//    val userPhoto = rememberAsyncImagePainter(authModel.currentUser.value?.pictureURL)


    var comment by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .padding(bottom = 0.dp)
            .background(color = Color.White)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
//                Image(
//                    painter = userPhoto,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(RoundedCornerShape(20.dp))
//                )

                BasicTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .weight(1F)
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (comment.isEmpty() && !isFocused) {
                                Text(
                                    text = "Расскажи свою мысль",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                CommentSendButton(
                    commentsViewModel = commentsViewModel,
                    message = comment,
                    email = email,
                    newsId = currentNews.id,
                    onPromptClear = {
                        comment = ""
                        isFocused = false
                        focusRequester.freeFocus()
                    },
                )
            }
        }
    }
}
