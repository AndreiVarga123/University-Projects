use WineShop
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

AlTER PROCEDURE test2 AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here



	declare @start_time_all datetime
	set @start_time_all = getdate()

	declare @customer_insert_start datetime
	declare @customer_insert_finish datetime
	declare @table_name varchar(30)
	set @table_name = 'Customer'

	execute delete_rows 500,@table_name

	set @customer_insert_start = getdate()
	execute insert_rows 500,@table_name
	set @customer_insert_finish = getdate()

	declare @pruchase_insert_start datetime
	declare @purchase_insert_finish datetime
	set @table_name = 'Purchase'

	execute delete_rows 500,@table_name

	set @pruchase_insert_start = getdate()
	execute insert_rows 500,@table_name
	set @purchase_insert_finish = getdate()

	declare @view2_start datetime
	declare @view2_end datetime
	set @view2_start = getdate()
	execute select_view View_2
	set @view2_end = getdate()

	declare @view3_start datetime
	declare @view3_end datetime
	set @view3_start = getdate()
	execute select_view View_3
	set @view3_end = getdate()

	insert into TestRuns(Description,StartAt,EndAt) values ('TestRun of test1', @start_time_all, @view3_end)

	declare @testrunid int
	set @testrunid = (select max(TestRunID) from TestRuns)

	declare @testtableid int
	set @testtableid = (select TableID from Tables where Name='Customer')
	insert into TestRunTables(TestRunID,TableID,StartAt,EndAt) values (@testrunid, @testtableid, @customer_insert_start, @customer_insert_finish)
	set @testtableid = (select TableID from Tables where Name='Purchase')
	insert into TestRunTables(TestRunID,TableID,StartAt,EndAt) values (@testrunid, @testtableid, @pruchase_insert_start, @purchase_insert_finish)

	declare @testviewid int
	set @testviewid = (select ViewId from Views where Name='View_2')
	insert into TestRunViews(TestRunID,ViewID, StartAt,EndAt) values (@testrunid, @testviewid, @view2_start, @view2_end)
	set @testviewid = (select ViewId from Views where Name='View_3')
	insert into TestRunViews(TestRunID,ViewID, StartAt,EndAt) values (@testrunid, @testviewid, @view3_start, @view3_end)

	
END
GO

execute test2