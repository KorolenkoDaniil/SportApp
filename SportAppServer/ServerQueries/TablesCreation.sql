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






-- ������� ������ � ������� Users
INSERT INTO Users (UserEmail, UserImage)
VALUES 
('ddddddd@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('dddddd@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('5ftdtde@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('hhhhhh@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg'),
('fffffffff@gmail.com', '0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg');



-- ������� ������ � ������� News
INSERT INTO News (Sport, DateTime, Title, ImageId, ArticleText, TextAfterLemmatize)
VALUES 
-- ������� � �������
('������', CONVERT(DATETIME, '2024-04-05 14:00:00', 120), '����� ���������� ������� � �����', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '����� ������� ������� 3:1 � ������ ���������� �����, ��������� ���� ��������� � ���������� ������.', '����� �������� ������� ������ ���������� ����� ���������� ��������� ��������� ������'),
('������', CONVERT(DATETIME, '2024-04-06 19:00:00', 120), '��������� ��������� ��������� ����', '7wkpxki9yofar3hmkfm08s5yox8mz54b.jpg', '��������� ������� � ��������� ���� � ����������� 4:1, ����������������� �������� ��������� ���.', '��������� �������� ��������� ���� ��������� ������������������ �������� ��������� ���'),
('������', CONVERT(DATETIME, '2024-04-07 16:30:00', 120), '������� ���������� � ����� � ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������� ������� ������ � ����� � ������, ������� 2:0 � ������� ����� �� ������.', '������� �������� ������ ����� �������� �������� ������� ���� �����'),
('������', CONVERT(DATETIME, '2024-04-08 18:00:00', 120), '��� ������� ���� � ���� 1', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', '��������� ���-������ ������� ���� � ����������� 3:1 � ������ ����� ���������� �������.', '��������� ��� ������ �������� ���� ��������� ������ ���� ��������� �������'),

-- ������� � ����������
('���������', CONVERT(DATETIME, '2024-04-05 20:00:00', 120), '���� ��������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� �������� ������� � ����������� 115-103 � ����������� ��������.', '���� �������� ������� ��������� ������� ��������'),
('���������', CONVERT(DATETIME, '2024-04-06 21:30:00', 120), '������ �������� � ������� � ����� �� ����-���', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� ������� � ������� 118-112, ������� ���� ������� ��� ������ � ����-���.', '��������'),
('���������', CONVERT(DATETIME, '2024-04-07 22:00:00', 120), '������� ���� ��������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ���� �������� ��������� ������ ��� �������� 120-105.', '������� ���� �������� ��������� ������ �������'),
('���������', CONVERT(DATETIME, '2024-04-08 23:30:00', 120), '������ ������� ����� �� ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������ ������� �������� ������� ������� 125-98, ��������� ���� ����� ����� � ������.', '������ ������� �������� ������� ������� ���������� ����� ������ �����'),

-- ������� � �������
('������', CONVERT(DATETIME, '2024-04-05 17:00:00', 120), '������ ������� ������� � ����� ��������� ���������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ ������� ����� ����� � ����� ��������� ���������� �������, ������� ������� ������ � ����������.', '������ ������� ����� ����� ����� �������� ��������� ������� �������� ������� ������ ���������'),
('������', CONVERT(DATETIME, '2024-04-06 14:30:00', 120), '���� ������� ���������� ������ � ����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� ������� �������� ������ �� ������� � ����, ������� ������ ����� � ������.', '��� ������� �������� ������ ������ ��� �������� ����� ����� �����'),
('������', CONVERT(DATETIME, '2024-04-07 16:00:00', 120), '������ �������� ������ ������� � ������ ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������ �������� � ������ �������������� ��������� �������, ������� ������ �������.', '������� ������ �������� ����� ������������� �������� ������ �������� ������ �������'),
('������', CONVERT(DATETIME, '2024-04-08 18:45:00', 120), '�������� � ������ � ����������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� �������� � ������� ������ ���������� � ���������� ������� � �������.', '����� �������� ������� ������ ����������� ��������� ������ ������'),

-- ������� � ������
('������', CONVERT(DATETIME, '2024-04-05 19:15:00', 120), '������ ���������� �� ���� � ����� ��������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� �� ���� � 1/4 ������ ����� ��������, ������� ������ ������ 3:2.', '������ �������� �� ���� ����� ����� ������� �������� ������ ������'),
('������', CONVERT(DATETIME, '2024-04-06 21:00:00', 120), '���� �������� ������ � ����������', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', '���� ������� ��������� � ��������� 4:3 � ����� ����� ��������.', '���� �������� ��������� �������� ���� ����� �������'),
('������', CONVERT(DATETIME, '2024-04-07 22:30:00', 120), '��������� ���������� ������� �����', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '��������� ������� ������ ��� ��������� ������� 5:1 � ������������� �����.', '��������� �������� ������ ������� ����� ����������� ����'),
('������', CONVERT(DATETIME, '2024-04-08 23:45:00', 120), '������ ������ ������� � �����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ ������ �������� ������ ��� ��������� � ����� � ����� ����� ��������.', '������ ������ �������� ������ ������� ����� ����� ����� �������'),

-- ������� � ������� 1
('������� 1', CONVERT(DATETIME, '2024-04-05 15:30:00', 120), '����-��� ��������: ������ ������ ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� �������� ������� ������ �� ����-��� ��������, �������� �� ������� �����������.', 'dfsd'),
('������� 1', CONVERT(DATETIME, '2024-04-06 17:00:00', 120), '���� ���������� �� ������ ����� � ����-��� ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� ���������� ������� ����-��� ���������, ������� ���� ������� � ����������.', 'sadf'),
('������� 1', CONVERT(DATETIME, '2024-04-07 11:00:00', 120), '����� ������ ������� �� ����-��� ������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� ������ �������� ������ �� ����-��� ������, ����������������� �������� ���������.', 'tyiltuil'),
('������� 1', CONVERT(DATETIME, '2024-04-08 12:30:00', 120), '������� ������������� �������� �����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������������������ �������� ����� �� ������ � ��������� ����� ����� �������.', 'ert'),

-- ������� � ���������
('��������', CONVERT(DATETIME, '2024-04-05 18:30:00', 120), '������� ������ �������� � ���������� ������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������ �� ��������� �������� � ������ ���������� ������, ������� ������ � �������� �����.', 'pppklsdkf'),
('��������', CONVERT(DATETIME, '2024-04-06 20:00:00', 120), '�������� ���������� ����� ����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '�������� �������� ����� ����, ������� ������ ��� �������� � ������.', 'wqrwrew'),
('��������', CONVERT(DATETIME, '2024-04-07 21:30:00', 120), '������ ���������� ��� � ����������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� ��� � ���������� �������� ������� �� ��������� � ����������� 3:0.', 'erterterter'),
('��������', CONVERT(DATETIME, '2024-04-08 22:00:00', 120), '������ ��������� � ������� � ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������ ��������� ����� ���������� ������� � ������, ������� ����� � ����������� 3:1.', 'q	dsfsdf'),

-- �������������� ������� ��� �������
('������', CONVERT(DATETIME, '2024-04-05 12:30:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '��������� ������� ������� ������� 2:1 � ����������� ����� ���������� �������-����.', '��������� ������� �������� ������� ������� ���� ���������� ������� ����'),
('������', CONVERT(DATETIME, '2024-04-05 12:31:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '��������� ������� ������� ������� 2:1 � ����������� ����� ���������� �������-����.', '��������� ������� �������� ������� ������� ���� ���������� ������� ����'),
('������', CONVERT(DATETIME, '2024-04-05 12:32:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '��������� ������� ������� ������� 2:1 � ���� ����� ���������� �������-����.', '��������� ������� �������� ������� ������� ���� ���������� ������� ����'),
('������', CONVERT(DATETIME, '2024-04-05 12:33:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '��������� ������� ������� �������� 2:1 � ����������� ����� ���������� �������-����.', '��������� ������� �������� ������� ������� ���� ���������� ������� ����');






INSERT INTO news_tags (NewsDateTime, Tag) 
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-07 16:30:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-05 20:00:00', 120), '���������'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), '���������'),
(CONVERT(DATETIME, '2024-04-07 22:00:00', 120), '���������'),
(CONVERT(DATETIME, '2024-04-08 23:30:00', 120), '���������'),
(CONVERT(DATETIME, '2024-04-05 17:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-06 14:30:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-07 16:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-08 18:45:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-05 19:15:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-06 21:00:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-07 22:30:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-08 23:45:00', 120), '������'),
(CONVERT(DATETIME, '2024-04-05 15:30:00', 120), '������� 1'),
(CONVERT(DATETIME, '2024-04-06 17:00:00', 120), '������� 1'),
(CONVERT(DATETIME, '2024-04-07 11:00:00', 120), '������� 1'),
(CONVERT(DATETIME, '2024-04-08 12:30:00', 120), '������� 1'),
(CONVERT(DATETIME, '2024-04-05 18:30:00', 120), '��������'),
(CONVERT(DATETIME, '2024-04-06 20:00:00', 120), '��������'),
(CONVERT(DATETIME, '2024-04-07 21:30:00', 120), '��������'),
(CONVERT(DATETIME, '2024-04-08 22:00:00', 120), '��������'),
(CONVERT(DATETIME, '2024-04-05 12:30:00', 120), '������');



INSERT INTO NewsComments (NewsDateTime, CommentDateTime, CommentText, UserEmail)
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), GETDATE(), '������ ������ ������!', 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), GETDATE(), '��������� ������ ��������� ����!', '5ftdtde@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 22:30:00', 120), GETDATE(), '��������� ����������!', 'hhhhhh@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), GETDATE(), '��� ����� � ����.', 'fffffffff@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 20:00:00', 120), GETDATE(), '��������� �� �������� ������.', 'dddddd@gmail.com');



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






-- 1. ������ �������������� ������, ���� ����
IF EXISTS (SELECT * FROM sys.fulltext_indexes WHERE object_id = OBJECT_ID('News'))
    DROP FULLTEXT INDEX ON News;
GO

-- 2. ������ ���������� ������, ���� ���
IF EXISTS (SELECT * FROM sys.indexes WHERE name = 'PK_News')
    DROP INDEX PK_News ON News;
GO

-- 3. ������� ����������� ������� FTS_key, ���� �� ����������
IF COL_LENGTH('News', 'FTS_key') IS NULL
BEGIN
    ALTER TABLE News ADD FTS_key INT IDENTITY(1,1);
END
GO

-- 4. �������� ���������� ������ �� FTS_key (��������� ��� FTS)
CREATE UNIQUE INDEX PK_News ON News(FTS_key);
GO

-- 5. ������ ������� ��������������� ������, ���� ��� ����
IF EXISTS (SELECT * FROM sys.fulltext_catalogs WHERE name = 'ftCatalog')
    DROP FULLTEXT CATALOG ftCatalog;
GO

-- 6. �������� ����� ������� ��������������� ������
CREATE FULLTEXT CATALOG ftCatalog AS DEFAULT;
GO

-- 7. �������� �������������� ������ (������ ���� 1049 � �������)
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
        -- ������ �� ������
        SELECT *
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE IF @sport IS NULL OR @sport = ''
    BEGIN
        -- ������ �� ������
        SELECT *
        FROM News
        WHERE CONTAINS(ArticleText, @search)
        ORDER BY DateTime DESC
        OFFSET @Offset ROWS FETCH NEXT @PageSize ROWS ONLY;
    END
    ELSE
    BEGIN
        -- �� ������ � ������
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
        -- ���� ��������� ������ ����, ������� �� ���� ������ (���� �� �����)
        SELECT @total = COUNT(*)
        FROM News
        WHERE (@sport IS NULL OR Sport = @sport);
    END
    ELSE
    BEGIN
        -- ���� ��������� ������ �� ����, ������� �� ���������� ������� � ���� ������ (���� �����)
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


