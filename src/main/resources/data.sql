-- Вставка данных в таблицу "Адрес"
INSERT INTO address (city, street, number_of_build) VALUES
('Moscow', 'Arbat', '12A'),
('Saint Petersburg', 'Nevsky Prospect', '10B'),
('Novosibirsk', 'Lenina', '5C'),
('Kazan', 'Baumana', '20'),
('Sochi', 'Kurortny', '7D');

-- Вставка данных в таблицу "Склад"
INSERT INTO store (address_id, capacity, fullness) VALUES
(1, 1000, 500),
(2, 2000, 1200),
(3, 1500, 800),
(4, 1800, 1700),
(5, 1200, 600);

-- Вставка данных в таблицу "Магазин"
INSERT INTO shop (address_id, store_id, shop_name) VALUES
(1, 1, 'Shop Moscow 1'),
(2, 2, 'Shop Saint Petersburg 1'),
(3, 3, 'Shop Novosibirsk 1'),
(4, 4, 'Shop Kazan 1'),
(5, 5, 'Shop Sochi 1');

-- Вставка данных в таблицу "Товар"
INSERT INTO product (procuct_name, description, shop_id) VALUES
('Laptop', 'High performance laptop', 1),
('Smartphone', 'Latest model smartphone', 2),
('Headphones', 'Noise-cancelling headphones', 3),
('Monitor', '4K UHD monitor', 4),
('Mouse', 'Ergonomic wireless mouse', 5);

-- Вставка данных в таблицу "Продавец"
INSERT INTO saller (fio, cust_position, store_id, salary) VALUES
('Ivanov Ivan', 'Manager', 1, 50000),
('Petrov Petr', 'Cashier', 2, 35000),
('Sidorov Sidr', 'Warehouse Worker', 3, 30000),
('Smirnova Olga', 'Sales Consultant', 4, 40000),
('Fedorov Fyodor', 'Logistics Specialist', 5, 45000);
