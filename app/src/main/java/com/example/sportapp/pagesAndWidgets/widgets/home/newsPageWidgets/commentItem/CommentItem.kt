package com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets.commentItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity

@Composable
fun CommentItem(comment: CommentEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Аватар
        val painter = rememberAsyncImagePainter(comment.user.pictureURL)

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Контент комментария
        Column(modifier = Modifier.weight(1f)) {
            Row {
                Text(text = comment.user.email)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = comment.commentDateTime)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.commentText)
        }
    }
}

