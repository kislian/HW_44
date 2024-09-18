-- public.faculty определение

-- Drop table

-- DROP TABLE public.faculty;

CREATE TABLE public.faculty (
	id int8 NOT NULL,
	color varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT faculty_pkey PRIMARY KEY (id)
);