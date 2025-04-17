USE KorSport;
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[CountComments]
    @newsDateTime DATETIME,
    @CommentCount INT OUTPUT
AS
BEGIN
    SELECT @CommentCount = COUNT(*)
    FROM NewsComments 
    WHERE NewsDateTime = @newsDateTime;
END
GO
