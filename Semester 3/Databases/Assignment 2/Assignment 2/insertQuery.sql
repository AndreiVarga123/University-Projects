USE [WineShop]

INSERT INTO Producer(id, producer_name, country, details)
VALUES 
	(1,'Purcari','Moldova', 'Grupul Purcari este unul dintre principalii producători de vin și brandy din Europa Centrală și de Est, gestionând peste 1.300 de hectare de podgorii și 4 vinării din România și Republica Moldova: Purcari, Crama Ceptura, Bostavan și Bardar.'),
	(2,'Jidvei','Romania', 'Grupul Jidvei este cel mai mare producător de vinuri din România. Cu o suprafață  totală de peste 2.500 de hectare de viță-de-vie, acesta deține cea mai mare plantație viticolă din țară și cea mai mare podgorie cu proprietar unic din Europa. '),
	(3,'Budureasca','Romania','Crama Budureasca a fost inaugurată în primăvara anului 2013 și are o suprafață de 2500mp, fiind una din cele mai noi și mai mari crame din regiunea Dealu Mare.'),
	(4,'Castel Freres', 'Franta', 'Grupul Castel este cel mai mare producător francez de vin și deține cele mai mari mărci de vin franceze și străine distribuite în Franța.'),
	(5,'Trapiche','Argentina','A fost fonda in 1883 si are o suprafata de peste 1000 de hectare, care se intinde peste mai multe podgorii din Mendoza.');

INSERT INTO Vineyard(id, vineyard_name, country, region)
VALUES
	(1,'Purcari','Moldova','Sud-estul Moldovei'),
	(2,'Tarnave','Romania','Transilvania'),
	(3,'Dealu Mare','Romania', 'Dealurile Istritei'),
	(4,'Languedoc-Roussillon','Franta','Bordeaux'),
	(5,'Mendoza','Argentina','Poalele Andesului');

INSERT INTO Variety(id,variety_name,taste,variety_description)
VALUES
	(1,'Pinot Noir','dry','Cabernet Sauvignon este soiul de vin ideal când vine vorba de îmbunătățirea calităților prin îmbătrânire.'),
	(2,'Cabernet-Sauvignon','dry','Cabernet Sauvignon este soiul de vin ideal când vine vorba de îmbunătățirea calităților prin îmbătrânire.'),
	(3,'Chardonnay','dry','Chardonnay sec este un vin elegant, cu o aromă florală distinctă. Vinul este catifelat, suav, tandru, cu aromă ce o amintește pe cea a fânului de curând cosit.'),
	(4,'Sauvignon Blanc','dry','Sauvignon blanc este un vin, cu o culoare galbenă-canar cu reflexe verzui strălucitoare. Are aromă destul de intensă, amintind-o pe cea de iasomie sau de mirosul florilor de viță-de-vie.'),
	(5,'Malbec','dry','Malbec este un soi de struguri cu bobița mare, pelița subțire și preferă mai mult soare ca alte soiuri de struguri roșii'),
	(6,'Port','sweet','Vinul de Porto este un vin portughez,fortificat produs exclusiv în regiunea DOC Douro. Este de obicei un vin dulce, roșu, alb sau roze.');

INSERT INTO Supplier(id,full_name,email,phone)
VALUES
	(1,'Oana Topan','ontpn@gmail.com','0798454330'),
	(2,'Dana Hudema','dnhdm@gmail.com','0738184711'),
	(3,'Iannis Taravinas', 'nnstrvns@gmail.com','0792124599'),
	(4,'Daniel Timoficiuc','dnltmfcc@gmail.com','0710995828'),
	(5,'Adrian Georgiu', 'drngrg@gmail.com','0767387036');

INSERT INTO Wine(id,full_name,producer_id,vineyard_id,variety_id,supplier_id,color,price,alchool_percentage,volume)
VALUES
	(1,'Purcari Pinot Noir',1,1,1,1,'red',26.00,12.5,750),
	(2,'Purcari Sauvignon Blanc',1,1,4,3,'white',26.00,13.00,700),
	(3,'Ana Sauvignon Blanc',2,2,4,2,'white',51.00,12.5,750),
	(4,'Budureasca Cabernet Sauvignon',3,3,2,2,'red',18.50,14.00,750),
	(5,'Trapiche oak cask Malbec',5,5,5,4,'red',33.00,13.50,700);

INSERT INTO Customer(id,full_name,customer_password,email)
VALUES
	(1,'Andrei Varga', '1111', 'ndrvrg@gmail.com'),
	(2,'Andrei Forro', '2222', 'ndrfrr@gmail.com'),
	(3,'Alexandra Vomir','3333', 'lxndrvmr@gmail.com'),
	(4,'Alex Stufaru', '4444', 'lxstfr@gmail.com'),
	(5,'Andrei Varga', '5555', 'ndrvrg2@gmail.com'),
	(6,'Stefan Apoi', '666', 'stfnp@gmail.com');

INSERT INTO CustomerReview(id,customer_id,wine_id,rating,descr)
VALUES
	(1,1,4,9.5,'Great Wine'),
	(2,5,4,9.5,'I loved this wine'),
	(3,2,4,5.0,'Mid'),
	(4,4,4,1.5,'Better wine in Barlad'),
	(5,3,3,7.5,'Pretty good');

INSERT INTO CriticReview(id,critic_name,critic_details,wine_id,rating,descr)
VALUES
	(1,'Rares Font','Best critic in Borsa',4,6.5,'Wine was ok'),
	(2,'Racasan Andrei','Gambler, turned wine critic',4,10,'Best wine I ever had'),
	(3,'Lucian Ghilea','Best critic in Huedini',2,5,'Nothing special, just an average wine'),
	(4,'Rares Font','Best critic in Borsa',3,8,'Good wine'),
	(5,'Racasan Andrei','Gambler, turned wine critic',3,9,'Very good wine');

INSERT INTO Purchase(id,customer_id,expected_delivery_date,shipping_details,total_quantity,total_price)
VALUES
	(1,1,'2022-11-23','Shipped from Warehouse in Amsterdam, Netherlands',2,52.00),
	(2,4,'2022-12-05','Shipped from Warehouse in New York, USA',3,110.00),
	(3,2,'2022-12-13','Shipped from Warehouse in Paris, France', 5,92.50),
	(4,3,'2022-11-15', 'Shipped from Warehouse in Bucharest, Romania', 10,260.00),
	(5,5,'2022-11-20', 'Shipped from Warehouse in Bucharest, Romania', 5, 165.00),
	(6,4,'2022-12-07','Shipped from Warehouse in New York, USA',1,26);

INSERT INTO Purchase_item(purchase_id,wine_id,quantity,price)
VALUES
	(1,1,2,52.0),
	(2,2,1,26.0),
	(2,3,1,51.0),
	(2,5,1,33.0),
	(3,4,5,92.5),
	(5,5,5,165.0),
	(6,1,1,26.0);


UPDATE Vineyard
SET country = 'Russia'
WHERE country IN ('Moldova','Romania') 

UPDATE  Wine
SET price = 25.00
WHERE price BETWEEN 0 AND 20

UPDATE Supplier
SET email = 'drngrg@yahoo.com'
WHERE email LIKE '%gmail%' AND full_name = 'Adrian Georgiu'

DELETE FROM Variety WHERE taste = 'sweet' AND variety_description IS NOT NULL

DELETE FROM Producer WHERE producer_name = 'Castel Freres'

UPDATE Vineyard
SET country = 'Romania'
WHERE vineyard_name IN ('Tarnave','Dealu Mare')
UPDATE Vineyard
SET country = 'Moldova'
WHERE vineyard_name = 'Purcari'

UPDATE Supplier
SET email = 'drngrg@gmail.com'
WHERE email LIKE '%yahoo%' AND full_name = 'Adrian Georgiu'

UPDATE  Wine
SET price = 18.50
WHERE price = 30

INSERT INTO Variety(id,variety_name,taste,variety_description) VALUES(6,'Port','sweet','Vinul de Porto este un vin portughez,fortificat produs exclusiv în regiunea DOC Douro. Este de obicei un vin dulce, roșu, alb sau roze.');

INSERT INTO Producer(id, producer_name, country, details) VALUES(4,'Castel Freres', 'Franta', 'Grupul Castel este cel mai mare producător francez de vin și deține cele mai mari mărci de vin franceze și străine distribuite în Franța.');











