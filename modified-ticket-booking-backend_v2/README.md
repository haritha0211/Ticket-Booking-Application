# Modified Movie Ticket Booking System Backend

## Overview
This is a Spring Boot backend application for a movie ticket booking system **without a separate User entity**. Instead, Admin and Customer entities have their own authentication mechanisms.

## Key Changes Made
- **Removed User entity** completely
- **Admin table** now includes email and password for authentication
- **Customer table** now includes email and password for authentication (no user_id foreign key)
- Updated all related services, controllers, and DTOs
- Separate login endpoints for Admin and Customer
- Simplified database schema

## Database Schema
- `admin` - Admin details with authentication
- `customers` - Customer details with authentication  
- `movies` - Movie information
- `theatres` - Theatre information (linked to admin)
- `screens` - Screen details for theatres
- `seats` - Seat information for screens
- `shows` - Show timings and details
- `ticket_bookings` - Booking records
- `tickets` - Ticket information
- `ticket_seats` - Many-to-many relationship between tickets and seats
- `theatre_movies` - Many-to-many relationship between theatres and movies

## Technology Stack
- Spring Boot 3.1.0
- Spring Data JPA
- MySQL 8.0
- Java 17
- Maven

## Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Database Setup
1. Create MySQL database:
   ```sql
   CREATE DATABASE booking_db;
   ```

2. Run the provided `database-schema.sql` file to create tables and insert sample data.

### Application Setup
1. Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

2. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

## API Endpoints

### Admin Authentication
- `POST /api/admin/signin?email=<email>&password=<password>` - Admin login

### Customer Authentication  
- `POST /api/customers/signin?email=<email>&password=<password>` - Customer login
- `POST /api/customers` - Customer registration

### Movies
- `GET /api/movies` - Get all movies
- `GET /api/movies/{id}` - Get movie by ID
- `POST /api/movies` - Create movie (Admin)
- `PUT /api/movies/{id}` - Update movie (Admin)
- `DELETE /api/movies/{id}` - Delete movie (Admin)

### Shows
- `GET /api/shows` - Get all shows
- `GET /api/shows/{id}` - Get show by ID
- `GET /api/shows/movie/{movieId}` - Get shows by movie
- `POST /api/shows` - Create show (Admin)

### Seats
- `GET /api/seats/available/{screenId}` - Get available seats for screen
- `GET /api/seats/screen/{screenId}` - Get all seats for screen
- `POST /api/seats/book` - Book seats

### Bookings
- `GET /api/bookings` - Get all bookings
- `GET /api/bookings/{id}` - Get booking by ID
- `GET /api/bookings/{id}/cost` - Get booking cost
- `GET /api/bookings/customer/{customerId}` - Get customer bookings
- `POST /api/bookings` - Create booking
- `DELETE /api/bookings/{id}` - Cancel booking

## Sample Login Credentials

### Admin
- Email: `ravi@admin.com`
- Password: `admin123`

### Customer  
- Email: `rajesh@email.com`
- Password: `password123`

## Key Features
- **Separate authentication** for Admin and Customer
- **No user management complexity** - direct login for each entity type
- **Clean separation** of Admin and Customer concerns
- **Full booking workflow** - from movie selection to seat booking
- **Exception handling** with proper error responses
- **CORS enabled** for frontend integration

## Frontend Integration
The backend is designed to work with the provided frontend code. Key integration points:
- Customer registration creates customer with password
- Customer login validates email/password directly  
- Admin login validates admin email/password
- All booking operations use customer ID directly

## Notes
- Passwords are stored in plain text (for development only)
- In production, implement password hashing
- Add input validation and security measures
- Consider adding JWT token authentication for session management

This modified backend eliminates the complexity of user-customer relationships while maintaining all core functionality.