package com.example.sportapp.support2026.presentation.screeens.homeScreen.newsDetailsScreen.widgets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.sportapp.R
import com.example.sportapp.support2026.features.news.domain.entities.news.NewsDetails
import com.example.sportapp.support2026.features.news.presentation.NewsDetailsViewModel
import com.example.sportapp.support2026.features.user.domain.entity.User

@Composable
fun InteractiveButtons(
    overlay: MutableState<Boolean>,
    currentNews: NewsDetails,
    user: User?,
    newsDetailsViewModel: NewsDetailsViewModel
//    CommentsCount: MutableState<Int>,
//    isDarkTheme: Boolean



) {

    val iconlike: Int =  R.drawable.like
    val iconComments = R.drawable.comment
    val iconSend = R.drawable.send

    val isLiked by newsDetailsViewModel.isLiked.collectAsState()





    val likeRes = if (isLiked) R.drawable.red_heart else iconlike

    val context = LocalContext.current
    val link = "https://korolenkodaniil.github.io/deeplink-sportapp/?id=${currentNews.id}"


//    val likeCount = remember { mutableStateOf(currentNews.likesCount) }



    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(likeRes),
            contentDescription = null,
            modifier = Modifier.clickable {
                newsDetailsViewModel.toggleLike(
//                    likeCount = likeCount,
                    currentNewsId = currentNews.id,
                    email = user?.email ?: ""
                )
            }
        )

        Spacer(Modifier.width(8.dp))
//        Text(text = likeCount.value.toString(), style = TextStyle(
//            color = MaterialTheme.colorScheme.onBackground
//        ))

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(iconComments),
            contentDescription = null,
            Modifier.clickable { overlay.value = true }
        )

        Spacer(Modifier.width(8.dp))
//        Text(text = CommentsCount.value.toString())
//            style = TextStyle(
//            color = MaterialTheme.colorScheme.onBackground
//        ))

        Spacer(Modifier.width(16.dp))
        Image(
            painter = painterResource(iconSend),
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