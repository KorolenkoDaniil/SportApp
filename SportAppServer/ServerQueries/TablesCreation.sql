use KorSport


DROP TABLE IF EXISTS CommentLikes;
DROP TABLE IF EXISTS NewsLike;
DROP TABLE IF EXISTS NewsComments;
DROP TABLE IF EXISTS news_tags;
DROP TABLE IF EXISTS News;
DROP TABLE IF EXISTS Users;



CREATE TABLE News (
    Sport NVARCHAR(255) NOT NULL, 
    DateTime DATETIME NOT NULL PRIMARY KEY,
    Title NVARCHAR(MAX) NOT NULL,
    ImageId NVARCHAR(MAX) NOT NULL,
	ArticleText NVARCHAR(MAX),
	TextAfterLemmatize NVARCHAR(MAX),
	FTS_key Int Identity(1, 1) not null
);

CREATE TABLE Users (
    UserEmail NVARCHAR(255) NOT NULL Primary key, 
	UserImage NVARCHAR(MAX),
);


CREATE TABLE news_tags (
    TagId INT IDENTITY(1,1) PRIMARY KEY,
    NewsDateTime DATETIME NOT NULL,
    Tag NVARCHAR(50) NOT NULL
);

CREATE TABLE NewsComments (
    CommentId INT IDENTITY(1,1) PRIMARY KEY,
    NewsDateTime DATETIME NOT NULL,
    CommentDateTime DATETIME NOT NULL,
    CommentText NVARCHAR(max) NOT NULL,
    UserEmail NVARCHAR(255) NOT NULL,
	LikesCount Int not null default 0
    FOREIGN KEY (NewsDateTime) REFERENCES News(DateTime) ON DELETE CASCADE,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail) ON DELETE CASCADE
);

CREATE TABLE NewsLike (
    LikeId INT IDENTITY(1,1) PRIMARY KEY,
    NewsDateTime DATETIME NOT NULL,
    UserEmail NVARCHAR(255) NOT NULL,
    FOREIGN KEY (NewsDateTime) REFERENCES News(DateTime) ON DELETE CASCADE,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail) 
);



CREATE TABLE CommentLikes (
    CommentLikeId INT IDENTITY(1,1) PRIMARY KEY,                  
    CommentId INT NOT NULL,                                       
    LikedByUserEmail NVARCHAR(255) NOT NULL,                      

    FOREIGN KEY (CommentId) REFERENCES NewsComments (CommentId) ON DELETE CASCADE,
    FOREIGN KEY (LikedByUserEmail) REFERENCES Users(UserEmail),

    CONSTRAINT UQ_CommentLikes_CommentUser UNIQUE (CommentId, LikedByUserEmail)
);



create index NewCommentsIndex on NewsComments (NewsDateTime)
create index NewLikesIndex on NewsLike (NewsDateTime)
create index NewsSportIndex on News (Sport)

--drop index if exists NewCommentsIndex on NewsComments



alter table news_tags 
add constraint FK_News_Tags
foreign key (NewsDateTime) references News(DateTime)
on delete cascade 






-- Вставка данных в таблицу Users
INSERT INTO Users (UserEmail, UserImage)
VALUES 
('ddddddd@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('dddddd@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('5ftdtde@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('hhhhhh@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('fffffffff@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg');



-- Вставка данных в таблицу News
INSERT INTO News (Sport, DateTime, Title, ImageId, ArticleText, TextAfterLemmatize)
VALUES 
-- Новости о футболе
('Футбол', CONVERT(DATETIME, '2024-04-05 14:00:00', 120), 'Зенит обыгрывает Спартак в дерби', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Зенит победил Спартак 3:1 в важном московском дерби, обеспечив себе лидерство в чемпионате России.', 'зенит победить спартак важный московский дерби обеспечить лидерство чемпионат россия'),
('Футбол', CONVERT(DATETIME, '2024-04-06 19:00:00', 120), 'Ливерпуль разрывает Манчестер Сити', '7wkpxki9yofar3hmkfm08s5yox8mz54b.jpg', 'Ливерпуль выиграл у Манчестер Сити с результатом 4:1, продемонстрировав отличный командный дух.', 'ливерпуль выиграть манчестер сити результат продемонстрировать отличный командный дух'),
('Футбол', CONVERT(DATETIME, '2024-04-07 16:30:00', 120), 'Ювентус выигрывает в дерби с Наполи', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Ювентус одержал победу в дерби с Наполи, выиграв 2:0 в сложном матче на выезде.', 'ювентус одержать победа дерби наполоть выиграть сложный матч выезд'),
('Футбол', CONVERT(DATETIME, '2024-04-08 18:00:00', 120), 'ПСЖ победил Лион в Лиге 1', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', 'Парижский Сен-Жермен победил Лион с результатом 3:1 в важном матче чемпионата Франции.', 'парижский сен жермен победить лион результат важный матч чемпионат франция'),

-- Новости о баскетболе
('Баскетбол', CONVERT(DATETIME, '2024-04-05 20:00:00', 120), 'Никс побеждают Хьюстон', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Никс победили Хьюстон с результатом 115-103 в напряженном поединке.', 'никс победить хьюстон результат напрячь поединок'),
('Баскетбол', CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'Даллас Маверикс и Лейкерс в битве за плей-офф', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Даллас Маверикс выиграл у Лейкерс 118-112, укрепив свои позиции для выхода в плей-офф.', 'ячсмячсм'),
('Баскетбол', CONVERT(DATETIME, '2024-04-07 22:00:00', 120), 'Милуоки Бакс побеждают Индиану', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Милуоки Бакс одержали уверенную победу над Индианой 120-105.', 'милуока бакс одержать уверенный победа индиана'),
('Баскетбол', CONVERT(DATETIME, '2024-04-08 23:30:00', 120), 'Бостон Селтикс снова на высоте', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Бостон Селтикс победили Детройт Пистонс 125-98, продолжив свою серию побед в сезоне.', 'бостон селтикс победить детройт пистонс продолжить серия победа сезон'),

-- Новости о теннисе
('Теннис', CONVERT(DATETIME, '2024-04-05 17:00:00', 120), 'Роджер Федерер выходит в финал Открытого чемпионата Франции', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Роджер Федерер вновь вышел в финал Открытого чемпионата Франции, победив Рафаэля Надаля в полуфинале.', 'роджер федерер вновь выйти финал открытый чемпионат франция победить рафаэль надаля полуфинал'),
('Теннис', CONVERT(DATETIME, '2024-04-06 14:30:00', 120), 'Кики Бертенс выигрывает турнир в Риме', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Кики Бертенс одержала победу на турнире в Риме, обыграв Симону Халеп в финале.', 'кик бертенс одержать победа турнир рим обыграть симон халеп финал'),
('Теннис', CONVERT(DATETIME, '2024-04-07 16:00:00', 120), 'Кербер победила Серену Уильямс в финале Австралии', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Ангелик Кербер выиграла в финале Австралийского Открытого турнира, победив Серену Уильямс.', 'ангелик кербер выиграть финал австралийский открытый турнир победить серена уильямс'),
('Теннис', CONVERT(DATETIME, '2024-04-08 18:45:00', 120), 'Джокович и Надаль в полуфинале', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Новак Джокович и Рафаэль Надаль встретятся в полуфинале турнира в Мадриде.', 'новак джокович рафаэль надаль встретиться полуфинал турнир мадрид'),

-- Новости о хоккее
('Хоккей', CONVERT(DATETIME, '2024-04-05 19:15:00', 120), 'Сибирь обыгрывает Ак Барс в Кубке Гагарина', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сибирь победила Ак Барс в 1/4 финала Кубка Гагарина, одержав важную победу 3:2.', 'сибирь победить ак барс финал кубок гагарин одержать важный победа'),
('Хоккей', CONVERT(DATETIME, '2024-04-06 21:00:00', 120), 'ЦСКА вырывает победу у Локомотива', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', 'ЦСКА победил Локомотив в овертайме 4:3 в матче Кубка Гагарина.', 'цска победить локомотив овертайм матч кубок гагарин'),
('Хоккей', CONVERT(DATETIME, '2024-04-07 22:30:00', 120), 'Металлург обыгрывает Салават Юлаев', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Металлург одержал победу над Салаватом Юлаевым 5:1 в захватывающем матче.', 'металлург одержать победа салават юлаев захватывать матч'),
('Хоккей', CONVERT(DATETIME, '2024-04-08 23:45:00', 120), 'Динамо Москва выходит в финал', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Динамо Москва одержало победу над Спартаком и вышло в финал Кубка Гагарина.', 'динамо москва одержать победа спартак выйти финал кубок гагарин'),

-- Новости о Формуле 1
('Формула 1', CONVERT(DATETIME, '2024-04-05 15:30:00', 120), 'Гран-при Бахрейна: победа Льюиса Хэмилтона', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Льюис Хэмилтон одержал победу на Гран-при Бахрейна, несмотря на сильную конкуренцию.', 'dfsd'),
('Формула 1', CONVERT(DATETIME, '2024-04-06 17:00:00', 120), 'Макс Ферстаппен на первом месте в Гран-при Австралии', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Макс Ферстаппен выиграл Гран-при Австралии, улучшив свои позиции в чемпионате.', 'sadf'),
('Формула 1', CONVERT(DATETIME, '2024-04-07 11:00:00', 120), 'Шарль Леклер победил на Гран-при Монако', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Шарль Леклер завоевал победу на Гран-при Монако, продемонстрировав отличную стратегию.', 'tyiltuil'),
('Формула 1', CONVERT(DATETIME, '2024-04-08 12:30:00', 120), 'Феррари демонстрирует отличную форму', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Феррари продемонстрировала отличную форму на тестах в Барселоне перед новым сезоном.', 'ert'),

-- Новости о волейболе
('Волейбол', CONVERT(DATETIME, '2024-04-05 18:30:00', 120), 'Сборная России победила в чемпионате Европы', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сборная России по волейболу победила в финале чемпионата Европы, обыграв Италию в решающем матче.', 'pppklsdkf'),
('Волейбол', CONVERT(DATETIME, '2024-04-06 20:00:00', 120), 'Бразилия выигрывает Кубок мира', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Бразилия выиграла Кубок мира, одержав победу над Францией в финале.', 'wqrwrew'),
('Волейбол', CONVERT(DATETIME, '2024-04-07 21:30:00', 120), 'Сербия обыгрывает США в полуфинале', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сербия победила США в полуфинале мирового турнира по волейболу с результатом 3:0.', 'erterterter'),
('Волейбол', CONVERT(DATETIME, '2024-04-08 22:00:00', 120), 'Италия побеждает в турнире в Турции', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Италия завоевала титул победителя турнира в Турции, выиграв финал с результатом 3:1.', 'q	dsfsdf'),

-- Дополнительные новости для примера
('Футбол', CONVERT(DATETIME, '2024-04-05 12:30:00', 120), 'Манчестер Юнайтед обыгрывает Арсенал в Английской Премьер-Лиге', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Манчестер Юнайтед победил Арсенал 2:1 в напряженном матче Английской Премьер-Лиги.', 'манчестер юнайтед победить арсенал напрячь матч английский премьер лига'),
('Футбол', CONVERT(DATETIME, '2024-04-05 12:31:00', 120), 'Манчестер Юнайтед обыгрывает Арсенал в Английской Премьер-Лиге', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Манчестер победил победил Арсенал 2:1 в напряженном матче Английской Премьер-Лиги.', 'манчестер юнайтед победить арсенал напрячь матч английский премьер лига'),
('Футбол', CONVERT(DATETIME, '2024-04-05 12:32:00', 120), 'Манчестер Юнайтед обыгрывает Арсенал в Английской Премьер-Лиге', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Манчестер Юнайтед победил Арсенал 2:1 в беда матче Английской Премьер-Лиги.', 'манчестер юнайтед победить арсенал напрячь матч английский премьер лига'),
('Футбол', CONVERT(DATETIME, '2024-04-05 12:33:00', 120), 'Манчестер Юнайтед обыгрывает Арсенал в Английской Премьер-Лиге', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Манчестер Юнайтед победил победили 2:1 в напряженном матче Английской Премьер-Лиги.', 'манчестер юнайтед победить арсенал напрячь матч английский премьер лига');






INSERT INTO news_tags (NewsDateTime, Tag) 
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), 'футбол'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), 'футбол'),
(CONVERT(DATETIME, '2024-04-07 16:30:00', 120), 'футбол'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), 'футбол'),
(CONVERT(DATETIME, '2024-04-05 20:00:00', 120), 'баскетбол'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'баскетбол'),
(CONVERT(DATETIME, '2024-04-07 22:00:00', 120), 'баскетбол'),
(CONVERT(DATETIME, '2024-04-08 23:30:00', 120), 'баскетбол'),
(CONVERT(DATETIME, '2024-04-05 17:00:00', 120), 'теннис'),
(CONVERT(DATETIME, '2024-04-06 14:30:00', 120), 'теннис'),
(CONVERT(DATETIME, '2024-04-07 16:00:00', 120), 'теннис'),
(CONVERT(DATETIME, '2024-04-08 18:45:00', 120), 'теннис'),
(CONVERT(DATETIME, '2024-04-05 19:15:00', 120), 'хоккей'),
(CONVERT(DATETIME, '2024-04-06 21:00:00', 120), 'хоккей'),
(CONVERT(DATETIME, '2024-04-07 22:30:00', 120), 'хоккей'),
(CONVERT(DATETIME, '2024-04-08 23:45:00', 120), 'хоккей'),
(CONVERT(DATETIME, '2024-04-05 15:30:00', 120), 'формула 1'),
(CONVERT(DATETIME, '2024-04-06 17:00:00', 120), 'формула 1'),
(CONVERT(DATETIME, '2024-04-07 11:00:00', 120), 'формула 1'),
(CONVERT(DATETIME, '2024-04-08 12:30:00', 120), 'формула 1'),
(CONVERT(DATETIME, '2024-04-05 18:30:00', 120), 'волейбол'),
(CONVERT(DATETIME, '2024-04-06 20:00:00', 120), 'волейбол'),
(CONVERT(DATETIME, '2024-04-07 21:30:00', 120), 'волейбол'),
(CONVERT(DATETIME, '2024-04-08 22:00:00', 120), 'волейбол'),
(CONVERT(DATETIME, '2024-04-05 12:30:00', 120), 'футбол');



INSERT INTO NewsComments (NewsDateTime, CommentDateTime, CommentText, UserEmail)
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), GETDATE(), 'Крутая победа Зенита!', 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), GETDATE(), 'Ливерпуль просто уничтожил Сити!', '5ftdtde@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 22:30:00', 120), GETDATE(), 'Металлург красавчики!', 'hhhhhh@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), GETDATE(), 'ПСЖ снова в деле.', 'fffffffff@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 20:00:00', 120), GETDATE(), 'Бразильцы не оставили шансов.', 'dddddd@gmail.com');



INSERT INTO NewsLike (NewsDateTime, UserEmail)
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 16:30:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-05 20:00:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 22:00:00', 120), 'ddddddd@gmail.com')



go
CREATE OR ALTER PROCEDURE [dbo].[LikesCount]
    @newsDateTime DATETIME,
    @LikesCount INT OUTPUT
AS
BEGIN
    SELECT @LikesCount = COUNT(*)
    FROM NewsLike
    WHERE NewsDateTime = @newsDateTime;
END
GO


DECLARE @date DATETIME = CONVERT(DATETIME, '2024-04-06 21:30:00', 120);
DECLARE @LikeCount INT;

EXEC [dbo].[LikesCount] @newsDateTime = @date, @LikesCount = @LikeCount OUTPUT;

SELECT @LikeCount AS LikeCount;

USE KorSport
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE OR ALTER PROCEDURE [dbo].[CountComments]
    @newsDateTime DATETIME,
    @CommentCount INT OUTPUT
AS
BEGIN
    SELECT @CommentCount = COUNT(*)
    FROM NewsComments 
    WHERE NewsDateTime = @newsDateTime;
END
GO


CREATE OR ALTER PROCEDURE [dbo].[LikesCount]
    @newsDateTime DATETIME,
    @LikesCount INT OUTPUT
AS
BEGIN
    SELECT @LikesCount = COUNT(*)
    FROM NewsLike nl
    JOIN News n ON nl.NewsDateTime = n.DateTime
    WHERE n.DateTime = @newsDateTime;
END
GO


DECLARE @date DATETIME = CONVERT(DATETIME, '2024-04-06 21:30:00', 120);
DECLARE @LikeCount INT;

EXEC [dbo].[LikesCount] @newsDateTime = @date, @LikesCount = @LikeCount OUTPUT;
SELECT @LikeCount AS LikeCount;


DECLARE @CommentCount INT;
EXEC [dbo].[CountComments] @newsDateTime = @date, @CommentCount = @CommentCount OUTPUT;
SELECT @CommentCount AS CommentCount;






-- 1. Удалим полнотекстовый индекс, если есть
IF EXISTS (SELECT * FROM sys.fulltext_indexes WHERE object_id = OBJECT_ID('News'))
    DROP FULLTEXT INDEX ON News;
GO

-- 2. Удалим уникальный индекс, если был
IF EXISTS (SELECT * FROM sys.indexes WHERE name = 'PK_News')
    DROP INDEX PK_News ON News;
GO

-- 3. Добавим техническую колонку FTS_key, если не существует
IF COL_LENGTH('News', 'FTS_key') IS NULL
BEGIN
    ALTER TABLE News ADD FTS_key INT IDENTITY(1,1);
END
GO

-- 4. Создадим уникальный индекс по FTS_key (требуется для FTS)
CREATE UNIQUE INDEX PK_News ON News(FTS_key);
GO

-- 5. Удалим каталог полнотекстового поиска, если уже есть
IF EXISTS (SELECT * FROM sys.fulltext_catalogs WHERE name = 'ftCatalog')
    DROP FULLTEXT CATALOG ftCatalog;
GO

-- 6. Создадим новый каталог полнотекстового поиска
CREATE FULLTEXT CATALOG ftCatalog AS DEFAULT;
GO

-- 7. Создадим полнотекстовый индекс (укажем язык 1049 — русский)
CREATE FULLTEXT INDEX ON News(ArticleText LANGUAGE 1049)
    KEY INDEX PK_News ON ftCatalog;
GO



CREATE OR ALTER PROCEDURE SearchNews
    @search NVARCHAR(4000) = NULL,
    @sport NVARCHAR(500) = NULL,
    @PageNumber INT = 1,
    @PageSize INT = 10
AS
BEGIN
    SET NOCOUNT ON;

    PRINT @search
    PRINT @sport
    PRINT @PageNumber
    PRINT @PageSize

    DECLARE @Offset INT = (@PageNumber - 1) * @PageSize;

    IF @search IS NULL OR @search = ''
    BEGIN
        -- Только по спорту
        SELECT *
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE IF @sport IS NULL OR @sport = ''
    BEGIN
        -- Только по тексту
        SELECT *
        FROM News
        WHERE CONTAINS(ArticleText, @search)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE
    BEGIN
        -- По тексту и спорту
        SELECT *
        FROM News
        WHERE CONTAINS(ArticleText, @search)
          AND Sport = @sport
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
END
GO





CREATE OR ALTER PROCEDURE CountNews
    @search NVARCHAR(4000) = NULL,
    @sport NVARCHAR(500) = NULL,
    @total INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    print @search
    print @sport

    IF @search IS NULL OR @search = ''
    BEGIN
        -- Если поисковый запрос пуст, считаем по виду спорта (если он задан)
        SELECT @total = COUNT(*)
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport);
    END
    ELSE
    BEGIN
        -- Если поисковый запрос не пуст, считаем по поисковому запросу и виду спорта (если задан)
        SELECT @total = COUNT(*)
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport)
          AND CONTAINS(ArticleText, @search);
    END
END
GO




CREATE OR ALTER PROCEDURE SearchUserByEmail
	@email NVARCHAR(200)
AS
BEGIN
	 SELECT U.UserEmail, U.UserImage
    FROM Users U
    WHERE LOWER(U.UserEmail) = LOWER(@Email)
end



DELETE FROM News
WHERE Title IS NULL
   OR ArticleText IS NULL
   OR ImageId IS NULL
   OR Sport IS NULL
   OR DateTime IS NULL;


