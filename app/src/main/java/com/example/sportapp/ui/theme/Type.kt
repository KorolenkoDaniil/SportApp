package com.example.sportapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.sportapp.R



val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val OpenSans = FontFamily(
    Font(R.font.open_sans_bold, FontWeight.Bold),
    Font(R.font.open_sans_regular, FontWeight.Normal),
    Font(R.font.open_sans_light, FontWeight.Light),
    Font(R.font.open_sans_medium, FontWeight.Medium),
)

val Roboto = FontFamily(
    Font(R.font.roboto_italic_font, FontWeight.Normal),
    Font(R.font.roboto_variable_font, FontWeight.Normal),
)



val style1 = TextStyle(color = Color.Black, fontFamily = Roboto, fontSize = 20.sp, fontWeight = FontWeight.Bold)

val style2 = TextStyle(color = Color.Black, fontFamily = Roboto, fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start)

val style3 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 18.sp, fontWeight = FontWeight.Bold)

val style4 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 16.sp, fontWeight = FontWeight.Bold)

val style5 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

val style6 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

val style12 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 18.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)

val style7 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

val style8 = TextStyle(color = Blue100, fontFamily = Roboto, fontSize = 40.sp, fontWeight = FontWeight.Bold)

val style9 = TextStyle(color = Color.Black, fontFamily = Roboto, fontSize = 14.sp, fontWeight = FontWeight.Bold)

val style10 = TextStyle(color = Color.Black, fontFamily = Roboto, fontSize = 14.sp, fontWeight = FontWeight.Normal)

val style11 = TextStyle(color = Color.White, fontFamily = Roboto, fontSize = 14.sp, fontWeight = FontWeight.Bold)

val style12_first_page = TextStyle(fontFamily = Roboto, fontSize = 24.sp, fontWeight = FontWeight.Light, textAlign = TextAlign.Center)

val style13_log_in__page = TextStyle(fontFamily = Roboto, fontSize = 32.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

val style14 = TextStyle(fontFamily = Roboto, fontSize = 14.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start)

val style16 = TextStyle(fontFamily = Roboto, fontSize = 12.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = blue_grey_color)

val style15 = TextStyle(fontFamily = Roboto, fontSize = 12.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, color = red_accent_color, textDecoration = TextDecoration.Underline)

val elapsedTime = TextStyle(fontFamily = Roboto, fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color.Black.copy(alpha = 0.5f))

val my_messages = TextStyle(fontFamily = Roboto, fontSize = 16.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.End)

val companion_messages = TextStyle(fontFamily = Roboto, fontSize = 16.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Start, color = Color.White)

val News_title_style = TextStyle(fontFamily = Roboto, fontSize = 24.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Start, color = Color.Black)




//val Typography.style1: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = MaterialTheme.colorScheme.onPrimary,
//        fontFamily = Roboto,
//        fontSize = 20.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style2: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = MaterialTheme.colorScheme.onSurface,
//        fontFamily = Roboto,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Start
//    )
//
//val Typography.style3: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = MaterialTheme.colorScheme.onSurface,
//        fontFamily = Roboto,
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style4: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style5: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 24.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style6: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 20.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style7: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style8: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Blue100,
//        fontFamily = Roboto,
//        fontSize = 40.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style9: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.Black,
//        fontFamily = Roboto,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style10: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.Black,
//        fontFamily = Roboto,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Normal
//    )
//
//val Typography.style11: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Bold
//    )
//
//val Typography.style12: TextStyle
//    @Composable
//    get() = TextStyle(
//        color = Color.White,
//        fontFamily = Roboto,
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style12FirstPage: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 24.sp,
//        fontWeight = FontWeight.Light,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style13LoginPage: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 32.sp,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center
//    )
//
//val Typography.style14: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 14.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Start
//    )
//
//val Typography.style15: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Center,
//        color = red_accent_color,
//        textDecoration = TextDecoration.Underline
//    )
//
//val Typography.style16: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Center,
//        color = blue_grey_color
//    )
//
//val Typography.elapsedTime: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Normal,
//        color = Color.Black.copy(alpha = 0.5f)
//    )
//
//val Typography.myMessages: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.End
//    )
//
//val Typography.companionMessages: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 16.sp,
//        fontWeight = FontWeight.Normal,
//        textAlign = TextAlign.Start,
//        color = Color.White
//    )
//
//val Typography.newsTitle: TextStyle
//    @Composable
//    get() = TextStyle(
//        fontFamily = Roboto,
//        fontSize = 24.sp,
//        fontWeight = FontWeight.Medium,
//        textAlign = TextAlign.Start,
//        color = Color.Black
//    )
//

