package com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.sportapp.domain.viewModels.authorization.AuthViewModel
import com.example.sportapp.ui.theme.SettingTextStyle

@Composable
fun ItemWithToggle(drawableResource: Painter, text: String, authViewModel: AuthViewModel){

    val isWhiteTheme = authViewModel.themeIsWhite.collectAsState().value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(0.6f)
        ) {
            Image(
                painter = drawableResource,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = text, Modifier.fillMaxWidth().weight(1F), style = MaterialTheme.typography.SettingTextStyle)
        }

        Spacer(modifier = Modifier.weight(1f))



        Switch(
            checked = isWhiteTheme,
            onCheckedChange = {

                authViewModel.changeTheme()
            },
            modifier = Modifier.height(20.dp)
        )
    }
}