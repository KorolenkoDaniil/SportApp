use KorSport

DROP TABLE IF EXISTS CommentLike;
DROP TABLE IF EXISTS NewsComments;
DROP TABLE IF EXISTS NewsLike;
DROP TABLE IF EXISTS news_tags;
DROP TABLE IF EXISTS News;
DROP TABLE IF EXISTS Users;




CREATE TABLE News (
    Sport NVARCHAR(255) NOT NULL, 
    DateTime DATETIME NOT NULL PRIMARY KEY,
    Title NVARCHAR(MAX) NOT NULL,
    ImageId NVARCHAR(MAX) NOT NULL,
	ArticleText NVARCHAR(MAX),
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
    CommentLikeId INT IDENTITY(1,1) PRIMARY KEY,                  -- Уникальный ID лайка
    CommentId INT NOT NULL,                                       -- Ссылка на комментарий
    LikedByUserEmail NVARCHAR(255) NOT NULL,                      -- Кто лайкнул

    FOREIGN KEY (CommentId) REFERENCES NewsComments (CommentId) ON DELETE CASCADE,
    FOREIGN KEY (LikedByUserEmail) REFERENCES Users(UserEmail),

    CONSTRAINT UQ_CommentLikes_CommentUser UNIQUE (CommentId, LikedByUserEmail)
);



create index NewCommentsIndex on NewsComments (NewsDateTime)
create index NewLikesIndex on NewsLike (NewsDateTime)

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
INSERT INTO News (Sport, DateTime, Title, ImageId, ArticleText)
VALUES 
-- Новости о футболе
('Футбол', CONVERT(DATETIME, '2024-04-05 14:00:00', 120), 'Зенит обыгрывает Спартак в дерби', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Зенит победил Спартак 3:1 в важном московском дерби, обеспечив себе лидерство в чемпионате России.'),
('Футбол', CONVERT(DATETIME, '2024-04-06 19:00:00', 120), 'Ливерпуль разрывает Манчестер Сити', '7wkpxki9yofar3hmkfm08s5yox8mz54b.jpg', 'Ливерпуль выиграл у Манчестер Сити с результатом 4:1, продемонстрировав отличный командный дух.'),
('Футбол', CONVERT(DATETIME, '2024-04-07 16:30:00', 120), 'Ювентус выигрывает в дерби с Наполи', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Ювентус одержал победу в дерби с Наполи, выиграв 2:0 в сложном матче на выезде.'),
('Футбол', CONVERT(DATETIME, '2024-04-08 18:00:00', 120), 'ПСЖ победил Лион в Лиге 1', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', 'Парижский Сен-Жермен победил Лион с результатом 3:1 в важном матче чемпионата Франции.'),

-- Новости о баскетболе
('Баскетбол', CONVERT(DATETIME, '2024-04-05 20:00:00', 120), 'Никс побеждают Хьюстон', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Никс победили Хьюстон с результатом 115-103 в напряженном поединке.'),
('Баскетбол', CONVERT(DATETIME, '2024-04-06 21:30:00', 120), 'Даллас Маверикс и Лейкерс в битве за плей-офф', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Даллас Маверикс выиграл у Лейкерс 118-112, укрепив свои позиции для выхода в плей-офф.'),
('Баскетбол', CONVERT(DATETIME, '2024-04-07 22:00:00', 120), 'Милуоки Бакс побеждают Индиану', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Милуоки Бакс одержали уверенную победу над Индианой 120-105.'),
('Баскетбол', CONVERT(DATETIME, '2024-04-08 23:30:00', 120), 'Бостон Селтикс снова на высоте', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Бостон Селтикс победили Детройт Пистонс 125-98, продолжив свою серию побед в сезоне.'),

-- Новости о теннисе
('Теннис', CONVERT(DATETIME, '2024-04-05 17:00:00', 120), 'Роджер Федерер выходит в финал Открытого чемпионата Франции', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Роджер Федерер вновь вышел в финал Открытого чемпионата Франции, победив Рафаэля Надаля в полуфинале.'),
('Теннис', CONVERT(DATETIME, '2024-04-06 14:30:00', 120), 'Кики Бертенс выигрывает турнир в Риме', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Кики Бертенс одержала победу на турнире в Риме, обыграв Симону Халеп в финале.'),
('Теннис', CONVERT(DATETIME, '2024-04-07 16:00:00', 120), 'Кербер победила Серену Уильямс в финале Австралии', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Ангелик Кербер выиграла в финале Австралийского Открытого турнира, победив Серену Уильямс.'),
('Теннис', CONVERT(DATETIME, '2024-04-08 18:45:00', 120), 'Джокович и Надаль в полуфинале', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Новак Джокович и Рафаэль Надаль встретятся в полуфинале турнира в Мадриде.'),

-- Новости о хоккее
('Хоккей', CONVERT(DATETIME, '2024-04-05 19:15:00', 120), 'Сибирь обыгрывает Ак Барс в Кубке Гагарина', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сибирь победила Ак Барс в 1/4 финала Кубка Гагарина, одержав важную победу 3:2.'),
('Хоккей', CONVERT(DATETIME, '2024-04-06 21:00:00', 120), 'ЦСКА вырывает победу у Локомотива', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', 'ЦСКА победил Локомотив в овертайме 4:3 в матче Кубка Гагарина.'),
('Хоккей', CONVERT(DATETIME, '2024-04-07 22:30:00', 120), 'Металлург обыгрывает Салават Юлаев', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Металлург одержал победу над Салаватом Юлаевым 5:1 в захватывающем матче.'),
('Хоккей', CONVERT(DATETIME, '2024-04-08 23:45:00', 120), 'Динамо Москва выходит в финал', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Динамо Москва одержало победу над Спартаком и вышло в финал Кубка Гагарина.'),

-- Новости о Формуле 1
('Формула 1', CONVERT(DATETIME, '2024-04-05 15:30:00', 120), 'Гран-при Бахрейна: победа Льюиса Хэмилтона', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Льюис Хэмилтон одержал победу на Гран-при Бахрейна, несмотря на сильную конкуренцию.'),
('Формула 1', CONVERT(DATETIME, '2024-04-06 17:00:00', 120), 'Макс Ферстаппен на первом месте в Гран-при Австралии', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Макс Ферстаппен выиграл Гран-при Австралии, улучшив свои позиции в чемпионате.'),
('Формула 1', CONVERT(DATETIME, '2024-04-07 11:00:00', 120), 'Шарль Леклер победил на Гран-при Монако', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Шарль Леклер завоевал победу на Гран-при Монако, продемонстрировав отличную стратегию.'),
('Формула 1', CONVERT(DATETIME, '2024-04-08 12:30:00', 120), 'Феррари демонстрирует отличную форму', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Феррари продемонстрировала отличную форму на тестах в Барселоне перед новым сезоном.'),

-- Новости о волейболе
('Волейбол', CONVERT(DATETIME, '2024-04-05 18:30:00', 120), 'Сборная России победила в чемпионате Европы', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сборная России по волейболу победила в финале чемпионата Европы, обыграв Италию в решающем матче.'),
('Волейбол', CONVERT(DATETIME, '2024-04-06 20:00:00', 120), 'Бразилия выигрывает Кубок мира', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Бразилия выиграла Кубок мира, одержав победу над Францией в финале.'),
('Волейбол', CONVERT(DATETIME, '2024-04-07 21:30:00', 120), 'Сербия обыгрывает США в полуфинале', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Сербия победила США в полуфинале мирового турнира по волейболу с результатом 3:0.'),
('Волейбол', CONVERT(DATETIME, '2024-04-08 22:00:00', 120), 'Италия побеждает в турнире в Турции', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', 'Италия завоевала титул победителя турнира в Турции, выиграв финал с результатом 3:1.'),

-- Дополнительные новости для примера
('Футбол', CONVERT(DATETIME, '2024-04-05 12:30:00', 120), 'Манчестер Юнайтед обыгрывает Арсенал в Английской Премьер-Лиге', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', 'Манчестер Юнайтед победил Арсенал 2:1 в напряженном матче Английской Премьер-Лиги.')






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

/****** Object:  StoredProcedure [dbo].[CountComments]    Script Date: 15.04.2025 15:49:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Правильно изменяем процедуру, если она существует или создаем, если её нет
CREATE OR ALTER PROCEDURE [dbo].[CountComments]
    @newsDateTime DATETIME,
    @CommentCount INT OUTPUT
AS
BEGIN
    -- Подсчет комментариев по NewsDateTime
    SELECT @CommentCount = COUNT(*)
    FROM NewsComments 
    WHERE NewsDateTime = @newsDateTime;
END
GO


-- Правильно изменяем процедуру LikesCount, исправив связь с таблицей News
CREATE OR ALTER PROCEDURE [dbo].[LikesCount]
    @newsDateTime DATETIME,
    @LikesCount INT OUTPUT
AS
BEGIN
    -- Подсчет лайков для новости по NewsDateTime
    SELECT @LikesCount = COUNT(*)
    FROM NewsLike nl
    JOIN News n ON nl.NewsDateTime = n.DateTime
    WHERE n.DateTime = @newsDateTime;
END
GO


-- Пример выполнения процедуры для подсчета лайков
DECLARE @date DATETIME = CONVERT(DATETIME, '2024-04-06 21:30:00', 120);
DECLARE @LikeCount INT;

-- Вызов процедуры LikesCount
EXEC [dbo].[LikesCount] @newsDateTime = @date, @LikesCount = @LikeCount OUTPUT;

SELECT @LikeCount AS LikeCount;


-- Пример выполнения процедуры для подсчета комментариев
DECLARE @CommentCount INT;

-- Вызов процедуры CountComments
EXEC [dbo].[CountComments] @newsDateTime = @date, @CommentCount = @CommentCount OUTPUT;

SELECT @CommentCount AS CommentCount;
