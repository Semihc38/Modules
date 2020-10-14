-- ORDERING RESULTS

-- Populations of all countries in descending order
SELECT population FROM country ORDER BY population DESC;
--Names of countries and continents in ascending order
SELECT name, continent FROM country ORDER BY continent ASC; 
-- LIMITING RESULTS
-- The name and average life expectancy of the countries with the 10 highest life expectancies.
SELECT name, lifeexpectancy FROM country WHERE lifeexpectancy IS NOT NULL ORDER BY lifeexpectancy DESC LIMIT 10;
-- CONCATENATING OUTPUTS

-- The name & state of all cities in California, Oregon, or Washington.
                                                                --   district IN ('California','Oregon','Washington')
SELECT name||', '||district AS city_state FROM city WHERE district ='California' OR district='Oregon' OR district='Washington'
ORDER BY population;
-- "city, state", sorted by state then city
SELECT city ||', 'district FROM ;
-- AGGREGATE FUNCTIONS
-- 
-- Average Life Expectancy in the World

-- Total population in Ohio

-- The surface area of the smallest country in the world

-- The 10 largest countries in the world
SELECT name AS largest_countries FROM country ORDER BY surfacearea DESC LIMIT 10;

-- The number of countries who declared independence in 1991

-- GROUP BY
-- Count the number of countries where each language is spoken, ordered from most countries to least
SELECT language, COUNT(countrycode) FROM countrylanguage GROUP BY language ORDER BY COUNT(countrycode)DESC;
-- Average life expectancy of each continent ordered from highest to lowest
SELECT continent, AVG(lifeexpectancy) FROM country
WHERE lifeexpectancy IS NOT NULL
GROUP BY continent ORDER BY AVG(lifeexpectancy) DESC;

-- Exclude Antarctica from consideration for average life expectancy

-- Sum of the population of cities in each state in the USA ordered by state name
SELECT SUM(population) FROM city WHERE countrycode='USA' GROUP BY district ORDER BY district DESC;
-- The average population of cities in each state in the USA ordered by state name

-- SUBQUERIES
SELECT name, countrycode FROM city WHERE countrycode IN (SELECT code FROM country WHERE continent ='Europe');
-- Find the names of cities under a given government leader
SElect name from city where countrycode =(select code from country where headofstate='Yoweri Museveni');
SElect name from city where countrycode IN(select code from country where headofstate='Beatrix');
-- Find the names of cities whose country they belong to has not declared independence yet
SELECT name,(select indepyear from country ctry where code= countrycode) FROM city WHERE countrycode IN (SElECT code FROM country where indepyear IS NOT NULL);
-- Additional samples
-- You may alias column and table names to be more descriptive

-- Alias can also be used to create shorthand references, such as "c" for city and "co" for country.

-- Ordering allows columns to be displayed in ascending order, or descending order (Look at Arlington)

-- Limiting results allows rows to be returned in 'limited' clusters,where LIMIT says how many, and OFFSET (optional) specifies the number of rows to skip

-- Most database platforms provide string functions that can be useful for working with string data. In addition to string functions, string concatenation is also usually supported, which allows us to build complete sentences if necessary.

-- Aggregate functions provide the ability to COUNT, SUM, and AVG, as well as determine MIN and MAX. Only returns a single row of value(s) unless used with GROUP BY.

-- Counts the number of rows in the city table

-- Also counts the number of rows in the city table

-- Gets the SUM of the population field in the city table, as well as
-- the AVG population, and a COUNT of the total number of rows.

-- Gets the MIN population and the MAX population from the city table.

-- Using a GROUP BY with aggregate functions allows us to summarize information for a specific column. For instance, we are able to determine the MIN and MAX population for each countrycode in the city table.
