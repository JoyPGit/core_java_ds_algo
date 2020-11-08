-- DB -> https://www.w3schools.com/sql/sql_distinct.asp
/*
SELECT - extracts data from a database
UPDATE - updates data in a database
DELETE - deletes data from a database
INSERT INTO - inserts new data into a database
CREATE DATABASE - creates a new database
ALTER DATABASE - modifies a database
CREATE TABLE - creates a new table
ALTER TABLE - modifies a table
DROP TABLE - deletes a table
CREATE INDEX - creates an index (search key)
DROP INDEX - deletes an index
JOINS
*/

--intersection, union

-- 1 SELECT MULTIPLE COLS
SELECT CustomerID, CustomerName FROM Customers where PostalCode in ("12209", "05021"); 
-- 2 FOR SINGLE SELECTION
SELECT * FROM Customers where PostalCode = "12209"; 
-- 3 FOR MULTIPLE SELECTION USING BETWEEN, LIKE AND IN
SELECT * FROM Products WHERE Price BETWEEN 50 AND 60;
SELECT * FROM Customers WHERE City LIKE 'Mex%'; --(case sensistive)
SELECT * FROM Customers where PostalCode in ("12209", "05021"); 
-- 4 DISTINCT
SELECT Distinct Country FROM Customers;

-- https://www.w3schools.com/sql/sql_and_or.asp
-- 5 AND, OR, NOT

-- AND
SELECT * FROM Customers
WHERE CustomerID = "1" AND Country='Germany' AND City='Berlin';

-- selects all fields from "Customers" where country is "Germany" AND city 
-- must be "Berlin" OR "München" (use parenthesis to form complex expressions):

-- OR
SELECT * FROM Customers WHERE Country = "Germany" AND (City='Berlin' OR City='München');
-- IN CAN WORK
SELECT * FROM Customers WHERE Country = "Germany" AND City In ("Berlin");

-- NOT
SELECT * FROM Customers WHERE Country NOT IN ("Germany");
SELECT * FROM Customers WHERE NOT Country='Germany';

--NOT AND AND
SELECT * FROM Customers WHERE Country NOT IN ("Germany", "USA");

-- 6 ORDER BY
SELECT CustomerName FROM Customers
WHERE Country NOT IN('Germany') ORDER BY COUNTRY ASC;


-- 7 INSERT INTO VALUES
INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway');

-- NULL
SELECT * FROM Customers WHERE Customer IS NOT NULL;

-- 8 UPDATE (WHERE IS VERY IMP, W/O IT ALL RECORDS WILL BE UPDATED)
UPDATE Customers
SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' 
WHERE CustomerID = 1;

-- 9 DELETE
DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';

-- 10 LIMIT
SELECT * FROM Customers LIMIT 3;

-- 11  MIN-MAX  --  MAX(COL_NAME)
SELECT MIN(Price) AS SmallestPrice FROM Products;

-- 12 COUNT, AVG , SUM
SELECT AVG(Price) FROM Products;

-- 13 WILDCARD *, ?, [], https://www.w3schools.com/sql/sql_wildcards.asp
-- selects all customers with a City starting with "b", "s", or "p":
SELECT * FROM Customers WHERE City LIKE '[bsp]%';

-- 14 ALIAS
SELECT CustomerID AS ID, CustomerName AS Customer FROM Customers;

-- ALIAS TABLE NAME
SELECT o.OrderID, o.OrderDate, c.CustomerName
FROM Customers AS c, Orders AS o
WHERE c.CustomerName='Around the Horn' AND c.CustomerID=o.CustomerID;

-- JOIN MEANS CROSS PRODUCT

-- 15 INNER JOINS
SELECT o.OrderID, Customers.CustomerName
FROM Orders AS o
INNER JOIN Customers ON o.CustomerID = Customers.CustomerID;

-- 16 SELF JOIN (CROSS PRODUCT WITH ITSELF, SO ALIAS)

-- https://www.youtube.com/watch?v=6DQpvfdj6EE
-- FIND STUDENT ENROLLED IN ATLEAST TWO COURSES
-- CAN BE DONE USING NESTED QUERY OR SELF JOIN

-- SELF JOIN (CROSS-PRODUCT AND THEN S_id same but not C_id)
SELECT * FROM STUDY AS a, STUDY AS b WHERE
a.S_id = b.S_id And a.C_id <> b.C_id;

-- 17 (or) using GROUP BY AND HAVING
SELECT * FROM STUDY GROUP BY S_id
HAVING COUNT(C_id)>1;



-- MISC https://www.youtube.com/watch?v=_yog7h4BokQ

-- NESTED QUERY
-- 1 SEL EMP_NAME EARNING MAX SALARY
SELECT Emp_Name FROM EMP WHERE SALARY = (SELECT MAX(Salary) FROM EMP);


-- https://www.w3resource.com/sql-exercises/employee-database-exercise/
-- sql-subqueries-exercise-employee-database-45.php





