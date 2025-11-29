CREATE DATABASE IF NOT EXISTS smart_parking;
USE smart_parking;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) DEFAULT 'user'
);

CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    type VARCHAR(20),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE slots (
    id INT AUTO_INCREMENT PRIMARY KEY,
    slot_number VARCHAR(10),
    type VARCHAR(20),
    status VARCHAR(20) DEFAULT 'available',
    vehicle_id INT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

CREATE TABLE tickets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT,
    slot_id INT,
    entry_time DATETIME,
    exit_time DATETIME,
    total_charge DOUBLE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    FOREIGN KEY (slot_id) REFERENCES slots(id)
);

-- Insert a few initial slots
INSERT INTO slots(slot_number, type, status) VALUES
('A1', 'Compact', 'available'),
('A2', 'Large', 'available'),
('E1', 'Electric', 'available');
