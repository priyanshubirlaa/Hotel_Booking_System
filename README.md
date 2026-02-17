🏨 Hotel Booking System – Backend (Spring Boot)

A backend REST API application that simulates a simplified hotel booking platform.
The system manages customers, hotels, rooms, and bookings with proper layered architecture and enterprise-style design.

📌 Project Overview

This project is a backend-only Hotel Booking / Order Management System built using Java and Spring Boot.

The goal of this project is to:

Build clean and structured REST APIs

Understand backend architecture

Apply Java & Spring Boot concepts practically

Design scalable and maintainable systems

Follow enterprise development practices

The system exposes APIs to manage:

Customers

Hotels

Rooms

Bookings

🛠️ Technology Stack

Java (OOP, Core Concepts)

Spring Boot

Spring Data JPA

MySQL (Primary Database)

Maven

Swagger (OpenAPI)

Postman (API Testing)

Git & GitHub

🏗️ Architecture

The project follows proper layered architecture:

controller  →  service  →  repository  →  database

Layers:

Controller Layer – Handles HTTP requests and responses

Service Layer – Business logic and validations

Repository Layer – Database interaction via JPA

DTO Layer – Clean API contract (Request & Response separation)

Exception Layer – Global exception handling

🗄️ Database

This project uses MySQL.

Create Database
CREATE DATABASE hotel_booking_system;

application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_system
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

🚀 How to Run
Using Maven Wrapper
./mvnw clean install
./mvnw spring-boot:run


Or:

./mvnw clean package
java -jar target/*.jar


Application runs on:

http://localhost:8080

📚 Swagger API Documentation

Swagger UI:

http://localhost:8080/swagger-ui.html


Use Swagger to test all APIs interactively.

📦 Modules & APIs

All APIs are available under:

/api

👤 Customer Module

Create Customer
POST /api/customers

Get Customer by ID
GET /api/customers/{id}

Update Customer
PUT /api/customers/{id}

🏨 Hotel Module

Add Hotel
POST /api/hotels

List Hotels (with pagination & optional location filter)
GET /api/hotels?page=0&size=10&location=City

Get Hotel by ID
GET /api/hotels/{id}

🛏️ Room Module

Add Room to Hotel
POST /api/hotels/{hotelId}/rooms

List Available Rooms (Paginated)
GET /api/hotels/{hotelId}/rooms?page=0&size=10

Get Room Details
GET /api/hotels/{hotelId}/rooms/{roomId}

📖 Booking Module

Create Booking
POST /api/bookings

Get Booking by ID
GET /api/bookings/{id}

Cancel Booking
PUT /api/bookings/{id}/cancel

List Bookings by Status (Paginated)
GET /api/bookings/status/{status}?page=0&size=10

Allowed status values:

CONFIRMED

CANCELLED

✅ Key Features Implemented

✔ Proper layered architecture
✔ Clean Request & Response DTO separation
✔ MySQL integration
✔ Pagination support
✔ Global exception handling
✔ Custom exceptions (BusinessException, ResourceNotFoundException)
✔ Booking availability validation
✔ Swagger documentation
✔ Meaningful HTTP status codes
✔ Postman tested APIs

⚠️ Exception Handling

The project includes:

ResourceNotFoundException → 404 errors

BusinessException → Invalid operations (e.g., invalid booking status)

Global exception handler using @RestControllerAdvice

Validation errors return proper 4xx responses.

🔄 Booking Logic

Booking creation ensures:

Customer exists

Hotel exists

Room exists

Room belongs to selected hotel

Room is available

Room becomes unavailable after booking

Booking status set to CONFIRMED

Cancelled booking restores room availability

All handled transactionally.

📈 Project Phases
Phase 1 – Setup

Spring Boot setup

MySQL integration

Basic CRUD

Phase 2 – Core Functionality

Customer module

Hotel module

Room module

Booking module

API testing

Phase 3 – Enhancements

Pagination

Exception handling improvements

Swagger integration

Business validations

🎯 Outcome

By completing this project, you gain hands-on experience with:

Building REST APIs using Spring Boot

Designing scalable backend systems

Using JPA with relational databases

Handling real-world booking logic

Writing clean, maintainable code

Using Git professionally

🔗 Repository

GitHub Repository:

👉 https://github.com/priyanshubirlaa/Hotel_Booking_System
