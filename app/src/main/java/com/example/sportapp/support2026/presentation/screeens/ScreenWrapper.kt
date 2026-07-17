package com.example.sportapp.support2026.presentation.screeens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sportapp.support2026.presentation.ui.ScreenPaddingHorizontal
import com.example.sportapp.support2026.presentation.ui.ScreenPaddingVertical

@Composable
fun ScreenWrapper (
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = ScreenPaddingHorizontal, vertical = ScreenPaddingVertical)
    ) {
        topBar()

        content()
    }

}