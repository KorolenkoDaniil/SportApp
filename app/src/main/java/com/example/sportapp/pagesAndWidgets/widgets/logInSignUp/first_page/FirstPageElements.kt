package com.example.sportapp.pagesAndWidgets.widgets.logInSignUp.first_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.ui.theme.background_color
import com.example.sportapp.ui.theme.style12_first_page

@Composable
fun FirstPageElements() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background_color)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(0.75f)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.kor_sport_logo),
                        contentDescription = "",
                        modifier = Modifier.height(200.dp)
                    )
                    Text(
                        text = "KorSport — будь в курсе, \n" +
                                "будь лидером", style = style12_first_page
                    )
                }
            }


            Box(
                modifier = Modifier
                    .weight(0.15f)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.kor_food),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.korcourses),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.kor_fin2),
                        contentDescription = "",
                        modifier = Modifier.height(40.dp)
                    )
                }
            }
        }
    }
}




//Box(
//modifier = Modifier
//.weight(0.15f)
//.fillMaxWidth()
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(R.drawable.kor_food),
//            contentDescription = "",
//            modifier = Modifier.height(36.dp)
//        )
//        Image(
//            painter = painterResource(R.drawable.korcourses),
//            contentDescription = "",
//            modifier = Modifier.height(36.dp)
//        )
//        Image(
//            painter = painterResource(R.drawable.kor_fin2),
//            contentDescription = "",
//            modifier = Modifier.height(36.dp)
//        )
//    }
//}