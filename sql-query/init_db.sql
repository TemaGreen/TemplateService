 create table if not exists public.template(
 	id bigint primary key,
 	name text,
 	value text,
 	create_date timestamp,
 	path text, 	
 	startdate date,
 	enddate date
 );
 
 create table if not exists public.reference_jeneral(
 	id bigint primary key,
 	id_faset bigint,
 	id_pfaset bigint,
 	num int,
 	name text,
 	value text
 );
 
 create table if not exists public.template_service(
 	id bigint primary key,
 	template bigint,
 	dictionary bigint,
 	constraint FK_template foreign key(template)
 		references public.template(id) on delete cascade,
 	constraint FK_reference_jeneral foreign key(dictionary)
 		references public.reference_jeneral(id) on delete cascade
 );
 
CREATE SEQUENCE IF NOT EXISTS public.template_sequence
START WITH 1
INCREMENT BY 1
NO MINVALUE 
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.reference_jeneral_sequence
START WITH 1
INCREMENT BY 1
NO MINVALUE 
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.template_service_sequence
START WITH 1
INCREMENT BY 1
NO MINVALUE 
NO MAXVALUE
CACHE 1;
 
 insert into template(id, name, value, create_date, path, startdate, enddate) values
 	(nextval('template_sequence'), 'Тест1', 'test1', current_date, 'template/test_1.jrxml', cast(current_date as date), null),
 	(nextval('template_sequence'), 'Тест2', 'test2', (current_date - interval '1 day'), 'template/test_2.jrxml', cast((current_date - interval '1 day') as date ), null),
 	(nextval('template_sequence'), 'Тест3', 'test3', (current_date - interval '2 day'), 'template/test_3.pdf', cast((current_date - interval '2 day') as date), null);
 
 insert into reference_jeneral(id, id_faset, id_pfaset, num, name, value) values
	(nextval('reference_jeneral_sequence'), 1, null, null, 'Шаблон значений для отчета test1', '');
 
 insert into reference_jeneral(id, id_faset, id_pfaset, num, name, value) values
	(nextval('reference_jeneral_sequence'), 1, null, 0, 'Имя', 'name'),
 	(nextval('reference_jeneral_sequence'), 1, null, 1, 'Фамилия', 'surename');
 
 insert into template_service(id, template, dictionary) values 
 	(nextval('template_service_sequence'), (select t.id from template t where t.value = 'test1'), (select rj.id from reference_jeneral rj where name = 'Шаблон значений для отчета test1')),
 	(nextval('template_service_sequence'), (select t.id from template t where t.value = 'test2'), null),
 	(nextval('template_service_sequence'), (select t.id from template t where t.value = 'test3'), null);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 