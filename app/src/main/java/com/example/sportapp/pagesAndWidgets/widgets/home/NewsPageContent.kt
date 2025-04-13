package com.example.sportapp.pagesAndWidgets.widgets.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsListEntity
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import com.example.sportapp.models.viewModels.OneNewsSate
import com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets.InteractiveButtons
import com.example.sportapp.pagesAndWidgets.widgets.home.newsPageWidgets.NewsPageHeader
import java.time.format.DateTimeFormatter

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NewsPageContent(
    oneNewsState: OneNewsSate,
    navController: NavHostController,
    newsViewModel: NewsActivityViewModel,
    newsList: NewsListEntity,
    horizontalPaddings: Dp,
    authModel: AuthViewModel,
    showBar: MutableState<Boolean>
) {
    val currentNews = (oneNewsState as OneNewsSate.OneNewsContent).news
    val painterNewsImage = rememberAsyncImagePainter(currentNews.newsImage)
    val overlay = remember { mutableStateOf(false) }

    val format = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")
    val formattedDate = currentNews.dateTime.format(format)
    val userPhoto = rememberAsyncImagePainter(authModel.currentUser.value?.pictureURL)


    LazyColumn {
        item {

            Box(Modifier.padding(horizontal = horizontalPaddings)) {

                Column {

                    NewsPageHeader(currentNews.title, navController)

                    Spacer(Modifier.height(4.dp))

                    Text(text = formattedDate, style = TextStyle(textAlign = TextAlign.End), modifier = Modifier.fillMaxWidth())

                    Spacer(Modifier.height(2.dp))

                    Image(
                        painter = painterNewsImage,
                        contentDescription = "newsImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(20.dp))


                    Text(text = "\t\t\t" + currentNews.articleText)

                    Spacer(Modifier.height(20.dp))

                    InteractiveButtons(currentNews.dateTime, overlay)

                    Spacer(Modifier.height(20.dp))
                }
            }
        }

        item {
            NewsCardRow(navController, newsViewModel, newsList, horizontalPaddings)
            Spacer(Modifier.height(20.dp))
        }
    }

    if (overlay.value) {

        showBar.value = false


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .clickable { onDismiss(overlay, showBar) }

        ) {
            Column (verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Text(text = "комментарии", style = TextStyle (textAlign = TextAlign.Center), modifier =  Modifier.fillMaxWidth(),)

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

fun onDismiss( overlay: MutableState<Boolean>, showBar: MutableState<Boolean>){
    overlay.value = false
    showBar.value = true
}



