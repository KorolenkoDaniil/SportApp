use KorSport


DROP TABLE IF EXISTS news_tags;
DROP TABLE IF EXISTS News;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS NewsCommets;

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

CREATE TABLE NewsCommets (
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
('������', CONVERT(DATETIME, '2024-04-05 14:00:00', 120), '����� ���������� ������� � �����', 'zenit-spartak.jpg', '����� ������� ������� 3:1 � ������ ���������� �����, ��������� ���� ��������� � ���������� ������.'),
('������', CONVERT(DATETIME, '2024-04-06 19:00:00', 120), '��������� ��������� ��������� ����', 'liverpool-city.jpg', '��������� ������� � ��������� ���� � ����������� 4:1, ����������������� �������� ��������� ���.'),
('������', CONVERT(DATETIME, '2024-04-07 16:30:00', 120), '������� ���������� � ����� � ������', 'juventus-napoli.jpg', '������� ������� ������ � ����� � ������, ������� 2:0 � ������� ����� �� ������.'),
('������', CONVERT(DATETIME, '2024-04-08 18:00:00', 120), '��� ������� ���� � ���� 1', 'psg-lyon.jpg', '��������� ���-������ ������� ���� � ����������� 3:1 � ������ ����� ���������� �������.'),

-- ������� � ����������
('���������', CONVERT(DATETIME, '2024-04-05 20:00:00', 120), '���� ��������� �������', 'knicks-houston.jpg', '���� �������� ������� � ����������� 115-103 � ����������� ��������.'),
('���������', CONVERT(DATETIME, '2024-04-06 21:30:00', 120), '������ �������� � ������� � ����� �� ����-���', 'mavericks-lakers.jpg', '������ �������� ������� � ������� 118-112, ������� ���� ������� ��� ������ � ����-���.'),
('���������', CONVERT(DATETIME, '2024-04-07 22:00:00', 120), '������� ���� ��������� �������', 'bucks-pacers.jpg', '������� ���� �������� ��������� ������ ��� �������� 120-105.'),
('���������', CONVERT(DATETIME, '2024-04-08 23:30:00', 120), '������ ������� ����� �� ������', 'celtics-pistons.jpg', '������ ������� �������� ������� ������� 125-98, ��������� ���� ����� ����� � ������.'),

-- ������� � �������
('������', CONVERT(DATETIME, '2024-04-05 17:00:00', 120), '������ ������� ������� � ����� ��������� ���������� �������', 'federer-final.jpg', '������ ������� ����� ����� � ����� ��������� ���������� �������, ������� ������� ������ � ����������.'),
('������', CONVERT(DATETIME, '2024-04-06 14:30:00', 120), '���� ������� ���������� ������ � ����', 'bertens-rome.jpg', '���� ������� �������� ������ �� ������� � ����, ������� ������ ����� � ������.'),
('������', CONVERT(DATETIME, '2024-04-07 16:00:00', 120), '������ �������� ������ ������� � ������ ���������', 'kerber-williams.jpg', '������� ������ �������� � ������ �������������� ��������� �������, ������� ������ �������.'),
('������', CONVERT(DATETIME, '2024-04-08 18:45:00', 120), '�������� � ������ � ����������', 'djokovic-nadal.jpg', '����� �������� � ������� ������ ���������� � ���������� ������� � �������.'),

-- ������� � ������
('������', CONVERT(DATETIME, '2024-04-05 19:15:00', 120), '������ ���������� �� ���� � ����� ��������', 'siberia-akbars.jpg', '������ �������� �� ���� � 1/4 ������ ����� ��������, ������� ������ ������ 3:2.'),
('������', CONVERT(DATETIME, '2024-04-06 21:00:00', 120), '���� �������� ������ � ����������', 'cska-lokomotiv.jpg', '���� ������� ��������� � ��������� 4:3 � ����� ����� ��������.'),
('������', CONVERT(DATETIME, '2024-04-07 22:30:00', 120), '��������� ���������� ������� �����', 'metallurg-salavat.jpg', '��������� ������� ������ ��� ��������� ������� 5:1 � ������������� �����.'),
('������', CONVERT(DATETIME, '2024-04-08 23:45:00', 120), '������ ������ ������� � �����', 'dynamo-moscow-final.jpg', '������ ������ �������� ������ ��� ��������� � ����� � ����� ����� ��������.'),

-- ������� � ������� 1
('������� 1', CONVERT(DATETIME, '2024-04-05 15:30:00', 120), '����-��� ��������: ������ ������ ���������', 'hamilton-bahrain.jpg', '����� �������� ������� ������ �� ����-��� ��������, �������� �� ������� �����������.'),
('������� 1', CONVERT(DATETIME, '2024-04-06 17:00:00', 120), '���� ���������� �� ������ ����� � ����-��� ���������', 'verstappen-australia.jpg', '���� ���������� ������� ����-��� ���������, ������� ���� ������� � ����������.'),
('������� 1', CONVERT(DATETIME, '2024-04-07 11:00:00', 120), '����� ������ ������� �� ����-��� ������', 'leclerc-monaco.jpg', '����� ������ �������� ������ �� ����-��� ������, ����������������� �������� ���������.'),
('������� 1', CONVERT(DATETIME, '2024-04-08 12:30:00', 120), '������� ������������� �������� �����', 'ferrari-form.jpg', '������� ������������������ �������� ����� �� ������ � ��������� ����� ����� �������.'),

-- ������� � ���������
('��������', CONVERT(DATETIME, '2024-04-05 18:30:00', 120), '������� ������ �������� � ���������� ������', 'russia-eurochamp.jpg', '������� ������ �� ��������� �������� � ������ ���������� ������, ������� ������ � �������� �����.'),
('��������', CONVERT(DATETIME, '2024-04-06 20:00:00', 120), '�������� ���������� ����� ����', 'brazil-worldcup.jpg', '�������� �������� ����� ����, ������� ������ ��� �������� � ������.'),
('��������', CONVERT(DATETIME, '2024-04-07 21:30:00', 120), '������ ���������� ��� � ����������', 'serbia-usa.jpg', '������ �������� ��� � ���������� �������� ������� �� ��������� � ����������� 3:0.'),
('��������', CONVERT(DATETIME, '2024-04-08 22:00:00', 120), '������ ��������� � ������� � ������', 'italy-turkey.jpg', '������ ��������� ����� ���������� ������� � ������, ������� ����� � ����������� 3:1.'),

-- �������������� ������� ��� �������
('������', CONVERT(DATETIME, '2024-04-05 12:30:00', 120), '��������� ������� ���������� ������� � ���������� �������-����', 'manutd-arsenal.jpg', '��������� ������� ������� ������� 2:1 � ����������� ����� ���������� �������-����.')





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
INSERT INTO NewsCommets (NewsDateTime, CommentDateTime, CommentText, UserEmail)
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
