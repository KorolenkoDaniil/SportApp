
import com.example.sportapp.models.chatMessage.MessageEntity
import com.example.sportapp.models.chatMessage.sqlLiteDB.MessagesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatRepository(private val messagesDao: MessagesDAO) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val messagesList = messagesDao.getAllMessages()

    fun addMessage(message: MessageEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            messagesDao.insertMessage(message)
        }
    }
}