
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportapp.models.news.domain.NewsEntity

@Composable
fun Comments(currentNews: NewsEntity) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        items(currentNews.comments) { comment ->
//            CommentItem(commentText = comment.commentText, userEmail = comment.userEmail)
//        }
    }
}

@Composable
fun CommentItem(commentText: String, userEmail: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = userEmail)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = commentText)
    }
}
