package com.example.sportapp.support2026.features.soccer.data.api

import com.example.sportapp.support2026.features.soccer.data.dto.MatchDto

interface SoccerApi {
//    лига с id 4937 2026

    suspend fun getMatchData(matchId: Int) : MatchDto
    suspend fun getLeagueMatches() : List<MatchDto>
}




//https://api.openligadb.de/getavailableLeagues/2026
//https://api.openligadb.de/getmatchdata/bl1/2026
//https://api.openligadb.de/getnextmatchbyleagueshortcut/bl1
//https://api.openligadb.de/getlastmatchbyleagueshortcut/bl1



//
//1. Запросы для экранов матчей и календарных списков
///getmatchdata/{leagueShortcut}/{leagueSeason}
//
//Где использовать: Полный календарь сезона (например, экран «Календарь всех игр»). Полезно для локального кеширования в Room/SQLite при первом запуске приложения.
//
//Ссылка: [https://api.openligadb.de/getmatchdata/bl1/2026](https://api.openligadb.de/getmatchdata/bl1/2026)
//
///getmatchdata/{leagueShortcut}/{leagueSeason}/{teamFilterstring}
//
//Где использовать: Экран конкретной команды (вкладка «Все матчи команды в лиге»).
//
//Ссылка: [https://api.openligadb.de/getmatchdata/bl1/2026/Bayern](https://api.openligadb.de/getmatchdata/bl1/2026/Bayern)
//
///getmatchdata/{teamId1}/{teamId2}
//
//Где использовать: Экран превью матча (раздел «История личных встреч / H2H»).
//
//Ссылка: [https://api.openligadb.de/getmatchdata/40/134](https://api.openligadb.de/getmatchdata/40/134)
//
///getlastchangedate/{leagueShortcut}/{leagueSeason}/{groupOrderId}
//
//Где использовать: Оптимизация запросов / Smart Refresh. Перед тем как качать весь тур заново, вы проверяете дату обновления. Если дата не изменилась, данные из сети не запрашиваются.
//
//Ссылка: [https://api.openligadb.de/getlastchangedate/bl1/2026/1](https://api.openligadb.de/getlastchangedate/bl1/2026/1)
//
//2. Запросы для виджетов и «Быстрых карточек» (Next / Last Match)
///getnextmatchbyleagueteam/{leagueId}/{teamId}
//
//Где использовать: Виджет «Следующая игра моей любимой команды» на главном экране.
//
//Ссылка: [https://api.openligadb.de/getnextmatchbyleagueteam/4937/40](https://api.openligadb.de/getnextmatchbyleagueteam/4937/40)
//
///getnextmatchbyleagueshortcut/{leagueShortcut}
//
//Где использовать: Баннер «Ближайший матч турнира» в шапке экрана лиги.
//
//Ссылка: [https://api.openligadb.de/getnextmatchbyleagueshortcut/bl1](https://api.openligadb.de/getnextmatchbyleagueshortcut/bl1)
//
///getlastmatchbyleagueshortcut/{leagueShortcut}
//
//Где использовать: Баннер «Последний сыгранный матч» (для быстрого просмотра свежего результата).
//
//Ссылка: [https://api.openligadb.de/getlastmatchbyleagueshortcut/bl1](https://api.openligadb.de/getlastmatchbyleagueshortcut/bl1)
//
///getlastmatchbyleagueteam/{leagueId}/{teamId}
//
//Где использовать: Экран профиля команды (карточка «Прошедшая игра» с итоговым счетом).
//
//Ссылка: [https://api.openligadb.de/getlastmatchbyleagueteam/4937/40](https://api.openligadb.de/getlastmatchbyleagueteam/4937/40)
//
//3. Навигация и фильтры по турам
///getcurrentgroup/{leagueShortcut}
//
//Где использовать: Стартовый запрос при открытии приложения. Определяет, какой тур идет прямо сейчас, чтобы сразу открыть его пользователю.
//
//Ссылка: [https://api.openligadb.de/getcurrentgroup/bl1](https://api.openligadb.de/getcurrentgroup/bl1)
//
///getavailablegroups/{leagueShortcut}/{leagueSeason}
//
//Где использовать: Выпадающий список (Dropdown / Spinner) или горизонтальная лента выбора туров (1 тур, 2 тур... 34 тур).
//
//Ссылка: [https://api.openligadb.de/getavailablegroups/bl1/2026](https://api.openligadb.de/getavailablegroups/bl1/2026)
//
//4. Турнирные таблицы, команды и бомбардиры
///getbltable/{leagueShortcut}/{leagueSeason} (или /getgrouptable/...)
//
//Где использовать: Экран «Турнирная таблица» (очки, победы, ничьи, поражения, разница голов).
//
//Ссылка: [https://api.openligadb.de/getbltable/bl1/2026](https://api.openligadb.de/getbltable/bl1/2026)
//
///getgoalgetters/{leagueShortcut}/{leagueSeason}
//
//Где использовать: Вкладка «Бомбардиры» (список лучших игроков лиги по забитым мячам).
//
//Ссылка: [https://api.openligadb.de/getgoalgetters/bl1/2026](https://api.openligadb.de/getgoalgetters/bl1/2026)
//
///getavailableteams/{leagueShortcut}/{leagueSeason}
//
//Где использовать: Экран «Команды» (список всех клубов с логотипами) или экран выбора «Моя любимая команда» при регистрации.
//
//Ссылка: [https://api.openligadb.de/getavailableteams/bl1/2026](https://api.openligadb.de/getavailableteams/bl1/2026)
//
//5. Гибкая временная выборка для профиля команды
///getmatchesbyteam/{teamFilterstring}/{weekCountPast}/{weekCountFuture}
//
///getmatchesbyteamid/{teamId}/{weekCountPast}/{weekCountFuture}
//
//Где использовать: Экран формы команды (например, показать матчи команды за 2 недели до текущей даты и 3 недели после).
//
//Пример: Бавария (ID 40), 2 недели назад, 4 недели вперед.
//
//Ссылка: [https://api.openligadb.de/getmatchesbyteamid/40/2/4](https://api.openligadb.de/getmatchesbyteamid/40/2/4)
//
//6. Системные / Вспомогательные запросы
///getresultinfos/{leagueId} и /getresulttypes
//
//Где использовать: Парсинг результатов в коде. Помогают понять, к чему относится результат (счет первого тайма, результат после 90 минут, овертайм или серия пенальти).
//
//Ссылки:
//
//[https://api.openligadb.de/getresultinfos/4937](https://api.openligadb.de/getresultinfos/4937)
//
//[https://api.openligadb.de/getresulttypes](https://api.openligadb.de/getresulttypes)

//
//Подробный разбор эндпоинтов со скриншота:
//
//1. /getresulttypes
//Назначение: Возвращает глобальный справочник типов результатов для всех видов спорта (счет 1-го тайма, основной результат за 90 минут, овертайм, буллиты/пенальти).
//
//Где использовать: Служебный запрос при инициализации приложения. Нужен для маппинга данных в коде, чтобы точно знать, какое значение из массива matchResults показывать как «Финальный счет» (After90Minutes), а какое — как «Счет после 1-го тайма» (HalfTime).
//
//Ссылка: [https://api.openligadb.de/getresulttypes](https://api.openligadb.de/getresulttypes)
//
//2. /getavailablegroups/{leagueShortcut}/{leagueSeason}
//Назначение: Возвращает полный список всех туров (игровых дней/этапов) выбранной лиги за сезон.
//
//Где использовать:
//
//Выпадающий список (Dropdown) или горизонтальная лента с кнопками [1 тур] [2 тур] ... [34 тур] на экране расписания.
//
//Пагинация/переключение между турами.
//
//Ссылка: [https://api.openligadb.de/getavailablegroups/bl1/2026](https://api.openligadb.de/getavailablegroups/bl1/2026)
//
//3. /getgoalgetters/{leagueShortcut}/{leagueSeason}
//Назначение: Возвращает рейтинг лучших бомбардиров лиги за сезон (имя игрока, количество забитых мячей, команда).
//
//Где использовать: Вкладка «Бомбардиры» / «Топ игроков» на экране лиги.
//
//Ссылка: [https://api.openligadb.de/getgoalgetters/bl1/2026](https://api.openligadb.de/getgoalgetters/bl1/2026)
//
//4. /getavailableteams/{leagueShortcut}/{leagueSeason}
//Назначение: Список всех команд, выступающих в данной лиге в текущем сезоне (с их ID, названиями и ссылками на эмблемы/логотипы).
//
//Где использовать:
//
//Экран «Все команды лиги».
//
//Экран первичного выбора «Любимая команда» (Favorite Team) при первом входе в приложение.
//
//Ссылка: [https://api.openligadb.de/getavailableteams/bl1/2026](https://api.openligadb.de/getavailableteams/bl1/2026)
//
//5. /getbltable/{leagueShortcut}/{leagueSeason}
//Назначение: Возвращает итоговую турнирную таблицу Бундеслиги/лиги (место, сыгранные матчи, очки, победы/ничьи/поражения, забитые/пропущенные мячи).
//
//Где использовать: Главная вкладка «Турнирная таблица» (Standings) на экране лиги.
//
//Ссылка: [https://api.openligadb.de/getbltable/bl1/2026](https://api.openligadb.de/getbltable/bl1/2026)
//
//6. /getgrouptable/{leagueShortcut}/{leagueSeason}
//Назначение: Возвращает таблицы по группам (актуально для турниров со стадией групп, таких как Лига Чемпионов или Чемпионаты мира/Европы: Группа A, Группа B и т.д.).
//
//Где использовать: На экране турнирной таблицы для кубковых и международных турниров (ucl). Для обычной Бундеслиги чаще используют getbltable.
//
//Ссылка: [https://api.openligadb.de/getgrouptable/ucl/2026](https://api.openligadb.de/getgrouptable/ucl/2026)
//
//7. /getmatchesbyteam/{teamFilterstring}/{weekCountPast}/{weekCountFuture}
//Назначение: Фильтр матчей конкретной команды по текстовому названию за временное окно (в неделях).
//
//Параметры: Название команды (например, Bayern), сколько недель НАЗАД от текущей даты (weekCountPast), сколько недель ВПЕРЕД (weekCountFuture).
//
//Где использовать: На экране профиля команды, чтобы показать блок «Форма команды» (например, последние 3 недели и предстоящие 2 недели).
//
//Ссылка: [https://api.openligadb.de/getmatchesbyteam/Bayern/3/2](https://api.openligadb.de/getmatchesbyteam/Bayern/3/2)
//
//8. /getmatchesbyteamid/{teamId}/{weekCountPast}/{weekCountFuture}
//Назначение: Аналогично предыдущему запросу, но поиск идет по точному ID команды (что надежнее, так как исключает ошибки в написании названия).
//
//Где использовать: Экран «Карточка команды» или «Календарь клуба» за ближайший период.
//
//Пример: Бавария (ID: 40), за 4 недели назад и 4 недели вперед.
//
//Ссылка: [https://api.openligadb.de/getmatchesbyteamid/40/4/4](https://api.openligadb.de/getmatchesbyteamid/40/4/4)