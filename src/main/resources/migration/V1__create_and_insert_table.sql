-- Создание таблицы student_flux
CREATE TABLE IF NOT EXISTS student_flux (
    id SERIAL PRIMARY KEY,          -- Идентификатор (автоинкрементируемый)
    fio VARCHAR(255) NOT NULL,      -- ФИО студента
    email VARCHAR(255) UNIQUE NOT NULL,  -- Email (уникальный)
    courses_list TEXT[]             -- Список курсов (массив строк)
);

-- Вставка тестовых данных в таблицу student_flux
INSERT INTO student_flux (fio, email, courses_list)
VALUES
    ('Иван Иванов', 'ivan.ivanov@example.com', ARRAY['Java Programming', 'Spring Boot Mastery']),
    ('Мария Смирнова', 'maria.smirnova@example.com', ARRAY['Database Fundamentals', 'Cloud Computing Basics']),
    ('Алексей Петров', 'alexey.petrov@example.com', ARRAY['Advanced Java', 'Spring Boot Mastery']),
    ('Екатерина Сидорова', 'ekaterina.sidorova@example.com', ARRAY['Java Programming', 'Cloud Computing Basics']),
    ('Сергей Кузнецов', 'sergey.kuznetsov@example.com', ARRAY['Database Fundamentals']);
