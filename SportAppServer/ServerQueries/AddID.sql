BACKUP DATABASE SportAppDB
TO DISK = 'C:\My data\db backup\SportAppDB_Before_Id.bak'
WITH FORMAT,
MEDIANAME = 'SQLServerBackups',
NAME = 'Full Backup of SportAppServer Before Adding Id';
GO
	

begin transaction
BEGIN TRY 
	ALTER TABLE [News] DROP CONSTRAINT [PK_News];

	ALTER TABLE [News]
	ADD [Id] INT IDENTITY(1,1) NOT NULL;
	
	ALTER TABLE [News]
	ADD CONSTRAINT PK_News_id PRIMARY KEY CLUSTERED ([id]);

	COMMIT TRANSACTION; 
	PRINT 'таблица обнновлена, колонка ID добавлена';
END TRY 
BEGIN CATCH 
	ROLLBACK TRANSACTION;
	PRINT 'Произошла ошибка. Изменения отменены.';
    SELECT ERROR_MESSAGE() AS ErrorMessage;
END CATCH;

