use KorSport

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
    FOREIGN KEY (NewsDateTime) REFERENCES News(DateTime) ON DELETE CASCADE,
    FOREIGN KEY (UserEmail) REFERENCES Users(UserEmail) ON DELETE CASCADE
);


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
INSERT INTO News (Sport, DateTime, Title, ImageId, ArticleText)
VALUES 
-- ������� � �������
('������', CONVERT(DATETIME, '2024-04-05 14:00:00', 120), '����� ���������� ������� � �����', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '����� ������� ������� 3:1 � ������ ���������� �����, ��������� ���� ��������� � ���������� ������.'),
('������', CONVERT(DATETIME, '2024-04-06 19:00:00', 120), '��������� ��������� ��������� ����', '7wkpxki9yofar3hmkfm08s5yox8mz54b.jpg', '��������� ������� � ��������� ���� � ����������� 4:1, ����������������� �������� ��������� ���.'),
('������', CONVERT(DATETIME, '2024-04-07 16:30:00', 120), '������� ���������� � ����� � ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������� ������� ������ � ����� � ������, ������� 2:0 � ������� ����� �� ������.'),
('������', CONVERT(DATETIME, '2024-04-08 18:00:00', 120), '��� ������� ���� � ���� 1', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', '��������� ���-������ ������� ���� � ����������� 3:1 � ������ ����� ���������� �������.'),

-- ������� � ����������
('���������', CONVERT(DATETIME, '2024-04-05 20:00:00', 120), '���� ��������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� �������� ������� � ����������� 115-103 � ����������� ��������.'),
('���������', CONVERT(DATETIME, '2024-04-06 21:30:00', 120), '������ �������� � ������� � ����� �� ����-���', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� ������� � ������� 118-112, ������� ���� ������� ��� ������ � ����-���.'),
('���������', CONVERT(DATETIME, '2024-04-07 22:00:00', 120), '������� ���� ��������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ���� �������� ��������� ������ ��� �������� 120-105.'),
('���������', CONVERT(DATETIME, '2024-04-08 23:30:00', 120), '������ ������� ����� �� ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������ ������� �������� ������� ������� 125-98, ��������� ���� ����� ����� � ������.'),

-- ������� � �������
('������', CONVERT(DATETIME, '2024-04-05 17:00:00', 120), '������ ������� ������� � ����� ��������� ���������� �������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ ������� ����� ����� � ����� ��������� ���������� �������, ������� ������� ������ � ����������.'),
('������', CONVERT(DATETIME, '2024-04-06 14:30:00', 120), '���� ������� ���������� ������ � ����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� ������� �������� ������ �� ������� � ����, ������� ������ ����� � ������.'),
('������', CONVERT(DATETIME, '2024-04-07 16:00:00', 120), '������ �������� ������ ������� � ������ ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������ �������� � ������ �������������� ��������� �������, ������� ������ �������.'),
('������', CONVERT(DATETIME, '2024-04-08 18:45:00', 120), '�������� � ������ � ����������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� �������� � ������� ������ ���������� � ���������� ������� � �������.'),

-- ������� � ������
('������', CONVERT(DATETIME, '2024-04-05 19:15:00', 120), '������ ���������� �� ���� � ����� ��������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� �� ���� � 1/4 ������ ����� ��������, ������� ������ ������ 3:2.'),
('������', CONVERT(DATETIME, '2024-04-06 21:00:00', 120), '���� �������� ������ � ����������', 'kr1sdghuqwljgtgftxfvrv2o37g4k6ut.jpg', '���� ������� ��������� � ��������� 4:3 � ����� ����� ��������.'),
('������', CONVERT(DATETIME, '2024-04-07 22:30:00', 120), '��������� ���������� ������� �����', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '��������� ������� ������ ��� ��������� ������� 5:1 � ������������� �����.'),
('������', CONVERT(DATETIME, '2024-04-08 23:45:00', 120), '������ ������ ������� � �����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ ������ �������� ������ ��� ��������� � ����� � ����� ����� ��������.'),

-- ������� � ������� 1
('������� 1', CONVERT(DATETIME, '2024-04-05 15:30:00', 120), '����-��� ��������: ������ ������ ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� �������� ������� ������ �� ����-��� ��������, �������� �� ������� �����������.'),
('������� 1', CONVERT(DATETIME, '2024-04-06 17:00:00', 120), '���� ���������� �� ������ ����� � ����-��� ���������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '���� ���������� ������� ����-��� ���������, ������� ���� ������� � ����������.'),
('������� 1', CONVERT(DATETIME, '2024-04-07 11:00:00', 120), '����� ������ ������� �� ����-��� ������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '����� ������ �������� ������ �� ����-��� ������, ����������������� �������� ���������.'),
('������� 1', CONVERT(DATETIME, '2024-04-08 12:30:00', 120), '������� ������������� �������� �����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������������������ �������� ����� �� ������ � ��������� ����� ����� �������.'),

-- ������� � ���������
('��������', CONVERT(DATETIME, '2024-04-05 18:30:00', 120), '������� ������ �������� � ���������� ������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������� ������ �� ��������� �������� � ������ ���������� ������, ������� ������ � �������� �����.'),
('��������', CONVERT(DATETIME, '2024-04-06 20:00:00', 120), '�������� ���������� ����� ����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '�������� �������� ����� ����, ������� ������ ��� �������� � ������.'),
('��������', CONVERT(DATETIME, '2024-04-07 21:30:00', 120), '������ ���������� ��� � ����������', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '������ �������� ��� � ���������� �������� ������� �� ��������� � ����������� 3:0.'),
('��������', CONVERT(DATETIME, '2024-04-08 22:00:00', 120), '������ ��������� � ������� � ������', '1vj1fazpnqpov4rl7l36oc0k3ri7s6ff.jpg', '������ ��������� ����� ���������� ������� � ������, ������� ����� � ����������� 3:1.'),

-- �������������� ������� ��� �������
('������', CONVERT(DATETIME, '2024-04-05 12:30:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'unops22spedaia05oqkiyvm8x2xm5p31.jpg', '��������� ������� ������� ������� 2:1 � ����������� ����� ���������� �������-����.')





-- ������� ����� ��� ��������
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






-- ������� ������������ � ��������
INSERT INTO NewsComments (NewsDateTime, CommentDateTime, CommentText, UserEmail)
VALUES
(CONVERT(DATETIME, '2024-04-05 14:00:00', 120), CONVERT(DATETIME, '2024-04-05 14:15:00', 120), '�������� ����, ����� �������!', 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 19:00:00', 120), CONVERT(DATETIME, '2024-04-06 19:30:00', 120), '��������� ������� �������� ������!', 'dddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 16:30:00', 120), CONVERT(DATETIME, '2024-04-07 17:00:00', 120), '������� ��� �� ������!', '5ftdtde@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 18:00:00', 120), CONVERT(DATETIME, '2024-04-08 18:30:00', 120), '������� ���� ���!', 'hhhhhh@gmail.com'),
(CONVERT(DATETIME, '2024-04-05 20:00:00', 120), CONVERT(DATETIME, '2024-04-05 20:30:00', 120), '���� ������ � �����!', 'fffffffff@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 21:30:00', 120), CONVERT(DATETIME, '2024-04-06 22:00:00', 120), '������ ����� � ����-���!', 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 22:00:00', 120), CONVERT(DATETIME, '2024-04-07 22:30:00', 120), '������� ���� �� �������� ������ �������!', 'dddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 23:30:00', 120), CONVERT(DATETIME, '2024-04-08 23:45:00', 120), '������ �������� ���������!', '5ftdtde@gmail.com'),
(CONVERT(DATETIME, '2024-04-05 17:00:00', 120), CONVERT(DATETIME, '2024-04-05 17:30:00', 120), '������� �� ������!', 'hhhhhh@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 14:30:00', 120), CONVERT(DATETIME, '2024-04-06 14:45:00', 120), '������� �������� �����!', 'fffffffff@gmail.com'),
(CONVERT(DATETIME, '2024-04-07 16:00:00', 120), CONVERT(DATETIME, '2024-04-07 16:30:00', 120), '������ �������� ����������!', 'ddddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-08 18:45:00', 120), CONVERT(DATETIME, '2024-04-08 19:00:00', 120), '�������� � ������ � ��� ������ ���������!', 'dddddd@gmail.com'),
(CONVERT(DATETIME, '2024-04-05 19:15:00', 120), CONVERT(DATETIME, '2024-04-05 19:45:00', 120), '������ ������� ���� �����!', '5ftdtde@gmail.com'),
(CONVERT(DATETIME, '2024-04-06 21:00:00', 120), CONVERT(DATETIME, '2024-04-06 21:30:00', 120), '���� ������� � ���������!', 'hhhhhh@gmail.com');
