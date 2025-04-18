package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.comments.commentItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.comments.CommentEntity
import com.example.sportapp.R
import com.example.sportapp.models.viewModels.CommentsViewModel
import com.example.sportapp.ui.theme.elapsedTime


@Composable
fun CommentItem(comment: CommentEntity, commentsViewModel: CommentsViewModel) {


    val lastLikeTime = remember { mutableStateOf(0L) }

    val isLiked = remember { mutableStateOf(comment.isLiked) }
    val likesCount = remember { mutableStateOf(comment.likesCount) }

    val likeRes = if (isLiked.value) R.drawable.red_heart else R.drawable.like

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {

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


        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = comment.user.email)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = comment.elapsedTime, style = elapsedTime)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.commentText)
        }

        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(likeRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(12.dp)
                    .clickable {
                        commentsViewModel.toggleLike(
                            lastLikeTime = lastLikeTime,
                            isLiked,
                            user = comment.user,
                            commentId = comment.commentId,
                            comment,
                            likesCount
                        )
                    }
            )

            Text(text = likesCount.value.toString())
        }


    }
}