-- INSERT

-- 1. Add Klingon as a spoken language in the USA
INSERT INTO  countrylanguage (countrycode,isofficial,percentage,language) VALUES ('USA',false, .05, 'Klingon');

SELECT * FROM countrylanguage WHERE language='Klingon';

-- 2. Add Klingon as a spoken language in Great Britain
INSERT INTO countrylanguage VALUES ('GBR','Klingon',false, .08);

-- UPDATE

-- 1. Update the capital of the USA to Houston
UPDATE country set capital = 3796 where
code='USA';

-- 2. Update the capital of the USA to Washington DC and the head of state
select id from city where name='Washington';
UPDATE country set capital = (select id from city where name='Washington') where
code='USA';


-- DELETE

-- 1. Delete English as a spoken language in the USA
DELETE  FROM countrylanguage where
countrycode ='USA' and language ='English';

-- 2. Delete all occurrences of the Klingon language 
DELETE  FROM countrylanguage where
language ='Klingon';

-- REFERENTIAL INTEGRITY

-- 1. Try just adding Elvish to the country language table.
insert into countrylanguage values ('USA','Elvish',false, .02);
select * from countrylanguage where language='Elvish';
-- 2. Try inserting English as a spoken language in the country ZZZ. What happens?
insert into countrylanguage values ('ZZZ','English',false, .02);
-- 3. Try deleting the country USA. What happens?
delete from country where code='USA';

-- CONSTRAINTS

-- 1. Try inserting English as a spoken language in the USA
insert into countrylanguage values ('USA','English',false, 70);

-- 2. Try again. What happens?

-- 3. Let's relocate the USA to the continent - 'Outer Space'
update country set continent ='Outer Space' where code='USA';

-- How to view all of the constraints

SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
SELECT * FROM INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE
SELECT * FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS


-- TRANSACTIONS

-- 1. Try deleting all of the rows from the country language table and roll it back.
START TRANSACTION;
SELECT * FROM countrylanguage;
delete from countrylanguage;
ROLLBACK;


-- 2. Try updating all of the cities to be in the USA and roll it back
START TRANSACTION;
SELECT * from zzz;
UPDATE city set countrycode='GBR';
rollback;
SELECT * FROM city;

select * from city;
update city set countrycode='USA';
-- 3. Try updating a language (Elvish should work) and commit

-- 4. Demonstrate two different SQL connections trying to access the same table where one happens to be inside of a transaction but hasn't committed yet.
start transaction;
update city set countrycode='USA';
commit;