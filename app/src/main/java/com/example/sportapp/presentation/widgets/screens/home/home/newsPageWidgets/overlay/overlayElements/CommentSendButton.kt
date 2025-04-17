package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.overlayElements

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.R
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.CommentsViewModel
import com.example.sportapp.ui.theme.red_accent_color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime


@SuppressLint("NewApi")
@Composable
fun CommentSendButton(commentsViewModel: CommentsViewModel, authViewModel: AuthViewModel, message: String, newsDateTime: LocalDateTime, itemList: SnapshotStateList<CommentEntity>,  CommentsCount: MutableState<Int>,
                      onPromptClear: () -> Unit){
    Button(
        onClick = {

            CoroutineScope(Dispatchers.IO).launch {
                CommentsCount.value += 1
                val newComment = commentsViewModel.putComment(
                    comment = CommentEntity(
                        newsDateTime = newsDateTime.toString(),
                        commentDateTime = LocalDateTime.now().toString(),
                        commentText = message,
                        email = authViewModel.currentUser.value!!.email,
                        user = authViewModel.currentUser.value!!
                    )
                )
                itemList.addFirst(newComment)
            }

            onPromptClear()

        },
        modifier = Modifier.size(32.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = red_accent_color,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.send_prompt),
            contentDescription = "Отправить",
            modifier = Modifier.size(24.dp)
        )
    }
}