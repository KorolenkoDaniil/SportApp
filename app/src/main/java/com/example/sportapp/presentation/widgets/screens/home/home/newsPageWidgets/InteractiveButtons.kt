package com.example.sportapp.presentation.widgets.screens.home.home.newsPageWidgets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import java.time.LocalDateTime

@Composable
fun InteractiveButtons(dateTime: LocalDateTime, overlay: MutableState<Boolean>) {
    val context = LocalContext.current

    val link = "https://korolenkodaniil.github.io/deeplink-sportapp/?id=$dateTime"

    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter = painterResource(R.drawable.like),
            contentDescription = null,
        )

        Spacer(Modifier.width(8.dp))

        Text(text = "560")

        Spacer(Modifier.width(16.dp))

        Image(
            painter = painterResource(R.drawable.comment),
            contentDescription = null,
            Modifier.clickable {
                overlay.value = true
            }
        )

        Spacer(Modifier.width(8.dp))

        Text(text = "560")

        Spacer(Modifier.width(16.dp))

        Image(
            painter = painterResource(R.drawable.send),
            contentDescription = null,
            modifier = Modifier.clickable {
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, link)
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }
        )

        Spacer(Modifier.width(8.dp))

        Text(text = "560")
    }
}