/*
arithmetic expresion in select 3/3
and or not 3/3
() in where 3/3
distinct 3/3
order by 2/2
top 2/2
*/

USE [WineShop]

/*a*/
SELECT DISTINCT CustomerReview.rating
FROM CustomerReview
WHERE CustomerReview.wine_id = 4
UNION ALL
SELECT DISTINCT CriticReview.rating
FROM CriticReview
WHERE CriticReview.wine_id = 4
ORDER BY rating DESC

SELECT DISTINCT w.volume
FROM Wine w
WHERE w.color='red' OR w.color='white'


/*b*/
SELECT Wine.full_name
FROM Wine
WHERE color = 'red'
INTERSECT
SELECT Wine.full_name
FROM Wine
WHERE producer_id = 1

SELECT * FROM Customer c
WHERE c.id IN (SELECT p.customer_id FROM Purchase p)


/*c*/
SELECT * FROM Variety v1
WHERE v1.id IN(
SELECT v.id FROM Variety v
EXCEPT
SELECT w.variety_id FROM Wine w)

SELECT * FROM Supplier s
WHERE s.id NOT IN(SELECT w.supplier_id FROM Wine w)


/*d*/
SELECT w.full_name,p.producer_name,va.variety_name,vi.vineyard_name,s.full_name,w.color,w.alchool_percentage,w.volume
FROM Wine w
INNER JOIN Producer p ON w.producer_id = p.id
INNER JOIN Variety va ON w.variety_id = va.id
INNER JOIN Vineyard vi ON w.vineyard_id = vi.id
INNER JOIN Supplier s ON w.supplier_id = s.id

SELECT p.customer_id,w.full_name,pit.purchase_id
FROM Wine w
FULL OUTER JOIN Purchase_item pit ON pit.wine_id=w.id
FULL OUTER JOIN Purchase p ON pit.purchase_id=p.id
ORDER BY p.customer_id,pit.purchase_id

SELECT DISTINCT w.full_name,t.total_per_wine
FROM Wine w
LEFT JOIN (SELECT pit1.wine_id, (SELECT SUM(pit.quantity) FROM Purchase_item pit WHERE pit.wine_id = pit1.wine_id) AS total_per_wine FROM Purchase_item pit1) AS t
ON t.wine_id = w.id
ORDER BY t.total_per_wine DESC

SELECT w.*, p.producer_name
FROM Wine w
RIGHT JOIN Producer p ON w.producer_id = p.id


/*e*/
SELECT * FROM Customer c1
WHERE c1.id IN(
SELECT c.id FROM Customer c
EXCEPT
SELECT p.customer_id FROM Purchase p
WHERE NOT(p.total_quantity>=5 AND p.total_price>=100.00))

SELECT * FROM Wine w
WHERE w.id IN
	(SELECT t.wine_id
	FROM (SELECT p1.wine_id, (SELECT SUM(p.quantity) FROM Purchase_item p WHERE p.wine_id = p1.wine_id) AS total_per_wine FROM Purchase_item p1) AS t
	WHERE t.total_per_wine > 
		(SELECT AVG(t1.total_per_wine) 
		FROM (SELECT p1.wine_id, (SELECT SUM(p.quantity) FROM Purchase_item p WHERE p.wine_id = p1.wine_id) AS total_per_wine FROM Purchase_item p1) AS t1))


/*f*/
SELECT s.full_name
FROM Supplier s
WHERE EXISTS
(SELECT w.supplier_id FROM Wine w WHERE w.price>30 AND w.supplier_id=s.id)

SELECT c.full_name
FROM Customer c
WHERE EXISTS
(SELECT p.id FROM Purchase p WHERE p.customer_id=c.id);


/*g*/
SELECT * FROM Wine w
WHERE w.id IN
(SELECT p1.wine_id
FROM Purchase_item p1
WHERE (SELECT SUM(p.quantity) FROM Purchase_item p WHERE p.wine_id = p1.wine_id)>=5)

SELECT * FROM Wine w
WHERE w.id IN
(SELECT p1.wine_id
FROM Purchase_item p1
WHERE (SELECT SUM(p.price) FROM Purchase_item p WHERE p.wine_id = p1.wine_id)>=100)


/*h*/
SELECT TOP 3 p.country, COUNT(p.id) AS number_of_producers
FROM Producer p
GROUP BY p.country
ORDER BY COUNT(p.id) DESC

SELECT w.color, COUNT(w.id) AS number_of_wines, AVG(w.price) AS avg_price_for_color
FROM Wine w 
GROUP BY w.color
HAVING COUNT(w.id)>1

SELECT w.volume, COUNT(w.id) AS number_of_wines, AVG(w.price) AS avg_price_for_color
FROM Wine w 
GROUP BY w.volume
HAVING COUNT(w.id)>1

SELECT TOP 3 p.customer_id, SUM(p.total_price) AS all_time_spent
FROM Purchase p
GROUP BY p.customer_id
ORDER BY SUM(p.total_price) DESC


/*i*/
SELECT p.producer_name
FROM Producer p
WHERE p.id = ANY
	(SELECT w.producer_id
	FROM Wine w
	WHERE w.price>30)

SELECT p.producer_name
FROM Producer p
WHERE p.id = ANY
	(SELECT w.producer_id
	FROM Wine w
	WHERE w.price>(SELECT AVG(w.price) FROM Wine w))

SELECT s.full_name,s.phone
FROM Supplier s
WHERE s.id = ANY
	(SELECT w.supplier_id
	FROM Wine w
	WHERE w.color = 'red')

SELECT s.full_name,s.phone
FROM Supplier s
WHERE s.id IN
	(SELECT w.supplier_id
	FROM Wine w
	WHERE w.color = 'red')

SELECT w.full_name
FROM Wine w
WHERE w.id <> ALL
	(SELECT c.wine_id
	FROM CriticReview c
	WHERE c.rating<7.5) AND w.id IN(SELECT c1.wine_id FROM CriticReview c1)

SELECT w.full_name
FROM Wine w
WHERE w.id <> ALL
	(SELECT c.wine_id
	FROM CriticReview c
	WHERE c.rating<(SELECT AVG(c.rating) FROM CriticReview c) AND w.id IN(SELECT c1.wine_id FROM CriticReview c1))


SELECT c.full_name
FROM Customer c
WHERE c.id <> ALL
	(SELECT cr.customer_id
	FROM CustomerReview cr
	WHERE cr.rating>4.5 ) AND c.id IN(SELECT c1.customer_id FROM CustomerReview c1)

SELECT c.full_name
FROM Customer c
WHERE c.id NOT IN
	(SELECT cr.customer_id
	FROM CustomerReview cr
	WHERE cr.rating>4.5 ) AND c.id IN(SELECT c1.customer_id FROM CustomerReview c1)
	
