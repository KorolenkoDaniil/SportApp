package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.models.viewModels.AuthViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CommentsOverlay(showBar: MutableState<Boolean>, overlayVisible: MutableState<Boolean>, authModel: AuthViewModel, currentNews: NewsEntity){

    val userPhoto = rememberAsyncImagePainter(authModel.currentUser.value?.pictureURL)

    if (overlayVisible.value) {

        showBar.value = false


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .clickable { onDismiss(overlayVisible, showBar) }

        ) {
            Column (verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Text(text = "комментарии", style = TextStyle (textAlign = TextAlign.Center), modifier =  Modifier.fillMaxWidth(),)


                Comments(
                    currentNews = currentNews
                )

                Box(Modifier.height(60.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = userPhoto,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }
                }

            }
        }
    }
}

fun onDismiss(overlay: MutableState<Boolean>, showBar: MutableState<Boolean>){
    overlay.value = false
    showBar.value = true
}