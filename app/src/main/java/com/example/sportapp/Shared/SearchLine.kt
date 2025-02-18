package com.example.sportapp.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sportapp.models.user.domain.UserEntity


@Composable
fun SearchLine(user: UserEntity) {


    var userSearchText by remember {
        mutableStateOf("")
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.7F)
                .height(36.dp)
                .clip(RoundedCornerShape(32.dp))
        ) {
            BasicTextField(value = userSearchText,
                onValueChange = { userSearchText = it },
                modifier = Modifier

                    .background(Color.White)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .fillMaxSize(),

                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Search, contentDescription = "search icon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        innerTextField()
                    }
                })
        }

        Spacer(Modifier.width(20.dp))

        val painter = rememberAsyncImagePainter(user.pictureURL)

        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = "sadf",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}