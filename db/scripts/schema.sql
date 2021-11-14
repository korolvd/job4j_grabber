create table if not exists post (
id serial primary key,
name text,
text text,
link varchar(200) unique,
created_date timestamp
);