import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//класс, представления 1 события в матче
@Serializable
data class EventResponse(
    @SerialName("type") val type: String? = null,
    @SerialName("match_phase") val matchPhase: String? = null,
    @SerialName("order") val order: Int? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("team_id") val teamId: String? = null,
    @SerialName("player_id") val playerId: String? = null,
    @SerialName("player_surname") val playerSurname: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("player_full_name") val playerFullName: String? = null,
    @SerialName("player_shirt_name") val playerShirtName: String? = null,
    @SerialName("minute") val minute: Int? = null,
    @SerialName("additional_minute") val additionalMinute: Int? = null

)
