-- Write queries to return the following:
-- The following changes are applied to the "dvdstore" database.**

-- 1. Add actors, Hampton Avenue, and Lisa Byway to the actor table.
START TRANSACTION;
INSERT INTO actor(first_name,last_name) VALUES('HAMPTON','AVENUE'),('LISA', 'BYWAY');
SELECT * FROM actor;
COMMIT;
ROLLBACK;
-- 2. Add "Euclidean PI", "The epic story of Euclid as a pizza delivery boy in
-- ancient Greece", to the film table. The movie was released in 2008 in English.
-- Since its an epic, the run length is 3hrs and 18mins. There are no special
-- features, the film speaks for itself, and doesn't need any gimmicks.


START TRANSACTION;
INSERT INTO film (title, description, release_year, language_id,length)
VALUES ('EUCLIDEAN PI','The epic story of Euclid as a pizza delivery boy in ancient Greece','2008','1','198');
SELECT * FROM film WHERE title ='EUCLIDEAN PI';
COMMIT;
-- 3. Hampton Avenue plays Euclid, while Lisa Byway plays his slightly
-- overprotective mother, in the film, "Euclidean PI". Add them to the film.


START TRANSACTION;

INSERT INTO film_actor(actor_id,film_id)
VALUES ((SELECT actor_id FROM actor WHERE first_name = 'HAMPTON' AND last_name = 'AVENUE'),
(SELECT film_id FROM film WHERE title = 'EUCLIDEAN PI'));

INSERT INTO film_actor(actor_id,film_id)
VALUES ((SELECT actor_id FROM actor WHERE first_name = 'LISA' AND last_name = 'BYWAY'),
(SELECT film_id FROM film WHERE title = 'EUCLIDEAN PI'));

SELECT * FROM film_actor WHERE film_id = 1003;
COMMIT;

ROLLBACK;
-- 4. Add Mathmagical to the category table.
START TRANSACTION;
INSERT INTO category (category_id, name) VALUES (17, 'Mathmagical');
SELECT name FROM category;
ROLLBACK;
COMMIT;
-- 5. Assign the Mathmagical category to the following films, "Euclidean PI",
-- "EGG IGBY", "KARATE MOON", "RANDOM GO", and "YOUNG LANGUAGE"
START TRANSACTION;
INSERT INTO film_category(film_id,category_id)
VALUES ((SELECT film_id FROM film WHERE  title = 'EUCLIDEAN PI'),(SELECT category_id FROM category WHERE name = 'Mathmagical'));

UPDATE film_category 
SET category_id = 17 
WHERE  film_id = 274 OR film_id = 494 OR film_id = 714 OR film_id = 996;

ROLLBACK;

SELECT f.title,c.name FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON c.category_id = fc.category_id
WHERE c.name='Mathmagical';

COMMIT;
;
-- 6. Mathmagical films always have a "G" rating, adjust all Mathmagical films
-- accordingly.
-- (5 rows affected)
START TRANSACTION;
UPDATE film SET rating = 'G'
WHERE film_id IN
(SELECT film_id FROM film_category WHERE category_id =
(SELECT category_id FROM category WHERE name = 'Mathmagical'));
COMMIT;
ROLLBACK;
--TESTING
SELECT title, rating FROM film
WHERE film_id IN
(SELECT film_id FROM film_category WHERE category_id =
(SELECT category_id FROM category WHERE name = 'Mathmagical'));
-- 7. Add a copy of "Euclidean PI" to all the stores.

START TRANSACTION;
INSERT INTO inventory(film_id,store_id)
VALUES((SELECT film_id FROM film WHERE title = 'EUCLIDEAN PI'),1);

INSERT INTO inventory(film_id,store_id)
VALUES((SELECT film_id FROM film WHERE title = 'EUCLIDEAN PI'),2);

COMMIT;
ROLLBACK;

SELECT * FROM inventory
ORDER BY film_id DESC;


-- 8. The Feds have stepped in and have impounded all copies of the pirated film,
-- "Euclidean PI". The film has been seized from all stores, and needs to be
-- deleted from the film table. Delete "Euclidean PI" from the film table.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE> --It does not work, because the Euclidean PI is being associated in other tables.
START TRANSACTION;

DELETE FROM film WHERE film_id=1003;

COMMIT;

ROLLBACK;
--It does not work, because the Euclidean PI is being associated in other tables.

-- 9. Delete Mathmagical from the category table.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE>
START TRANSACTION;

DELETE FROM category
WHERE category_id = 17;

ROLLBACK;
--It does not work for the same reason as previous.  The Mathmagical is being used in other tables so we may not delete it.


-- 10. Delete all links to Mathmagical in the film_category tale.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE>
START TRANSACTION;

DELETE FROM film_category
WHERE category_id = 17;

ROLLBACK;

COMMIT;
--yes, here we are deleting primary, so it will allow us.

-- 11. Retry deleting Mathmagical from the category table, followed by retrying
-- to delete "Euclidean PI".
-- (Did either deletes succeed? Why?)
-- <YOUR ANSWER HERE>
START TRANSACTION;

DELETE FROM category
WHERE category_id = 17;

ROLLBACK;

COMMIT;
SELECT category_id FROM category WHERE category_id=17;
--yes, because the primary key has been deleted, it will now allow us to delete its foreign keys.

-- 12. Check database metadata to determine all constraints of the film id, and
-- describe any remaining adjustments needed before the film "Euclidean PI" can
-- be removed from the film table.
START TRANSACTION;

COMMIT;

ROLLBACK;