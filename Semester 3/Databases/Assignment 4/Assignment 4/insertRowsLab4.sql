USE WineShop
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE insert_rows
	@nr_of_rows varchar(30),
	@table_name varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    if ISNUMERIC(@nr_of_rows) != 1
	BEGIN
		print('Not a number -insert')
		return 1 
	END

	declare @full_name varchar(30)
	declare @password varchar(30)
	declare @email varchar(100)
	declare @customer_address varchar(255)
	declare @phone varchar(30)

	declare @customer_id int
	declare @expected_delivery_date date
	declare @shipping_details varchar(255)
	declare @total_quantity int
	declare @total_price int

	declare @vineyard_name varchar(30)
	declare @country varchar(30)
	declare @region varchar(30)

	SET @nr_of_rows = cast(@nr_of_rows as INT)
	
	declare @counter int
	set @counter = 1

	while @counter <= @nr_of_rows
	begin
		if @table_name = 'Customer'
		begin
			set @full_name = 'customer' + convert(varchar(10),(@counter))
			set @password = 'password' + convert(varchar(10),(@counter))
			set @email = 'customer' + convert(varchar(10),(@counter)) + '@gmail.com'
			set @customer_address = 'address' + convert(varchar(10),(@counter))
			set @phone = 'phone' + convert(varchar(10),(@counter))
			insert into Customer(id,full_name,customer_password,email,customer_address,phone) values(@counter,@full_name,@password,@email,@customer_address,@phone)
		end

		if @table_name = 'Purchase'
		begin
			set @customer_id = (select max(id) from Customer)
			set @customer_id = rand()*(@customer_id-1)+1
			set @expected_delivery_date = '2022-12-24'
			set @shipping_details = 'details' + convert(varchar(10),(@counter))
			set @total_quantity = rand()*(10-1)+1
			set @total_price = rand()*(500-15)+15
			insert into Purchase(id,customer_id,shipping_details,expected_delivery_date,total_quantity,total_price) values(@counter,@customer_id,@shipping_details,@expected_delivery_date,@total_quantity,@total_price)		
		end

		if @table_name = 'Vineyard'
		begin
			set @vineyard_name = 'vineyard' + convert(varchar(10),(@counter))
			set @country = 'country' + convert(varchar(10),(@counter))
			set @region = 'region' + convert(varchar(10),(@counter))
			insert into Vineyard(vineyard_name,country,region) values(@vineyard_name,@country,@region)
		end
		set @counter = @counter + 1
	end
END