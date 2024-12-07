-- Crear bases de datos para cada servicio
CREATE DATABASE users_service;
CREATE DATABASE books_service;
CREATE DATABASE loans_service;
CREATE DATABASE reports_service;

-- Switch a cada base de datos y crear tablas iniciales

-- Base de datos: users_service
\c users_service;
CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    status BOOLEAN DEFAULT true,
    role_id INT REFERENCES roles(id)
);

-- Base de datos: books_service
\c books_service;
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    category VARCHAR(50),
    available BOOLEAN DEFAULT true,
    location VARCHAR(255),
    quantity INT DEFAULT 0
);

-- Base de datos: loans_service
\c loans_service;
CREATE TABLE loans (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    returned BOOLEAN DEFAULT false,
    fine DECIMAL(10, 2) DEFAULT 0.00
);

-- Base de datos: reports_service
\c reports_service;
CREATE TABLE reports (
    id SERIAL PRIMARY KEY,
    report_type VARCHAR(50) NOT NULL,
    generated_date DATE NOT NULL,
    generated_by VARCHAR(255),
    file_path VARCHAR(255)
);
