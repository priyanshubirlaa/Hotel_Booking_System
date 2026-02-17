# 🏨 Hotel Booking System -- Backend (Spring Boot)

A backend REST API application that simulates a simplified hotel booking
platform.\
The system manages customers, hotels, rooms, and bookings using proper
layered architecture and enterprise-style design principles.

------------------------------------------------------------------------

## 📌 Project Overview

This project is a backend-only Hotel Booking / Order Management System
built using **Java and Spring Boot**.

### 🎯 Goals of the Project

-   Build clean and structured REST APIs\
-   Understand backend architecture\
-   Apply Java & Spring Boot concepts practically\
-   Design scalable and maintainable systems\
-   Follow enterprise development practices

The system exposes APIs to manage:

-   Customers\
-   Hotels\
-   Rooms\
-   Bookings

------------------------------------------------------------------------

## 🛠️ Technology Stack

-   Java (OOP, Core Concepts)\
-   Spring Boot\
-   Spring Data JPA\
-   MySQL (Primary Database)\
-   Maven\
-   Swagger (OpenAPI)\
-   Postman (API Testing)\
-   Git & GitHub

------------------------------------------------------------------------

## 🏗️ Architecture

The project follows proper layered architecture:

controller → service → repository → database

### Layers

-   Controller Layer -- Handles HTTP requests and responses\
-   Service Layer -- Business logic and validations\
-   Repository Layer -- Database interaction via JPA\
-   DTO Layer -- Clean API contract (Request & Response separation)\
-   Exception Layer -- Global exception handling

------------------------------------------------------------------------

## 🗄️ Database

This project uses **MySQL**.

### Create Database

CREATE DATABASE hotel_booking_system;

### application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_system\
spring.datasource.username=root\
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update\
spring.jpa.show-sql=true\
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

------------------------------------------------------------------------

## 🚀 How to Run

### Using Maven Wrapper

./mvnw clean install\
./mvnw spring-boot:run

Or:

./mvnw clean package\
java -jar target/\*.jar

Application runs on:

http://localhost:8080

------------------------------------------------------------------------

## 📚 Swagger API Documentation

Swagger UI:

http://localhost:8080/swagger-ui.html

Use Swagger to test all APIs interactively.

------------------------------------------------------------------------

# 📦 Modules & APIs

All APIs are available under:

/api

------------------------------------------------------------------------

## 👤 Customer Module

-   Create Customer → POST /api/customers\
-   Get Customer by ID → GET /api/customers/{id}\
-   Update Customer → PUT /api/customers/{id}

------------------------------------------------------------------------

## 🏨 Hotel Module

-   Add Hotel → POST /api/hotels\
-   List Hotels → GET /api/hotels?page=0&size=10&location=City\
-   Get Hotel by ID → GET /api/hotels/{id}

------------------------------------------------------------------------

## 🛏️ Room Module

-   Add Room → POST /api/hotels/{hotelId}/rooms\
-   List Rooms → GET /api/hotels/{hotelId}/rooms?page=0&size=10\
-   Get Room Details → GET /api/hotels/{hotelId}/rooms/{roomId}

------------------------------------------------------------------------

## 📖 Booking Module

-   Create Booking → POST /api/bookings\
-   Get Booking → GET /api/bookings/{id}\
-   Cancel Booking → PUT /api/bookings/{id}/cancel\
-   List Bookings by Status → GET
    /api/bookings/status/{status}?page=0&size=10

Allowed Status Values:

-   CONFIRMED\
-   CANCELLED

------------------------------------------------------------------------

## ✅ Key Features

✔ Layered architecture\
✔ DTO separation\
✔ MySQL integration\
✔ Pagination\
✔ Global exception handling\
✔ Custom exceptions\
✔ Booking availability validation\
✔ Swagger documentation\
✔ Proper HTTP status codes\
✔ Postman tested APIs

------------------------------------------------------------------------

## 🔗 Repository

https://github.com/priyanshubirlaa/Hotel_Booking_System
