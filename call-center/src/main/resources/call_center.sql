-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
DROP DATABASE IF EXISTS almundo;
CREATE DATABASE almundo;
-- -- ddl-end --
-- 

-- object: public.customer | type: TABLE --
DROP TABLE IF EXISTS public.customer CASCADE;
CREATE TABLE public.customer(
	customer_id bigserial NOT NULL,
	name varchar NOT NULL,
	identification varchar NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY (customer_id)

);
-- ddl-end --
ALTER TABLE public.customer OWNER TO postgres;
-- ddl-end --

-- object: public.employee | type: TABLE --
DROP TABLE IF EXISTS public.employee CASCADE;
CREATE TABLE public.employee(
	employee_id bigserial NOT NULL,
	name varchar NOT NULL,
	number varchar NOT NULL,
	free bool NOT NULL DEFAULT true,
	role_id bigserial,
	CONSTRAINT employee_pk PRIMARY KEY (employee_id)

);
-- ddl-end --
ALTER TABLE public.employee OWNER TO postgres;
-- ddl-end --

-- object: public.role | type: TABLE --
DROP TABLE IF EXISTS public.role CASCADE;
CREATE TABLE public.role(
	role_id bigserial NOT NULL,
	name varchar NOT NULL,
	CONSTRAINT role_pk PRIMARY KEY (role_id)

);
-- ddl-end --
ALTER TABLE public.role OWNER TO postgres;
-- ddl-end --

-- object: public.call | type: TABLE --
DROP TABLE IF EXISTS public.call CASCADE;
CREATE TABLE public.call(
	call_id bigserial NOT NULL,
	start timestamp NOT NULL,
	"end" timestamp NOT NULL,
	customer_id bigserial,
	employee_id bigserial,
	CONSTRAINT call_pk PRIMARY KEY (call_id)

);
-- ddl-end --
ALTER TABLE public.call OWNER TO postgres;
-- ddl-end --

-- object: role_fk | type: CONSTRAINT --
ALTER TABLE public.employee DROP CONSTRAINT IF EXISTS role_fk CASCADE;
ALTER TABLE public.employee ADD CONSTRAINT role_fk FOREIGN KEY (role_id)
REFERENCES public.role (role_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: customer_fk | type: CONSTRAINT --
ALTER TABLE public.call DROP CONSTRAINT IF EXISTS customer_fk CASCADE;
ALTER TABLE public.call ADD CONSTRAINT customer_fk FOREIGN KEY (customer_id)
REFERENCES public.customer (customer_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: employee_id | type: CONSTRAINT --
ALTER TABLE public.call DROP CONSTRAINT IF EXISTS employee_id CASCADE;
ALTER TABLE public.call ADD CONSTRAINT employee_id FOREIGN KEY (employee_id)
REFERENCES public.employee (employee_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


INSERT INTO public.role(role_id, name) VALUES (1, 'operador');
INSERT INTO public.role(role_id, name) VALUES (2, 'supervisor');
INSERT INTO public.role(role_id, name) VALUES (3, 'director');
INSERT INTO public.customer(customer_id, name, identification) VALUES (1, 'DANIELA', '123451');
INSERT INTO public.customer(customer_id, name, identification) VALUES (2, 'DANIEL', '123452');
INSERT INTO public.customer(customer_id, name, identification) VALUES (3, 'DAYANA', '123453');
INSERT INTO public.customer(customer_id, name, identification) VALUES (4, 'DANILO', '123454');
INSERT INTO public.customer(customer_id, name, identification) VALUES (5, 'ELIDA', '123455');
INSERT INTO public.customer(customer_id, name, identification) VALUES (6, 'EDUARDO', '123456');
INSERT INTO public.customer(customer_id, name, identification) VALUES (7, 'GABRIELA', '123457');
INSERT INTO public.customer(customer_id, name, identification) VALUES (8, 'GABRIEL', '123458');
INSERT INTO public.customer(customer_id, name, identification) VALUES (9, 'JANET', '123459');
INSERT INTO public.customer(customer_id, name, identification) VALUES (10, 'JUAN', '1234510');
INSERT INTO public.customer(customer_id, name, identification) VALUES (11, 'LILIANA', '1234511');
INSERT INTO public.customer(customer_id, name, identification) VALUES (12, 'LEONEL', '1234512');
INSERT INTO public.customer(customer_id, name, identification) VALUES (13, 'LAURA', '1234513');
INSERT INTO public.customer(customer_id, name, identification) VALUES (14, 'MARIANA', '1234514');
INSERT INTO public.customer(customer_id, name, identification) VALUES (15, 'MARIO', '1234515');
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (1, 'ANA', '12341', true, 1);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (2, 'ALFREDO', '12342', true, 1);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (3, 'ALEJANDRA', '12343', true, 1);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (4, 'ALEJANDRO', '12344', true, 1);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (5, 'AMALIA', '12345', true, 1);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (6, 'ARNULFO', '12346', true, 2);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (7, 'BIBIANA', '12347', true, 2);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (8, 'BETO', '12348', true, 2);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (9, 'CAMILA', '12349', true, 3);
INSERT INTO public.employee(employee_id, name, "number", free, role_id)	VALUES (10, 'CAMILO', '123410', true, 3);
INSERT INTO public.call(call_id, "start", "end", customer_id, employee_id) VALUES (1, TIMESTAMP '2018-01-28 15:36:38', TIMESTAMP '2018-01-28 15:42:38', 1, 2);
INSERT INTO public.call(call_id, "start", "end", customer_id, employee_id) VALUES (2, TIMESTAMP '2018-01-28 15:30:38', TIMESTAMP '2018-01-28 15:38:38', 2, 1);


ALTER SEQUENCE public.customer_customer_id_seq
    INCREMENT 1
    START 16
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

ALTER SEQUENCE public.call_call_id_seq
    INCREMENT 1
    START 13
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.employee_employee_id_seq
    INCREMENT 1
    START 10
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;