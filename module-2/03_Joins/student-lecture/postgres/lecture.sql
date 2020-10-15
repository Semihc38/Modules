-- ********* INNER JOIN ***********

-- Let's find out who made payment 16666:
SELECT * FROM payment where payment_id=16666;
-- Ok, that gives us a customer_id, but not the name. We can use the customer_id to get the name FROM the customer table
SELECT * FROM payment 
join customer  on customer.customer_id = payment.customer_id
 where payment_id=16666;
-- We can see that the * pulls back everything from both tables. We just want everything from payment and then the first and last name of the customer:
SELECT payment.*, customer.first_name, customer.last_name FROM payment
join customer on customer.customer_id = payment.customer_id
where payment_id=16666;
 --using alias
SELECT p.*, c.first_name, c.last_name FROM payment as p
join customer c on c.customer_id =p.customer_id
where payment_id=16666;
-- But when did they return the rental? Where would that data come from? From the rental table, so let's join that.
SELECT p.*, c.first_name, c.last_name,r.return_date FROM payment AS p
JOIN customer c ON c.customer_id =p.customer_id
JOIN rental r ON r.rental_id = p.rental_id
WHERE payment_id=16666;

select * from rental where rental_id=251;

--alternate path (causes duplicates due to bad data .. multiple dates for 1 payment)
SELECT p.*, c.first_name, c.last_name,r.return_date FROM payment as p
join customer  c on c.customer_id =p.customer_id
join rental r on r.customer_id= p.customer_id
where payment_id=16666;

-- What did they rent? Film id can be gotten through inventory.
SELECT p.*, c.first_name, c.last_name,f.title,r.return_date FROM payment as p
join customer c on c.customer_id =p.customer_id
join rental r on r.rental_id= p.rental_id
join inventory i on i.inventory_id = r.inventory_id
join film f on f.film_id = i.film_id 
where payment_id=16666;
-- What if we wanted to know who acted in that film?
SELECT p.*, c.first_name, c.last_name,f.title,r.return_date,a.first_name, a.last_name FROM payment as p
join customer c on c.customer_id =p.customer_id
join rental r on r.rental_id= p.rental_id
join inventory i on i.inventory_id = r.inventory_id
join film f on f.film_id = i.film_id 
join film_actor  fa on i.film_id=fa.film_id
join actor a on fa.actor_id=a.actor_id
where payment_id=16666;
-- What if we wanted a list of all the films and their categories ordered by film title
select f.film_id, f.title, c.category_id, c.name from film f
join film_category fc ON f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
order by title;
-- Show all the 'Comedy' films ordered by film title
select f.film_id, f.title, c.category_id, c.name from film f
join film_category fc ON f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
where  c.name ='Comedy'
order by title;
-- Finally, let's count the number of films under each category
select  f.title, count(f.title), c.name from film f
join film_category fc ON f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
group by f.title, c.name
order by c.name;

select   count(f.title), c.name from film f
join film_category fc ON f.film_id = fc.film_id
join category c on fc.category_id = c.category_id
group by  c.name
order by c.name;
-- ********* LEFT JOIN ***********

-- (There aren't any great examples of left joins in the "dvdstore" database, so the following queries are for the "world" database)

-- A Left join, selects all records from the "left" table and matches them with records from the "right" table if a matching record exists.

-- Let's display a list of all countries and their capitals, if they have some.
select country.name, city.name FROM country
join city on city.id=country.capital;
--left join
select country.name, city.name FROM country
left join city on city.id=country.capital;
--extra example
select countrylanguage.language, country.name from countrylanguage
join country on country.code= countrylanguage.countrycode;
-- Only 232 rows
-- But we're missing entries:

-- There are 239 countries. So how do we show them all even if they don't have a capital?
-- That's because if the rows don't exist in both tables, we won't show any information for it. If we want to show data FROM the left side table everytime, we can use a different join:

-- *********** UNION *************

-- Back to the "dvdstore" database...

-- Gathers a list of all first names used by actors and customers
-- By default removes duplicates
select first_name from actor
union 
select first_name from customer;

select first_name from actor
union 
select email from customer;
-- Gather the list, but this time note the source table with 'A' for actor and 'C' for customer
select first_name, 'A' as source from actor
union 
select first_name, 'C' as source from customer
where first_name like 'E%';