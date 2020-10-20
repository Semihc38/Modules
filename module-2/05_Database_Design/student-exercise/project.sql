START TRANSACTION;

CREATE TABLE departments (
        dept_id serial,
        name varchar(255) NOT NULL UNIQUE,
        CONSTRAINT pk_departments_id PRIMARY KEY (dept_id)   
 );

CREATE TABLE job_titles (
        job_id serial,
        title varchar(255) NOT NULL UNIQUE,
        CONSTRAINT pk_job_titles_id PRIMARY KEY (job_id)       
);
 
CREATE TABLE employees (
        employee_id serial,
        first_name varchar(255) NOT NULL,
        last_name varchar(255) NOT NULL,
        gender char(1),
        birthday date,
        hiredate date NOT NULL,
        departments_id integer NOT NULL,
        job_titles_id integer NOT NULL,
        CONSTRAINT pk_employees_id PRIMARY KEY (employee_id),
        CONSTRAINT fk_job_titles_id FOREIGN KEY (job_titles_id) REFERENCES job_titles(job_id),
        CONSTRAINT fk_departments_id FOREIGN KEY (departments_id) REFERENCES departments(dept_id)
);

CREATE TABLE projects (
        project_id serial,
        name varchar(255) NOT NULL UNIQUE,
        employees_id integer NOT NULL,
        startdate date NOT NULL,
        CONSTRAINT pk_projects_id PRIMARY KEY (project_id),
        CONSTRAINT fk_employees_id FOREIGN KEY (employees_id) REFERENCES employees(employee_id)
);
INSERT INTO departments (name) VALUES ('Project Management');
INSERT INTO departments (name) VALUES ('Reasearch and Development');
INSERT INTO departments (name) VALUES ('Human Resources');
INSERT INTO departments (name) VALUES ('Finance'); 
 

INSERT INTO job_titles (title) VALUES ('Associate');
INSERT INTO job_titles (title) VALUES ('Developer');
INSERT INTO job_titles (title) VALUES ('Director');
INSERT INTO job_titles (title) VALUES ('Manager');

INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Ben','Bill','2019-01-01',1,1);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Semih','Cetin','2020-12-21',1,3);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('William','Day','2003-01-01',2,3);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Elie','Rosario','2004-01-01',2,2);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Nate','Kirk','2005-01-01',3,4);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('John','Sonmez','2006-01-01',3,3);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Tim','Vovak','2007-01-01',4,4);
INSERT INTO employees (first_name,last_name,hiredate,departments_id,job_titles_id) VALUES ('Tyrone','Savage','2008-01-01',4,1); 

INSERT INTO projects (name,employees_id,startdate) VALUES ('VendingMachine',10,'2013-06-10');
INSERT INTO projects (name,employees_id,startdate) VALUES ('FleepApp',11,'2011-09-10');
INSERT INTO projects (name,employees_id,startdate) VALUES ('Vytrack',12,'2009-06-11');
INSERT INTO projects (name,employees_id,startdate) VALUES ('DataSecure',13,'2017-08-10');


SELECT * FROM projects;
SELECT * FROM job_titles;
SELECT * FROM departments;
SELECT * FROM employees;

 ROLLBACK;
 
 COMMIT;