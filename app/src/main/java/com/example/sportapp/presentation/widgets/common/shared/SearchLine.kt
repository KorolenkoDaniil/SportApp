package com.example.sportapp.presentation.widgets.common.shared

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.CleanArchitexture.domain.models.news.NewsEntity
import com.example.sportapp.CleanArchitexture.domain.models.user.UserEntity
import com.example.sportapp.R
import com.example.sportapp.models.viewModels.AuthViewModel
import com.example.sportapp.models.viewModels.NewsActivityViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchLine(
    user: StateFlow<UserEntity?>,
    authViewModel: AuthViewModel,
    navController: NavHostController,
    horizontalPaddings: Dp,
    newsViewModel: NewsActivityViewModel,
    promptState: MutableState<TextFieldValue>,
    openFilterOverlay: MutableState<Boolean>,
    isFocused: MutableState<Boolean>,
    itemList: SnapshotStateList<NewsEntity>
) {
    val focusRequester = remember { FocusRequester() }


    var previousText by remember { mutableStateOf("") }

    var isFirstLaunch by remember { mutableStateOf(true) }

    val lastTextChangeTime = remember { mutableStateOf(0L) }

    val iconRadius = if (isFocused.value ) 0.dp else 24.dp
    val iconSize = if (isFocused.value  ) 24.dp else 48.dp


    LaunchedEffect(promptState.value.text) {
        if (isFirstLaunch) {
            isFirstLaunch = false
            return@LaunchedEffect
        }

        delay(500)

        if (promptState.value.text != previousText) {
            Log.d("search", promptState.value.text)
            previousText = promptState.value.text
            if (promptState.value.text.isNotBlank()) {
                newsViewModel.toggleIsSearched(true)
                newsViewModel.searchNewsSuspend(1, promptState.value.text, -1)
            }
        }
    }


    Row(
        Modifier.padding(horizontal = horizontalPaddings),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            Modifier
                .height(40.dp)
                .weight(0.7F)
                .clip(RoundedCornerShape(24.dp))
        ) {
            BasicTextField(
                value = promptState.value,
                onValueChange = { newValue ->
                    promptState.value = newValue
                    lastTextChangeTime.value = System.currentTimeMillis()
                },

                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
                    .focusRequester(focusRequester)
                    .onFocusChanged { focusState -> isFocused.value = focusState.isFocused },
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search icon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        if (promptState.value.text.isEmpty() && !isFocused.value) {
                            newsViewModel.toggleIsSearched(false)
                            Text(
                                text = "Search....",
                                color = Color.Gray,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        Spacer(Modifier.width(20.dp))

        val painter = rememberAsyncImagePainter(user.value?.pictureURL)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .size(iconSize)
                    .clip(RoundedCornerShape(iconRadius))
            ) {
                if (!isFocused.value) {
                    Image(
                        painter = painter,
                        contentDescription = "User profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                authViewModel.signOut(navController)
                            }
                    )
                }
                else {
                    Image(
                        painter = painterResource(R.drawable.filter),
                        contentDescription = "User profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                openFilterOverlay.value = true
                            }
                    )
                }
            }
        }
    }
}
