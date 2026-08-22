package com.example.sportapp.support2026.presentation.screeens.homeScreen.widgets.homeTopBar.searchRow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.support2026.presentation.screeens.commonWidgets.AppTextField

@Composable
fun SearchRow (
    searchPrompt: String,
    onSearchQueryChange: (String) -> Unit
) {
    AppTextField(
        value = searchPrompt,
        onValueChange = onSearchQueryChange,
        placeholder = "поиск новости",
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(56.dp),
        singleLine = true,
    )
}