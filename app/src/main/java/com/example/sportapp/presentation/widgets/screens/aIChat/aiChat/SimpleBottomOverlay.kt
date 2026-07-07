//package com.example.sportapp.presentation.widgets.screens.aIChat.aiChat
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun SimpleBottomOverlay(
//    visible: Boolean,
//    content: @Composable () -> Unit
//) {
//    if (visible) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.Black.copy(alpha = 0.3f))
//        ) {
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .fillMaxWidth()
//                    .background(
//                        color = Color.White,
//                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
//                    )
//            ) {
//                content()
//            }
//        }
//    }
//}
