package com.example.sportapp.presentation.widgets.common.shared

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.R.drawable.loading
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.models.viewModels.NewsState
import com.example.sportapp.presentation.navigation.Screen

@Composable
fun Loading() {

    val startRotation = 0f
    val endRotation = 360f

    val transition = rememberInfiniteTransition(label = "")

    val offset by transition.animateFloat(
        initialValue = startRotation,
        targetValue = endRotation,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(loading),
            contentDescription = "loading",
            modifier = Modifier
                .size(75.dp)
                .graphicsLayer { rotationZ = offset }
        )
    }
}



@Composable
fun LoadingPage(
    viewModels: ViewModelContainer,
    navController: NavHostController
) {
    val newsState by viewModels.newsViewModel.getState().collectAsState()

    when (newsState) {
        is NewsState.Load -> {
            Loading() // твой индикатор загрузки
        }

        is NewsState.NewsContent -> {
            // переход на HomePage
            LaunchedEffect(Unit) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Loading.route) { inclusive = true }
                }
            }
        }

        is NewsState.Error -> {
            CommonError(
                viewModels.newsViewModel,
                Screen.Loading.route,
                navController,
                "новости"
            )
        }
    }
}
