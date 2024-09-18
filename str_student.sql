-- public.student определение

-- Drop table

-- DROP TABLE public.student;

CREATE TABLE public.student (
	id int8 NOT NULL,
	age int4 NOT NULL,
	"name" varchar(255) NULL,
	faculty_id int8 NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id)
);


-- public.student внешние включи

ALTER TABLE public.student ADD CONSTRAINT fk6geq7tnjed7u4hvgv1ac6lyh FOREIGN KEY (faculty_id) REFERENCES public.faculty(id);