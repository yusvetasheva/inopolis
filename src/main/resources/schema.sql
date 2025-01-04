--Таблица "Адрес", хранит данные об адресе магазина/склада (город, улица, номер постройки)
CREATE TABLE IF NOT EXISTS address(
	id serial PRIMARY KEY,
	city VARCHAR(60),
	street VARCHAR(60),
	number_of_build VARCHAR(60)
);

--Таблица "Склад", хранит данные об адресе, вместимости и заполненности склада
CREATE TABLE IF NOT EXISTS store(
	id serial PRIMARY KEY,
	address_id INTEGER,
	capacity INTEGER,
	fullness INTEGER,
	FOREIGN KEY (address_id) REFERENCES address (Id)
);

--Таблица "Магазин", хранит данные об адресе, складе, названии магазина
CREATE TABLE IF NOT EXISTS shop(
	id serial PRIMARY KEY,
	address_id INTEGER,
	store_id INTEGER,
	shop_name VARCHAR(600),
	FOREIGN KEY (address_id) REFERENCES address (Id),
	FOREIGN KEY (store_id) REFERENCES store (Id)
);

--Таблица "Товар", хранит данные об названии товара, описании, магазине
CREATE TABLE IF NOT EXISTS product(
	id serial PRIMARY KEY,
	procuct_name VARCHAR(100),
	description VARCHAR(600),
	shop_id INTEGER,
	FOREIGN KEY (shop_id) REFERENCES shop (Id)
);

--Таблица "Продавец", хранит данные об названии товара, описании, магазине
CREATE TABLE IF NOT EXISTS saller(
	id serial PRIMARY KEY,
	fio VARCHAR(100),
	cust_position VARCHAR(100),
	store_id INTEGER,
	salary INTEGER,
	FOREIGN KEY (store_id) REFERENCES store (Id)
);

drop table customer;


