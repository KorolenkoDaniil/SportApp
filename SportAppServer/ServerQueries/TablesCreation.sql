use SportAppDB

CREATE TABLE News (
    Sport NVARCHAR(255) NOT NULL,         -- Вид спорта
    DateTime DATETIME NOT NULL PRIMARY KEY, -- Дата и время события
    Text NVARCHAR(MAX) NOT NULL           -- Текст новости
);
