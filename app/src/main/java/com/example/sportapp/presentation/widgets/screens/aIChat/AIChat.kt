package com.example.sportapp.presentation.widgets.screens.aIChat

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.sportapp.containers.ViewModelContainer
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.presentation.widgets.screens.aIChat.aiChat.AIChatOverlay
import com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay.BottomSheet

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AIChatPage(
    authViewModel: AuthViewModel,
    navController: NavController,
    viewModels: ViewModelContainer,
    horizontalPaddings: Dp
) {

    val overlayVisible = remember { mutableStateOf(true) }

    BottomSheet(
        showSheet = overlayVisible.value,
        isAIChat = true,
        onDismiss = { overlayVisible.value = false }
    ) {
        Column {
            Box(Modifier.weight(1f).fillMaxSize()) {

            }
            AIChatOverlay(
                authModel = authViewModel,
                horizontalPaddings = horizontalPaddings,
                navController,
                viewModels
            )
        }
    }
}

