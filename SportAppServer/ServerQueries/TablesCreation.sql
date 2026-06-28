use KorSport


DROP TABLE IF EXISTS CommentLikes;
DROP TABLE IF EXISTS NewsLike;
DROP TABLE IF EXISTS NewsComments;
DROP TABLE IF EXISTS news_tags;
DROP TABLE IF EXISTS News;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS Users;



CREATE TABLE News (
    DateTime DATETIME NOT NULL PRIMARY KEY,
    Sport NVARCHAR(255) NOT NULL, 
    Title NVARCHAR(200) NOT NULL,
    ImageId NVARCHAR(300) NOT NULL,
	ArticleText NVARCHAR(MAX),
	TextAfterLemmatize NVARCHAR(MAX),
	FTS_key Int Identity(1, 1) not null
);

CREATE TABLE Users (
    UserEmail NVARCHAR(255) NOT NULL Primary key, 
	UserImage NVARCHAR(1000),
	IsWhiteTheme Bit not null DEFAULT 1 
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
	LikesCount Int not null default 0,
    FOREIGN KEY (NewsDateTime) REFERENCES News(DateTime) ON DELETE CASCADE,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail)  ON UPDATE No Action ON DELETE  No Action 
);

CREATE TABLE NewsLike (
    LikeId INT IDENTITY(1,1) PRIMARY KEY,
    NewsDateTime DATETIME NOT NULL,
    UserEmail NVARCHAR(255) NOT NULL,
    FOREIGN KEY (NewsDateTime) REFERENCES News(DateTime) ON DELETE CASCADE,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail)  ON UPDATE No Action ON DELETE  No Action 
);



CREATE TABLE CommentLikes (
    CommentLikeId INT IDENTITY(1,1) PRIMARY KEY,                  
    CommentId INT NOT NULL,                                       
    LikedByUserEmail NVARCHAR(255) NOT NULL,                      

    FOREIGN KEY (CommentId) REFERENCES NewsComments (CommentId) ON DELETE CASCADE,
    FOREIGN KEY (LikedByUserEmail) REFERENCES Users(UserEmail) ON UPDATE No Action ON DELETE  No Action ,

    CONSTRAINT UQ_CommentLikes_CommentUser UNIQUE (CommentId, LikedByUserEmail)
);


CREATE TABLE messages (
    MessageID INT IDENTITY(1, 1) PRIMARY KEY,
    UserEmail NVARCHAR(255) NOT NULL,
    MessageText NVARCHAR(MAX) NOT NULL,
    IsAIAnswer BIT NOT NULL,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail) ON UPDATE No Action ON DELETE  No Action 
);




CREATE INDEX AIChatEmailIndex on messages (UserEmail)
create index NewCommentsIndex on NewsComments (NewsDateTime)
create index NewLikesIndex on NewsLike (NewsDateTime)
create index NewsSportIndex on News (Sport)

--drop index if exists NewCommentsIndex on NewsComments



alter table news_tags 
add constraint FK_News_Tags
foreign key (NewsDateTime) references News(DateTime)
on delete cascade 




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






-- 1. ”далим полнотекстовый индекс, если есть
IF EXISTS (SELECT * FROM sys.fulltext_indexes WHERE object_id = OBJECT_ID('News'))
    DROP FULLTEXT INDEX ON News;
GO

-- 2. ”далим уникальный индекс, если был
IF EXISTS (SELECT * FROM sys.indexes WHERE name = 'PK_News')
    DROP INDEX PK_News ON News;
GO

-- 3. ƒобавим техническую колонку FTS_key, если не существует
IF COL_LENGTH('News', 'FTS_key') IS NULL
BEGIN
    ALTER TABLE News ADD FTS_key INT IDENTITY(1,1);
END
GO

-- 4. —оздадим уникальный индекс по FTS_key (требуетс€ дл€ FTS)
CREATE UNIQUE INDEX PK_News ON News(FTS_key);
GO

-- 5. ”далим каталог полнотекстового поиска, если уже есть
IF EXISTS (SELECT * FROM sys.fulltext_catalogs WHERE name = 'ftCatalog')
    DROP FULLTEXT CATALOG ftCatalog;
GO

-- 6. —оздадим новый каталог полнотекстового поиска
CREATE FULLTEXT CATALOG ftCatalog AS DEFAULT;
GO

-- 7. —оздадим полнотекстовый индекс (укажем €зык 1049 Ч русский)
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

   DECLARE @Offset INT = (@PageNumber - 1) * @PageSize;

    IF @search IS NULL OR @search = ''
    BEGIN
        -- “олько по спорту
        SELECT *
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE IF @sport IS NULL OR @sport = ''
    BEGIN
        -- “олько по тексту
        SELECT *
        FROM News
        WHERE CONTAINS(ArticleText, @search)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE
    BEGIN
        -- ѕо тексту и спорту
        SELECT *
        FROM News
        WHERE CONTAINS(ArticleText, @search)
          AND Sport = @sport
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
END
GO



CREATE OR ALTER PROCEDURE TakePaginatedNews
    @PageNumber INT = 1,
    @PageSize INT = 10
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Offset INT = @PageNumber * @PageSize;
       
	SELECT *
        FROM News
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
END
GO



GO
CREATE OR ALTER PROCEDURE TakePaginatedMessages 
    @PageNumber INT,
    @PageSize INT,
    @Email NVARCHAR(100)
AS
BEGIN 
    SET NOCOUNT ON;

    DECLARE @Offset INT = (@PageNumber) * @PageSize;

    SELECT * FROM messages M
    WHERE M.UserEmail = @Email
    ORDER BY MessageID 
    OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
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
        -- ≈сли поисковый запрос пуст, считаем по виду спорта (если он задан)
        SELECT @total = COUNT(*)
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport);
    END
    ELSE
    BEGIN
        -- ≈сли поисковый запрос не пуст, считаем по поисковому запросу и виду спорта (если задан)
        SELECT @total = COUNT(*)
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport)
          AND CONTAINS(ArticleText, @search);
    END
END
GO



go
	CREATE OR ALTER PROCEDURE SearchUserByEmail
		@email NVARCHAR(200)
	AS
	BEGIN
		SELECT *
		FROM Users U
			WHERE LOWER(U.UserEmail) = LOWER(@Email)
	end
go


CREATE INDEX NewsDateTimeIndex ON News(DateTime); -- на случай, если часто соедин€ешь по DateTime
CREATE INDEX TagIndex ON news_tags(Tag);






go
CREATE OR ALTER PROC DeleteNUllValue 
AS 
BEGIN
	DELETE FROM News
		WHERE Title IS NULL
			OR ArticleText IS NULL
			OR ImageId IS NULL
			OR Sport IS NULL
			OR DateTime IS NULL;
END
go



