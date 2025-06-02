package com.example.sportapp.presentation.widgets.screens.home.home.searchUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.domain.models.news.sports
import com.example.sportapp.models.viewModels.NewsActivityViewModel

@Composable
fun BottomSheetFilter(
    horizontalPaddings: Dp,
    openFilterOverlay: MutableState<Boolean>,
    newsViewModel: NewsActivityViewModel,
    promptState: MutableState<TextFieldValue>,
    itemList: SnapshotStateList<NewsEntity>,
    loading: MutableState<Boolean>,
) {

    LazyColumn(Modifier.padding(vertical = 24.dp, horizontal = horizontalPaddings)) {

        item { Text(text = "ФИЛЬТР СПОРТА", Modifier.fillMaxWidth(), textAlign = TextAlign.Center) }

        item { Spacer(Modifier.height(24.dp)) }

        item {
            Text(
                text = "Убрать фильтр",
                Modifier.clickable {
                    openFilterOverlay.value = false
                    newsViewModel.sportIndex = -1
                    newsViewModel.searchAndSetNews(
                        1,
                        promptState.value.text,
                        newsViewModel.sportIndex,
                        itemList,
                        true,
                    )
                },
                style = TextStyle(color = Color.Gray, fontSize = 16.sp),
            )
        }

        item { Spacer(Modifier.height(16.dp)) }

        items(sports.size) { index ->

            Text(sports[index], modifier = Modifier.clickable {
                loading.value = true
                openFilterOverlay.value = false
                newsViewModel.sportIndex = index
                newsViewModel.searchAndSetNews(1, promptState.value.text, index, itemList, true)
                loading.value = false
            }, style = TextStyle(color = MaterialTheme.colorScheme.onBackground))

            Spacer(Modifier.height(16.dp))
        }
    }
}