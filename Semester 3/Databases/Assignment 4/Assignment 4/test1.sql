use WineShop
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE test1 AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	declare @start_time_all datetime
	set @start_time_all = getdate()
	
	declare @insert_start datetime
	declare @insert_finish datetime
	declare @table_name varchar(30)
	set @table_name = 'Vineyard'

	execute delete_rows 500,@table_name

	set @insert_start = getdate()
	execute insert_rows 500,@table_name
	set @insert_finish = getdate()

	declare @view_start datetime
	declare @view_end datetime
	set @view_start = getdate()
	execute select_view View_1
	set @view_end = getdate()

	insert into TestRuns(Description,StartAt,EndAt) values ('TestRun of test1', @start_time_all, @view_end)
	declare @testrunid int
	set @testrunid = (select max(TestRunID) from TestRuns)
	declare @testtableid int
	set @testtableid = (select TableID from Tables where Name='Vineyard')
	declare @testviewid int
	set @testviewid = (select ViewID from Views where Name='View_1')
	insert into TestRunTables(TestRunID,TableID,StartAt,EndAt) values (@testrunid, @testtableid, @insert_start, @insert_finish)
	insert into TestRunViews(TestRunID,ViewID, StartAt,EndAt) values (@testrunid, @testviewid, @view_start, @view_end)

END
GO