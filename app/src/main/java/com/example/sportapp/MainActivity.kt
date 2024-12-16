package com.example.sportapp

import BottomBar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportapp.api.viewModels.MainViewModel
import com.example.sportapp.pages.HomePage
import com.example.sportapp.pages.LikePage
import com.example.sportapp.pages.MatchesPage
import com.example.sportapp.pages.VideoPage


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val mainActivity = MainViewModel()

            Scaffold(
                containerColor = Color(0xFFF6F6F6),
                bottomBar = { BottomBar(mainActivity.currentPage) },
            ) { innerPadding ->
                Box(
                    Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 8.dp)
                ) {
                    when (mainActivity.currentPage.value) {
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