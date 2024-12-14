-- 1. SELECT — Получение всех данных из таблицы "Адрес"
SELECT * FROM address;

-- 2. SELECT — Получение информации о складе с определённым address_id
SELECT * FROM store WHERE address_id = 41;

-- 3. SELECT — Получение списка магазинов и их адресов
SELECT s.shop_name, a.city, a.street, a.number_of_build
FROM shop s
JOIN address a ON s.address_id = a.id;

-- 4. SELECT — Получение товаров, которые продаются в магазине с shop_id = 1
SELECT p.product_name, p.description
FROM product p
WHERE p.shop_id = 1;

-- 5. UPDATE — Обновление информации о складе (изменение вместимости)
UPDATE store
SET capacity = 2000
WHERE address_id = 41;

-- 6. UPDATE — Изменение зарплаты продавца
UPDATE seller
SET salary = 55000
WHERE fio = 'Ivanov Ivan';

-- 7. DELETE — Удаление товара из магазина
DELETE FROM product
WHERE product_name = 'Mouse';

-- 8. DELETE — Удаление продавца по его имени
DELETE FROM seller
WHERE fio = 'Petrov Petr';

-- 9. INSERT INTO — Добавление нового адреса
INSERT INTO address (city, street, number_of_build)
VALUES ('Vladivostok', 'Svetlaya', '15B');

-- 10. INSERT INTO — Добавление нового склада с использованием существующего address_id
INSERT INTO store (address_id, capacity, fullness)
VALUES (46, 2500, 1000);

-- 11. INSERT INTO — Добавление нового магазина
INSERT INTO shop (address_id, store_id, shop_name)
VALUES (41, 46, 'Shop Vladivostok 1');

-- 12. SELECT — Получение всех продавцов и их магазинов
SELECT s.fio, s.cust_position, sh.shop_name
FROM seller s
JOIN store st ON s.store_id = st.id
JOIN shop sh ON st.id = sh.store_id;
