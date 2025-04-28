package com.example.sportapp.presentation.widgets.screens.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.presentation.widgets.common.shared.SearchLine
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.BottomSheet
import com.example.sportapp.presentation.widgets.screens.home.home.searchUI.BottomSheetFilter
import com.example.sportapp.presentation.widgets.screens.home.home.searchUI.SearchedNEwsList
import com.example.sportapp.ui.theme.style1
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomePageContent(
    user: StateFlow<UserEntity?>,
    viewModels: ViewModelContainer,
    navController: NavHostController,
    horizontalPaddings: Dp,
) {
    val openFilterOverlay = remember { mutableStateOf(false) }
    val promptState = remember { mutableStateOf(TextFieldValue("")) }

    val isFocused = remember { mutableStateOf(false) }
    val itemList = remember { mutableStateListOf<NewsEntity>() }
    val page = remember { mutableStateOf(1) }
    val loading = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Column {

        SearchLine(
            user,
            viewModels.authViewModel,
            navController,
            horizontalPaddings,
            viewModels.newsViewModel,
            promptState,
            openFilterOverlay,
            isFocused,
            itemList
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            if (!isFocused.value) {

                item { CurrentMatch(viewModels.matchesViewModel.nearestMatch, horizontalPaddings) }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Text(
                        text = "Sport news",
                        style = style1,
                        modifier = Modifier.padding(horizontal = horizontalPaddings)
                    )
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item { NewsCardRow(navController, viewModels.newsViewModel, horizontalPaddings) }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    Text(
                        text = "Recommended videos",
                        style = style1,
                        modifier = Modifier.padding(horizontal = horizontalPaddings)
                    )
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item {
                    VideoCardRow(
                        videoViewModel = viewModels.videoViewModel,
                        navController = navController,
                        horizontalPaddings
                    )
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }

        if (isFocused.value) {
            SearchedNEwsList(
                newsViewModel = viewModels.newsViewModel,
                searchPrompt = promptState.value.text,
                navController,
                itemList,
                page,
                loading,
                listState
            )
        }

        if (isFocused.value && openFilterOverlay.value)
        BottomSheet(
            showSheet = openFilterOverlay.value,
            onDismiss = { openFilterOverlay.value = false }
        ) {
            BottomSheetFilter(
                horizontalPaddings = horizontalPaddings,
                openFilterOverlay = openFilterOverlay,
                newsViewModel = viewModels.newsViewModel,
                promptState = promptState,
                itemList,
                page,
                loading,
            )
        }
    }
}


