package com.example.sportapp.pages.widgets.shared

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.user.domain.UserEntity
import com.example.sportapp.models.viewModels.AuthViewModel
import kotlinx.coroutines.flow.StateFlow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchLine(user: StateFlow<UserEntity?>, authViewModel: AuthViewModel, navController: NavHostController) {


    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .height(40.dp)
                .weight(0.7F)
                .clip(RoundedCornerShape(24.dp))
        ) {
            BasicTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp)
                    .fillMaxSize(),
                singleLine = true,

                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search icon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        if (textState.text.isEmpty()) {
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
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Image(
                    painter = painter,
                    contentDescription = "sadf",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            authViewModel.signOut(navController)
                        }
                )
            }
        }
    }
}