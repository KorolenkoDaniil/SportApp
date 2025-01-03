package com.example.sportapp.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.api.viewModels.ViewModelInterface
import com.example.sportapp.ui.theme.Blue100
import com.example.sportapp.ui.theme.style10
import com.example.sportapp.ui.theme.style11
import com.example.sportapp.ui.theme.style8
import com.example.sportapp.ui.theme.style9
import kotlinx.coroutines.launch


@Composable
fun <T> CommonError(viewModel: ViewModelInterface<T>) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column {
            Text(text = "404", style = style8)

            Spacer(Modifier.height(32.dp))

            Text(text = "You weren't meant to see this...", style = style9)
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Either the internet has broken or we couldn't loading matches",
                style = style10,
                modifier = Modifier.width(200.dp)
            )
            Spacer(Modifier.height(32.dp))


            //кнопка загрузки данных еще раз
            Button(colors = ButtonColors(
                contentColor = Blue100,
                disabledContentColor = Blue100,
                containerColor = Blue100,
                disabledContainerColor = Blue100
            ), onClick = {
                coroutineScope.launch {
                    viewModel.loadData()
                }
            }) {
                Text(text = "Try again", style = style11)
            }
        }


    }
}

