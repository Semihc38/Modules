-- Create a database
CREATE DATABASE ddlexample;
-- Drop a database
DROP DATABASE ddlexample;

-- Create table
CREATE TABLE example_table (
        id               SERIAL PRIMARY KEY,
        username         VARCHAR(100) NOT NULL,
        first_name       VARCHAR (20),
        last_name        VARCHAR (20),
        address_id       INTEGER
);
-- Drop Table/Drop Table If It Exists
DROP TABLE  example_table;
DROP TABLE if exists example_table;

-- Alter a table (constraints, structure)

-- Add a primary key
ALTER TABLE  example_table add constraint pk_example_table_id primary key (id);
-- Add a foreign key
create table address(
address_id serial primary key
);
ALTER TABLE  example_table add constraint fk_example_table_address_id foreign key (address_id) references address(address_id);

alter table example_table add constraint uniq_example_table_username unique (username);
-- Add a check
alter table example_table add constraint chk_example_table_username check(username!='test');
-- Sequences

-- Sequence created via serial

-- Sequence created manually
create sequence custome_seq start 100 increment by 1;
-- Getting next seaquence value
select nextval('custome_seq');
--Alter sequence 
alter sequence example_table_id_seq restart 8;
--alter table example_table modify
-- Inserting using sequences

-- 1 using select nextval
insert into example_table values((select nextval('example_table_id_seq')),' nocolumnuser','No','Column',null);

select *from example_table;
insert into example_table values((select nextval('example_table_id_seq')),' nocolumnuser2','No','Column',null);
-- 2 using DEFAULT
insert into example_table values(default,  'dwfaultsequser','Example','Default',null);
-- 3 omitting id field
insert into example_table(username,first_name,last_name)values ('omituser', 'example','omit');
alter sequence example_table_id_seq restart 12;
insert into example_table(12,'manualsequser3','example','manual3',null);
-- You can insert ids manually but that should be avoided if you are using sequences. Here's why:

-- Insert a row using hardcoded id after last id value

-- Now Insert using the sequence... what happens?

-- How do you resolve this?


-- Sequences and rollbacks
start transaction;
insert into example_table(username,first_name,last_name)values ('transactionuser', 'example','transaction');
rollback;

