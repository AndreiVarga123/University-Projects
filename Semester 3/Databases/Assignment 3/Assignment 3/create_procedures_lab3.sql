USE [WineShop]
GO

ALTER PROCEDURE alter_column_up
AS
	ALTER TABLE CriticReview
	ALTER COLUMN rating INT NOT NULL
GO

ALTER PROCEDURE alter_column_down
AS 
	ALTER TABLE CriticReview
	ALTER COLUMN rating FLOAT NOT NULL
GO

ALTER PROCEDURE add_column
AS 
	ALTER TABLE Supplier
	ADD adrress VARCHAR(255)
GO

ALTER PROCEDURE remove_column
AS
	ALTER TABLE Supplier
	DROP COLUMN adrress 
GO

ALTER PROCEDURE add_default_constraint
AS
	ALTER TABLE Vineyard
	ADD CONSTRAINT ro DEFAULT 'Romania' FOR country
GO

ALTER PROCEDURE remove_default_constraint
AS 
	ALTER TABLE Vineyard
	DROP CONSTRAINT ro
GO

ALTER PROCEDURE add_primary_key
AS 
	ALTER TABLE Purchase_item
	ADD CONSTRAINT pk PRIMARY KEY(purchase_id,wine_id)
GO

ALTER PROCEDURE remove_primary_key
AS
	ALTER TABLE Purchase_item
	DROP CONSTRAINT pk
GO

ALTER PROCEDURE add_candidate_key
AS
	ALTER TABLE Wine
	ADD CONSTRAINT candidate UNIQUE(full_name)
GO

ALTER PROCEDURE remove_candidate_key
AS
	ALTER TABLE Wine
	DROP CONSTRAINT candidate
GO

ALTER PROCEDURE add_foreign_key
AS
	ALTER TABLE Purchase
	ADD supplier_id INT
GO

ALTER PROCEDURE add_foreign_key_constraint
AS
	ALTER TABLE Purchase
	ADD CONSTRAINT fk FOREIGN KEY(supplier_id) REFERENCES Supplier(id)
GO

ALTER PROCEDURE remove_foreign_key_constraint
AS
	ALTER TABLE Purchase
	DROP fk
GO

ALTER PROCEDURE remove_foreign_key
AS
	ALTER TABLE Purchase
	DROP COLUMN supplier_id
GO

ALTER PROCEDURE add_table
AS
	CREATE TABLE Complaints(
	id int NOT NULL,
	descr varchar(256) NOT NULL,
	PRIMARY KEY(id))
GO

ALTER PROCEDURE remove_table
AS
	DROP TABLE Complaints
GO

EXECUTE remove_column