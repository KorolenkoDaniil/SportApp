package com.example.sportapp

import BottomBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.sportapp.ui.theme.SportAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            SportAppTheme {
                Scaffold(
                    containerColor = Color(0xFFF6F6F6),
                    bottomBar = { BottomBar(modifier = Modifier) }
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {

                    }
                }
            }
        }
    }
}