package com.example.sportapp.presentation.widgets.screens.home.home.searchUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.models.news.sports
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import kotlinx.coroutines.launch

@Composable
fun BottomSheetFilter(horizontalPaddings: Dp, openFilterOverlay: MutableState<Boolean>, newsViewModel: NewsActivityViewModel, promptState: MutableState<TextFieldValue>,) {

    val coroutineScope = rememberCoroutineScope()

    LazyColumn (Modifier.padding(vertical = 24.dp, horizontal = horizontalPaddings)) {
        item {
            Text(text = "Фильтр спорта", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
        }
        item { Spacer(Modifier.height(24.dp)) }

        items( sports.size) { index ->
            Text(sports[index], modifier = Modifier.clickable {
                openFilterOverlay.value = false
                newsViewModel.toggleIsSearched(true)
                coroutineScope.launch {
                    newsViewModel.searchNewsSuspend(1, promptState.value.text, index)
                }
            })
            Spacer(Modifier.height(16.dp))
        }
    }
}