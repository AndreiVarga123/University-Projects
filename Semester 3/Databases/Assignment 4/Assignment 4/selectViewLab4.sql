SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE select_view
	@view_name varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	if @view_name = 'View_1'
	begin
		select * from View_1
	end

	if @view_name = 'View_2'
	begin
		select * from View_2
	end

	if @view_name = 'View_2'
	begin
		select * from View_2
	end
END
GO

execute select_view View_1