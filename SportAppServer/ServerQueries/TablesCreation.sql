use SportAppDB

CREATE TABLE News (
    Sport NVARCHAR(255) NOT NULL,         -- ��� ������
    DateTime DATETIME NOT NULL PRIMARY KEY, -- ���� � ����� �������
    Text NVARCHAR(MAX) NOT NULL           -- ����� �������
);
