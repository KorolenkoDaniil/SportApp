package com.example.sportapp.presentation.widgets.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.SettingsHeader
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.Sosials

@Composable
fun SettingsPage(
    authViewModel: AuthViewModel,
    navController: NavController,
    navHostController: NavHostController,
    topPaddings: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = topPaddings)
    ) {

        SettingsHeader(
            authViewModel = authViewModel,
            navController = navController
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Row {
//                Image(painter = painterResource(R.drawable.))

                Text(
                    text = "Изменить почту",
                    modifier = Modifier.clickable {

                    }
                )


            }

            Text(
                text = "Изменить пароль",
                modifier = Modifier.clickable {

                }
            )

            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("сохранить")
            }

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            Text(
                text = "Темная тема",
                modifier = Modifier.clickable {

                }
            )
            Text(
                text = "Язык",
                modifier = Modifier.clickable {

                }
            )
            Text(
                text = "Уведомления",
                modifier = Modifier.clickable {

                }
            )

            Text(
                text = "Выйти из аккаунта",
                modifier = Modifier.clickable {
                    authViewModel.signOut(navHostController)
                }
            )
        }

        Sosials()
    }
}
