package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.R
import com.example.sportapp.domain.viewModels.LikeState
import com.example.sportapp.domain.viewModels.LikeViewModel

@Composable
fun InteractiveButtons(
    overlay: MutableState<Boolean>,
    currentNews: NewsEntity,
    user: UserEntity,
) {
    val likeViewModel: LikeViewModel = viewModel()

    val likeState = likeViewModel.state.collectAsState()

    LaunchedEffect(currentNews.dateTime, user.email) {
        val liked = likeViewModel.LikeExist(currentNews.dateTime, user.email)
        likeViewModel.state.value = if (liked) LikeState.Liked else LikeState.NotLiked
    }

    val likeRes = if (likeState.value == LikeState.Liked) R.drawable.red_heart else R.drawable.like



    val context = LocalContext.current
    val link = "https://korolenkodaniil.github.io/deeplink-sportapp/?id=${currentNews.dateTime}"

    val lastLikeTime = remember { mutableStateOf(0L) }
    val likeCoolDown = 2000L
    val likeCount = remember { mutableStateOf(currentNews.likesCount) }



    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(likeRes),
            contentDescription = null,
            modifier = Modifier.clickable {
                likeViewModel.toggleLike(
                    likeCoolDown,
                    lastLikeTime,
                    likeCount,
                    currentNews,
                    user,
                )
            }
        )

        Spacer(Modifier.width(8.dp))
        Text(text = likeCount.value.toString())

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.comment),
            contentDescription = null,
            Modifier.clickable { overlay.value = true }
        )

        Spacer(Modifier.width(8.dp))
        Text(text = currentNews.commentsCount.toString())

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(R.drawable.send),
            contentDescription = null,
            modifier = Modifier.clickable {
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, link)
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
        )

        Spacer(Modifier.width(8.dp))
        Text(text = "560")
    }
}