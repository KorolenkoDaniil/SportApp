import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportapp.R

@Composable
fun BottomBar(modifier: Modifier = Modifier) {

    BottomAppBar(
        modifier = modifier.fillMaxWidth()
            .height(100.dp),
        containerColor = Color(0xFFFFFFFF)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.home), // Указываем ресурс
                contentDescription = "Home Icon",
                modifier = Modifier
                    .size(40.dp)
//                    .alpha(0.5f)
            )
            Image(
                painter = painterResource(id = R.drawable.matches), // Указываем ресурс
                contentDescription = "Matches Icon",
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0.5f)
            )
            Image(
                painter = painterResource(id = R.drawable.video), // Указываем ресурс
                contentDescription = "Video Icon",
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0.5f)
            )
            Image(
                painter = painterResource(id = R.drawable.like), // Указываем ресурс
                contentDescription = "Like Icon",
                modifier = Modifier
                    .size(40.dp)
                    .alpha(0.5f)
            )
        }
    }
}
