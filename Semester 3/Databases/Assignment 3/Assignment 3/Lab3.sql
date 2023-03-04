USE [WineShop]
GO
-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE make_changes
	-- Add the parameters for the stored procedure here
	@newVersion varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DECLARE @currentVersion INT
	SET @currentVersion = (SELECT id_version FROM Version)

	if ISNUMERIC(@newVersion) != 1
	BEGIN
		print('Not a number')
		return 1 
	END
	
	SET @newVersion = cast(@newVersion as INT)
	if @newVersion < 0 or @newVersion > 7
	BEGIN
		print('Invalid number')
		return 2 
	END

	while @currentVersion < @newVersion
	begin
		SET @currentVersion = @currentVersion + 1
		if(@currentVersion = 1)
			execute dbo.alter_column_up
		else if(@currentVersion = 2)
			execute dbo.add_column
		else if(@currentVersion = 3)
			execute dbo.add_default_constraint
		else if(@currentVersion = 4)
			execute dbo.add_primary_key
		else if(@currentVersion = 5)
			execute dbo.add_candidate_key
		else if(@currentVersion = 6)
		begin
			execute dbo.add_foreign_key
			execute dbo.add_foreign_key_constraint
		end
		else if(@currentVersion = 7)
			execute dbo.add_table
	end

	while @currentVersion > @newVersion
	begin
		SET @currentVersion = @currentVersion - 1
		if(@currentVersion = 0)
			execute dbo.alter_column_down
		else if(@currentVersion = 1)
			execute dbo.remove_column
		else if(@currentVersion = 2)
			execute dbo.remove_default_constraint
		else if(@currentVersion = 3)
			execute dbo.remove_primary_key
		else if(@currentVersion = 4)
			execute dbo.remove_candidate_key
		else if(@currentVersion = 5)
		begin
			execute dbo.remove_foreign_key_constraint
			execute dbo.remove_foreign_key
		end
		else if(@currentVersion = 6)
			execute dbo.remove_table
	end

	truncate table Version
	insert into Version values(@newVersion)

END
GO

execute make_changes 0