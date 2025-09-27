-- Modified Movie Ticket Booking System Database Schema
-- Without separate Users table - Admin and Customer have their own authentication

CREATE DATABASE IF NOT EXISTS booking_db;
USE booking_db;

-- Table: admin (with authentication fields)
CREATE TABLE admin (
    admin_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    admin_name VARCHAR(100) NOT NULL,
    admin_contact VARCHAR(20),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table: customers (with authentication fields, no user_id)
CREATE TABLE customers (
    customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile_number VARCHAR(20),
    password VARCHAR(255) NOT NULL
);

-- Table: movies
CREATE TABLE movies (
    movie_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    movie_name VARCHAR(255) NOT NULL,
    description TEXT,
    language VARCHAR(50),
    movie_genre VARCHAR(50),
    movie_hours INT
);

-- Table: theatres (linked to admin)
CREATE TABLE theatres (
    theatre_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    theatre_name VARCHAR(255) NOT NULL,
    theatre_contact VARCHAR(20),
    theatre_address VARCHAR(255),
    admin_id INT,
    FOREIGN KEY (admin_id) REFERENCES admin(admin_id)
);

-- Table: screens
CREATE TABLE screens (
    screen_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    screen_name VARCHAR(255) NOT NULL,
    row_count INT NOT NULL,
    column_count INT NOT NULL,
    theatre_id INT NOT NULL,
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id)
);

-- Table: seats
CREATE TABLE seats (
    seat_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(10) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_booked BOOLEAN NOT NULL DEFAULT FALSE,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE,
    screen_id INT NOT NULL,
    FOREIGN KEY (screen_id) REFERENCES screens(screen_id)
);

-- Table: shows
CREATE TABLE shows (
    show_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    show_name VARCHAR(255) NOT NULL,
    show_start_time DATETIME NOT NULL,
    show_end_time DATETIME NOT NULL,
    movie_id INT NOT NULL,
    screen_id INT NOT NULL,
    theatre_id INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id),
    FOREIGN KEY (screen_id) REFERENCES screens(screen_id),
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id)
);

-- Table: theatre_movies (many-to-many)
CREATE TABLE theatre_movies (
    theatre_id INT NOT NULL,
    movie_id INT NOT NULL,
    PRIMARY KEY (theatre_id, movie_id),
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

-- Table: tickets
CREATE TABLE tickets (
    ticket_ref_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    booking_ref VARCHAR(50) NOT NULL,
    no_of_seats INT NOT NULL,
    ticket_status BOOLEAN NOT NULL DEFAULT TRUE
);

-- Table: ticket_bookings
CREATE TABLE ticket_bookings (
    ticket_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    booking_date DATE NOT NULL,
    total_cost DECIMAL(10,2) NOT NULL,
    transaction_id INT NOT NULL,
    transaction_mode VARCHAR(50) NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,
    customer_id INT NOT NULL,
    show_id INT NOT NULL,
    ticket_ref_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (show_id) REFERENCES shows(show_id),
    FOREIGN KEY (ticket_ref_id) REFERENCES tickets(ticket_ref_id)
);

-- Table: ticket_seats (many-to-many)
CREATE TABLE ticket_seats (
    ticket_id INT NOT NULL,
    seat_id INT NOT NULL,
    PRIMARY KEY (ticket_id, seat_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_ref_id),
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);

-- Sample Data Inserts

-- Insert Admins with authentication
INSERT INTO admin (admin_name, admin_contact, email, password) VALUES
('Ravi Admin', '9999999999', 'ravi@admin.com', 'admin123'),
('Priya Manager', '8888888888', 'priya@admin.com', 'admin456');

-- Insert Customers with authentication
INSERT INTO customers (customer_name, address, email, mobile_number, password) VALUES
('Rajesh Kumar', '123 Jubilee Hills, Hyderabad', 'rajesh@email.com', '9876543210', 'password123'),
('Priya Sharma', '456 Banjara Hills, Hyderabad', 'priya@email.com', '9988776655', 'password456'),
('Amit Patel', '789 Madhapur, Hyderabad', 'amit@email.com', '9123456789', 'password789');

-- Insert Movies
INSERT INTO movies (movie_name, description, language, movie_genre, movie_hours) VALUES
('Pathaan', 'Shah Rukh Khan action thriller', 'Hindi', 'Action', 2),
('Avatar: The Way of Water', 'James Cameron epic sequel', 'English', 'Sci-Fi', 3),
('RRR', 'SS Rajamouli period action film', 'Telugu', 'Action Drama', 3);

-- Insert Theatres
INSERT INTO theatres (theatre_name, theatre_contact, theatre_address, admin_id) VALUES
('INOX GVK One Mall', '9876543210', 'Hyderabad', 1),
('PVR Forum Mall', '9988776655', 'Hyderabad', 2);

-- Insert Screens
INSERT INTO screens (screen_name, row_count, column_count, theatre_id) VALUES
('Screen 1', 15, 20, 1),
('Screen 2', 12, 18, 1),
('IMAX Screen', 18, 25, 2);

-- Insert sample seats (just a few examples)
INSERT INTO seats (seat_number, type, price, is_booked, is_blocked, screen_id) VALUES
('A1', 'Regular', 200.00, FALSE, FALSE, 1),
('A2', 'Regular', 200.00, FALSE, FALSE, 1),
('B1', 'Premium', 300.00, FALSE, FALSE, 1),
('C1', 'VIP', 500.00, FALSE, FALSE, 1);

-- Insert Shows
INSERT INTO shows (show_name, show_start_time, show_end_time, movie_id, screen_id, theatre_id) VALUES
('Morning Show', '2025-09-17 09:00:00', '2025-09-17 11:00:00', 1, 1, 1),
('Evening Show', '2025-09-17 19:00:00', '2025-09-17 22:00:00', 2, 2, 1),
('Night Show', '2025-09-17 21:30:00', '2025-09-17 23:30:00', 3, 3, 2);

-- Insert Theatre-Movies relationships
INSERT INTO theatre_movies (theatre_id, movie_id) VALUES
(1, 1), (1, 2), (2, 3);