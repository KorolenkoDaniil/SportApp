package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets.overlay.overlayElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.support2026.features.comments.presentation.CommentsViewModel
import com.example.sportapp.support2026.presentation.ui.theme.red_accent_color

@Composable
fun CommentSendButton(
    commentsViewModel: CommentsViewModel,
    message: String,
    email: String,
    newsId: Int,
    enabled: Boolean = true,
    onPromptClear: () -> Unit
) {
    val canSend = enabled && message.isNotBlank() && email.isNotBlank()

    Button(
        onClick = {
            if (!canSend) return@Button
            commentsViewModel.AddNewComment(
                commentText = message.trim(),
                newsId = newsId,
                email = email
            )
            onPromptClear()
        },
        enabled = canSend,
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