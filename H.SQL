CREATE DATABASE hotel_db;

USE hotel_db;

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100),
    room_type VARCHAR(50),
    check_in DATE,
    check_out DATE
);
