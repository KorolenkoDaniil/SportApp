package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails

@Composable
fun InteractiveButtons(
    overlay: MutableState<Boolean>,
    currentNews: NewsDetails,
//    user: UserEntity,
//    CommentsCount: MutableState<Int>,
//    isDarkTheme: Boolean

) {

    val icon_like =  R.drawable.like
    val icon_comments = R.drawable.comment
    val icon_send = R.drawable.send



//TODO не запоминается, что новость была лайкнута

//    val likeViewModel: LikeViewModel = viewModel()
//    val likeRes = if (currentNews.isLiked) R.drawable.red_heart else icon_like

    val context = LocalContext.current
    val link = "https://korolenkodaniil.github.io/deeplink-sportapp/?id=${currentNews.id}"

    val lastLikeTime = remember { mutableStateOf(0L) }
//    val likeCount = remember { mutableStateOf(currentNews.likesCount) }



    Row(verticalAlignment = Alignment.CenterVertically) {

//        Image(
//            painter = painterResource(likeRes),
//            contentDescription = null,
//            modifier = Modifier.clickable {
//                likeViewModel.toggleLike(
//                    lastLikeTime = lastLikeTime,
//                    likeCount = likeCount,
//                    currentNews = currentNews,
//                    user = user
//                )
//            }
//        )

        Spacer(Modifier.width(8.dp))
//        Text(text = likeCount.value.toString(), style = TextStyle(
//            color = MaterialTheme.colorScheme.onBackground
//        ))

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(icon_comments),
            contentDescription = null,
            Modifier.clickable { overlay.value = true }
        )

        Spacer(Modifier.width(8.dp))
//        Text(text = CommentsCount.value.toString(), style = TextStyle(
//            color = MaterialTheme.colorScheme.onBackground
//        ))

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(icon_send),
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
    }
}