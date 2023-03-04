USE [WineShop];

CREATE TABLE Producer(
	id int NOT NULL,
	producer_name varchar(30) NOT NULL,
	country varchar(30) NOT NULL,
	details varchar(255),
	PRIMARY KEY(id)
);

CREATE TABLE Vineyard(
	id int NOT NULL,
	vineyard_name varchar(30) NOT NULL,
	country varchar(30) NOT NULL,
	region varchar(30) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Variety(
	id int NOT NULL,
	variety_name varchar(30) NOT NULL,
	taste varchar(50),
	variety_description varchar(255),
	PRIMARY KEY(id)
);

CREATE TABLE Supplier(
	id int NOT NULL,
	full_name varchar(30) NOT NULL,
	email varchar(100) NOT NULL,
	phone varchar(30) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE Wine(
	id int NOT NULL,
	full_name varchar (50) NOT NULL,
	producer_id int NOT NULL,
	variety_id int NOT NULL,
	vineyard_id int NOT NULL,
	supplier_id int NOT NULL,
	color varchar(30) NOT NULL,
	price float NOT NULL,
	alchool_percentage float NOT NULL,
	volume int NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(producer_id)
		REFERENCES Producer(id)
		ON DELETE CASCADE,
	FOREIGN KEY(variety_id)
		REFERENCES Variety(id)
		ON DELETE CASCADE,
	FOREIGN KEY(vineyard_id)
		REFERENCES Vineyard(id)
		ON DELETE CASCADE,
	FOREIGN KEY(supplier_id)
		REFERENCES Supplier(id)
		ON DELETE CASCADE
);

CREATE TABLE Customer(
	id int NOT NULL,
	full_name varchar(30) NOT NULL,
	customer_password varchar(30) NOT NULL,
	email varchar(100) NOT NULL,
	customer_address varchar(255),
	phone varchar(30),
	PRIMARY KEY(id)
);

CREATE TABLE Purchase(
	id int NOT NULL,
	customer_id int NOT NULL,
	expected_delivery_date date NOT NULL,
	shipping_details varchar(255) NOT NULL,
	total_quantity int NOT NULL,
	total_price float NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(customer_id)
		REFERENCES Customer(id)
		ON DELETE CASCADE
);

CREATE TABLE Purchase_item(
	purchase_id int NOT NULL,
	wine_id int NOT NULL,
	quantity int NOT NULL,
	price float NOT NULL,
	FOREIGN KEY(purchase_id)
		REFERENCES Purchase(id)
		ON DELETE CASCADE,
	FOREIGN KEY(wine_id)
		REFERENCES Wine(id)
		ON DELETE CASCADE
);

CREATE TABLE CustomerReview(
	id int NOT NULL,
	customer_id int NOT NULL,
	wine_id int NOT NULL,
	rating float NOT NULL,
	descr varchar(255) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(customer_id)
		REFERENCES Customer(id)
		ON DELETE CASCADE,
	FOREIGN KEY(wine_id)
		REFERENCES Wine(id)
		ON DELETE CASCADE
);

CREATE TABLE CriticReview(
	id int NOT NULL,
	critic_name varchar(30) NOT NULL,
	critic_details varchar(255) NOT NULL,
	wine_id int NOT NULL,
	rating float NOT NULL,
	descr varchar(255) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(wine_id)
		REFERENCES Wine(id)
		ON DELETE CASCADE
);