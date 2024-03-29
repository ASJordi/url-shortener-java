CREATE DATABASE IF NOT EXISTS url_shortener_db;

USE url_shortener_db;

CREATE TABLE urls(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255) NOT NULL,
    hash VARCHAR(255) NOT NULL,
    created DATE NOT NULL
);