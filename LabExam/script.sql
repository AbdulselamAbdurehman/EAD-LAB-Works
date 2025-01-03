-- Create a new database called BookstoreDB and a table called Books
CREATE DATABASE BookstoreDB;

-- Switch to the BookstoreDB database
USE BookstoreDB;

-- Create a table called Books with columns id, title, author, and price
CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);
