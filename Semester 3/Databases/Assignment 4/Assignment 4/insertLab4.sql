use WineShop;

insert into Tables(Name) values
('Vineyard'),
('Customer'),
('Purchase')

insert into Tests( Name) values
('test1'),
('test2')

insert into Views(Name) values
('View_1'),
('View_2'),
('View_3')

alter view [View_1] as
	select * from Vineyard

create view [View_2] as
	select c.full_name, p.total_quantity, p.total_price
	from Purchase p inner join Customer c on p.id=c.id

create view [View_3] as
	select p.customer_id, count(*) nr_of_purchases 
	from Purchase p inner join Customer c on p.id=c.id
	group by p.customer_id

insert into TestViews(TestID, ViewId) values
(4,6),
(5,7),
(5,8)

insert into TestTables(TestID, TableID, NoOfRows,Position) values
(4,5,500,1),
(5,6,500,1),
(5,7,500,2)