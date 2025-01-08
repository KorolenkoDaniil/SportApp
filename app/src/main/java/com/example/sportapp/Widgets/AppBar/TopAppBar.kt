
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.shared.BottomBar.UserImage
import com.example.sportapp.ui.theme.Blue100

@Composable
fun TopAppBar( appActivity: AppActivityViewModel ) {

    val pageName by appActivity.pageName.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .background(color = Blue100)
                .height(90.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = pageName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 4.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserImage()
        }
    }
}
