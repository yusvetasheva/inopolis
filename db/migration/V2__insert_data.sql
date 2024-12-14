-- Вставка данных в таблицу "Адрес"
INSERT INTO address (city, street, number_of_build) VALUES
('Moscow', 'Arbat', '12A'),
('Saint Petersburg', 'Nevsky Prospect', '10B'),
('Novosibirsk', 'Lenina', '5C'),
('Kazan', 'Baumana', '20'),
('Sochi', 'Kurortny', '7D');

-- Вставка данных в таблицу "Склад"
INSERT INTO store (address_id, capacity, fullness) VALUES
(41, 1000, 500),
(42, 2000, 1200),
(43, 1500, 800),
(44, 1800, 1700),
(45, 1200, 600);
