
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE delete_rows
	@nr_of_rows varchar(30),
	@table_name varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    if ISNUMERIC(@nr_of_rows) != 1
	BEGIN
		print('Not a number-delete'+@nr_of_rows)
		return 1 
	END
	
	SET @nr_of_rows = cast(@nr_of_rows as INT)

	declare @last_row int

	if @table_name = 'Customer'
	begin
		set @last_row = (select max(id) from Customer) - @nr_of_rows
		delete from Customer where id > @last_row
	end

	if @table_name = 'Purchase'
	begin
		set @last_row = (select max(id) from Purchase) - @nr_of_rows
		delete from Purchase where id > @last_row
	end

	if @table_name = 'Vineyard'
	begin
		declare @counter int
		set @counter = 1

		while @counter <= @nr_of_rows
		begin
			delete from Vineyard where vineyard_name = (select max(vineyard_name) from Vineyard)
			set @counter = @counter + 1
		end
	end

END
GO
