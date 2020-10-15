-- The following queries utilize the "dvdstore" database.

-- 1. All of the films that Nick Stallone has appeared in
-- (30 rows)
SELECT f.title FROM film f
JOIN film_actor  fa ON  fa.film_id= f.film_id
JOIN actor a ON a.actor_id=fa.actor_id
WHERE a.first_name='NICK' AND a.last_name='STALLONE';

-- 2. All of the films that Rita Reynolds has appeared in
-- (20 rows)
SELECT f.title FROM film f
JOIN film_actor  fa ON  fa.film_id= f.film_id
JOIN actor a ON a.actor_id=fa.actor_id
WHERE a.first_name='RITA' AND a.last_name='REYNOLDS';
-- 3. All of the films that Judy Dean or River Dean have appeared in
-- (46 rows)
SELECT f.title FROM film f
JOIN film_actor  fa on  fa.film_id= f.film_id
JOIN actor a on a.actor_id=fa.actor_id
WHERE (a.first_name='JUDY'AND a.last_name='DEAN') OR (a.first_name='RIVER'AND a.last_name='DEAN');
-- 4. All of the the ‘Documentary’ films
-- (68 rows)
SELECT c.name,f.title FROM film f
JOIN film_category fc ON fc.film_id=f.film_id
JOIN category c ON c.category_id= fc.category_id
WHERE name ='Documentary';
-- 5. All of the ‘Comedy’ films
-- (58 rows)
SELECT c.name,f.title FROM film f
JOIN film_category fc ON fc.film_id=f.film_id
JOIN category c ON c.category_id= fc.category_id
WHERE name ='Comedy';
-- 6. All of the ‘Children’ films that are rated ‘G’
-- (10 rows)
SELECT c.name,f.title FROM film f
JOIN film_category fc ON fc.film_id=f.film_id
JOIN category c ON c.category_id= fc.category_id
WHERE rating ='G' AND name='Children';
-- 7. All of the ‘Family’ films that are rated ‘G’ and are less than 2 hours in length
-- (3 rows)
SELECT c.name,f.title FROM film f
JOIN film_category fc ON fc.film_id=f.film_id
JOIN category c on c.category_id= fc.category_id
WHERE name ='Family' AND length <120 AND rating='G';
-- 8. All of the films featuring actor Matthew Leigh that are rated ‘G’
-- (9 rows)
SELECT f.title FROM film f
JOIN film_actor fa ON fa.film_id = f.film_id
JOIN actor a ON a.actor_id=fa.actor_id
WHERE a.first_name='MATTHEW' AND a.last_name='LEIGH' AND rating='G';
-- 9. All of the ‘Sci-Fi’ films released in 2006
-- (61 rows)
SELECT f.title AS sci_fi_films FROM film f
JOIN film_category fc on fc.film_id =f.film_id
JOIN category c ON c.category_id = fc.category_id
WHERE name='Sci-Fi' AND release_year=2006;
-- 10. All of the ‘Action’ films starring Nick Stallone
-- (2 rows)
SELECT f.title AS action_films FROM film f
JOIN film_actor fa ON fa.film_id = f.film_id
JOIN actor a ON a.actor_id= fa.actor_id
JOIN film_category fc ON fc.film_id =fa.film_id
JOIN category c ON c.category_id=fc.category_id
WHERE a.first_name='NICK' AND a.last_name='STALLONE' AND c.name='Action'; 
-- 11. The address of all stores, including street address, city, district, and country
-- (2 rows)
SELECT a.address,c.city,a.district,cntr.country FROM address a
JOIN city c ON c.city_id= a.city_id
JOIN country cntr ON cntr.country_id=c.country_id
JOIN store s ON s.address_id = a.address_id;
-- 12. A list of all stores by ID, the store’s street address, and the name of the store’s manager
-- (2 rows)
SELECT store.store_id,address.address,staff.first_name, staff.last_name FROM store
JOIN staff ON staff.store_id=store.store_id
JOIN address ON address.address_id =staff.address_id;

-- 13. The first and last name of the top ten customers ranked by number of rentals 
-- (#1 should be “ELEANOR HUNT�? with 46 rentals, #10 should have 39 rentals)
SELECT COUNT(customer.customer_id), customer.first_name, customer.last_name FROM customer
JOIN payment ON payment.customer_id= customer.customer_id
GROUP BY customer.first_name, customer.last_name
ORDER BY COUNT(customer.customer_id) DESC LIMIT 10;
-- 14. The first and last name of the top ten customers ranked by dollars spent 
-- (#1 should be “KARL SEAL�? with 221.55 spent, #10 should be “ANA BRADLEY�? with 174.66 spent)
SELECT SUM(payment.amount)AS dollar_spent, customer.first_name||', '|| customer.last_name AS top_10_customer FROM customer
JOIN payment ON payment.customer_id= customer.customer_id
GROUP BY customer.first_name, customer.last_name
ORDER BY SUM(payment.amount) DESC LIMIT 10;
-- 15. The store ID, street address, total number of rentals, total amount of sales (i.e. payments), and average sale of each store.
-- (NOTE: Keep in mind that an employee may work at multiple stores.)
-- (Store 1 has 7928 total rentals and Store 2 has 8121 total rentals)
select s.store_id,a.address,COUNT(i.inventory_id),SUM(p.amount),AVG(p.amount)
FROM store s
JOIN address a ON a.address_id = s.address_id
join inventory i on i.store_id=s.store_id  
JOIN rental r ON r.inventory_id = i.inventory_id
JOIN payment p ON p.rental_id=r.rental_id
GROUP BY s.store_id, a.address;
-- 16. The top ten film titles by number of rentals
-- (#1 should be “BUCKET BROTHERHOOD�? with 34 rentals and #10 should have 31 rentals)
SELECT f.title , COUNT(r.*) AS top_rental
FROM film f
JOIN inventory i ON i.film_id = f.film_id
JOIN rental r ON r.inventory_id = i.inventory_id
GROUP BY f.title
ORDER BY top_rental DESC LIMIT 10;
-- 17. The top five film categories by number of rentals 
-- (#1 should be “Sports�? with 1179 rentals and #5 should be “Family�? with 1096 rentals)
SELECT c.name , COUNT(r.*) AS toprental
FROM film f
JOIN inventory i ON i.film_id = f.film_id
JOIN rental r ON r.inventory_id = i.inventory_id
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category c ON c.category_id = fc.category_id
GROUP BY c.name
ORDER BY toprental DESC LIMIT 5;
-- 18. The top five Action film titles by number of rentals 
-- (#1 should have 30 rentals and #5 should have 28 rentals)
SELECT f.title , COUNT(r.*) AS toprental
FROM film f
JOIN inventory i ON i.film_id = f.film_id
JOIN rental r ON r.inventory_id = i.inventory_id
JOIN film_category fc ON fc.film_id = f.film_id
JOIN category c ON c.category_id = fc.category_id
WHERE c.name='Action'
GROUP BY f.title
ORDER BY toprental DESC LIMIT 5;
-- 19. The top 10 actors ranked by number of rentals of films starring that actor 
-- (#1 should be “GINA DEGENERES�? with 753 rentals and #10 should be “SEAN GUINESS�? with 599 rentals)
SELECT a.actor_id, a.first_name, a.last_name,count(*) FROM rental r
JOIN inventory i ON i.inventory_id= r.inventory_id
join film_actor fa on fa.film_id =i.film_id
join actor a on a.actor_id = fa.actor_id
group by a.actor_id,a.first_name,a.last_name
order by count(*) desc limit 10;

-- 20. The top 5 “Comedy�? actors ranked by number of rentals of films in the “Comedy�? category starring that actor 
-- (#1 should have 87 rentals and #5 should have 72 rentals)
SELECT a.actor_id, a.first_name, a.last_name,count(*) FROM rental r
JOIN inventory i ON i.inventory_id= r.inventory_id
join film_actor fa on fa.film_id =i.film_id
join actor a on a.actor_id = fa.actor_id
join film_category fc on fc.film_id= i.film_id
join category c on c.category_id=fc.category_id
where c.name ='Comedy'
group by a.actor_id,a.first_name,a.last_name
order by count(*) desc limit 10;