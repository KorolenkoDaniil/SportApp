package com.example.sportapp.support2026.presentation.screeens.matchesScheduleScreen.matchItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportapp.support2026.features.soccer.domain.entities.Match

@Composable
fun MatchItemContent(
    match: Match,
    team1Name: String,
    team2Name: String,
    logo1Painter: androidx.compose.ui.graphics.painter.Painter,
    logo2Painter: androidx.compose.ui.graphics.painter.Painter,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
//        Arrangement.SpaceBetween,
    ) {

        val gameStatus = remember(match.matchIsFinished, match.matchDateTimeUTC) {
            if (match.matchIsFinished) {
                "FT"
            } else {
                match.matchDateTimeUTC.let { utcString ->
                    runCatching {
                        java.time.Instant.parse(utcString)
                            .atZone(java.time.ZoneId.of("Europe/Minsk"))
                            .format(java.time.format.DateTimeFormatter.ofPattern(" HH:mm"))
                    }.getOrDefault("NS")
                } ?: "NS"
            }
        }

        val (team1Goals, team2Goals) = remember(match.matchIsFinished, match.matchResults) {
            val results = match.matchResults

            val selectedResult = if (match.matchIsFinished) {
                results.firstOrNull {
                    it.resultTypeKind == "After90Minutes" || it.resultName == "Endergebnis"
                } ?: results.lastOrNull()
            } else {
                when (results.size) {
                    0 -> null
                    1 -> results.firstOrNull()
                    else -> results.lastOrNull()
                }
            }

            if (selectedResult != null) {
                (selectedResult.pointsTeam1?.toString() ?: "-") to (selectedResult.pointsTeam2?.toString() ?: "-")
            } else {
                "-" to "-"
            }
        }


        Text(text = gameStatus)

        Spacer(Modifier.height(10.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row(
                modifier =  Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = logo1Painter,
                    contentDescription = "team1Logo",
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = team1Name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }


            Text(text = team1Goals)
        }


        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row(
                modifier =  Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = logo2Painter,
                    contentDescription = "team2Logo",
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = team2Name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }


            Text(text = team2Goals)
        }

    }
}



//        match.matchDateTimeUTC?.let { utcString -> ... }
//        Берёт строку времени в UTC из объекта матча.
//        Если там лежит строка (например, "2026-11-27T19:30:00Z"), она попадает в блок под именем utcString (можно было написать и it, но явное имя utcString читается понятнее).
//        Если там null, блок пропускается и возвращается null.
//        runCatching { ... }
//        Обёртка для безопасного выполнения кода, который может выбросить исключение (например, DateTimeParseException, если с сервера пришла кривая строка даты или пустая строка).
//        Вместо падения приложения runCatching перехватывает ошибку и упаковывает результат в объект Result.
//        Парсинг и перевод времени (Instant.parse -> atZone -> format)
//        Instant.parse(utcString) — превращает строку формата ISO-8601 UTC ("2026-11-27T19:30:00Z") в момент времени на шкале UTC. //
//        .atZone(ZoneId.of("Europe/Minsk")) — переводит этот момент времени в часовой пояс Минска (UTC+3), получая 27 ноября 2026, 22:30
//        .format(DateTimeFormatter.ofPattern("HH:mm")) — форматирует дату/время в строку "22:30".
//        .getOrDefault("NS")
//        Достаёт результат из runCatching.
//        Если парсинг и форматирование прошли успешно — вернёт "22:30".
//        Если внутри runCatching вылетела ошибка — вернёт запасное значение "NS".
//        ?: "NS" (Elvis-оператор)
//        Срабатывает, если левая часть (результат .let { ... }) равна null — то есть когда match.matchDateTimeUTC изначально был null.
//        В этом случае подставляется "NS".