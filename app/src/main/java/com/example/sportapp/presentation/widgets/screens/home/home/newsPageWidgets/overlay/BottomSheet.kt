package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets.overlay

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    showSheet: Boolean,
    onDismiss: () -> Unit,
    isAIChat: Boolean,
    content: @Composable () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { !isAIChat }
    )
    val coroutineScope = rememberCoroutineScope()

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                if (isAIChat) {

                } else {
                    coroutineScope.launch { sheetState.hide() }
                    onDismiss()
                }
            },
            sheetState = sheetState,
            dragHandle = { if (isAIChat) null else {} }
        ) {
            content()
        }
    }
}
