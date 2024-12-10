package com.example.sportapp

import BottomBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.sportapp.Pages.HomePage
import com.example.sportapp.Pages.LikePage
import com.example.sportapp.Pages.MatchesPage
import com.example.sportapp.Pages.VideoPage
import com.example.sportapp.Widgets.AppBar.TopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            val currentPage = remember {
                mutableStateOf("home")
            }

            Scaffold(
                containerColor = Color(0xFFF6F6F6),
                bottomBar = { BottomBar(currentPage) },
                topBar = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        TopAppBar(modifier = Modifier.statusBarsPadding())
                    }
                }
            ) { innerPadding ->
                Box(
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp)
                ) {
                    when (currentPage.value) {
                        "home" -> HomePage()
                        "matches" -> MatchesPage()
                        "video" -> VideoPage()
                        "like" -> LikePage()
                    }
                }
            }

        }
    }
}