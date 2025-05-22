package com.example.sportapp.presentation.widgets.screens.home

import AppActivityViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.ItemWithAction
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.ItemWithTextField
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.ItemWithToggle
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.SettingsHeader
import com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems.Sosials

@Composable
fun SettingsPage(
    authViewModel: AuthViewModel,
    navController: NavController,
    navHostController: NavHostController,
    topPaddings: Dp,
    appActivityViewModel: AppActivityViewModel
) {

    val openDialog = remember { mutableStateOf(false) }
    val newPassword = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = topPaddings)
            .background(MaterialTheme.colorScheme.background)
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


            ItemWithTextField(
                drawableResource = painterResource(R.drawable.password),
                text = "Изменить пароль",
                input = newPassword
            )


            Button(
                onClick = {
                    openDialog.value = true
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("сохранить новый пароль")
            }

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)


            ItemWithToggle(
                drawableResource = painterResource(R.drawable.moon),
                text = "темная тема",
                appActivityViewModel = appActivityViewModel
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.language),
                    "",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = "язык",
                    modifier = Modifier.clickable {

                    }
                )
            }


//            ItemWithToggle(
//                drawableResource = painterResource(R.drawable.notification),
//                text = "Уведомения"
//            )


            ItemWithAction(
                drawableResource = painterResource(R.drawable.delete),
                text = "Удалить аккаунт",
                action = {
                    authViewModel.deleteUser(
                        navController = navHostController
                    )
                }
            )


            ItemWithAction(
                drawableResource = painterResource(R.drawable.exit),
                text = "Выйти из аккаунта",
                action = {
                    authViewModel.signOut(
                        navController = navHostController
                    )
                }
            )
        }

        Sosials()
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Подтвердите измененеие пароля", fontSize = 20.sp) },
            confirmButton = {
                Button({
                    openDialog.value = false
                    authViewModel
                }) {
                    Text("OK", fontSize = 22.sp)
                }
            }
        )
    }
}
