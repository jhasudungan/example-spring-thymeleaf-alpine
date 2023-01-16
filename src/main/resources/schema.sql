CREATE TABLE category (
	category_id varchar(200) NOT null primary KEY,
	category_name varchar(200) NOT NULL,
	category_description varchar(500),
	created_date date not null,
	last_modified date not null
);

create table product (
	product_id varchar(200) NOT null primary KEY,
	product_name varchar(200) not null,
	product_description varchar(500),
	category_id varchar(200) NOT null,
	created_date date not null,
	last_modified date not null
);