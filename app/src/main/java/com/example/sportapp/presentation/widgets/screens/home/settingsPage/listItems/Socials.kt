package com.example.sportapp.presentation.widgets.screens.home.settingsPage.listItems

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R

@Composable
fun Sosials(){

    val context = LocalContext.current

    Spacer(Modifier.height(16.dp))

    Text("Напиши соц сети")

    Spacer(Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.instagram_logo),
            contentDescription = "Instagram",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://instagram.com/your_profile")
                    )
                    context.startActivity(intent)
                }
        )

        Image(
            painter = painterResource(R.drawable.telegram_icon),
            contentDescription = "Telegram",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://t.me/your_channel")
                    )
                    context.startActivity(intent)
                }
        )

        Image(
            painter = painterResource(R.drawable.vk_logo),
            contentDescription = "VK",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://vk.com/your_profile")
                    )
                    context.startActivity(intent)
                }
        )
    }
}