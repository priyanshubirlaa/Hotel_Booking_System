# 🏨 Hotel Booking System -- Backend (Spring Boot)

A production-oriented backend REST API application that simulates a hotel booking platform.  
The system manages customers, hotels, rooms, and bookings using secure authentication, role-based authorization, and enterprise-grade backend practices.

---

## 📌 Project Overview

This project is a backend-only Hotel Booking System built using **Java and Spring Boot**.

### 🎯 Goals of the Project

- Build clean, structured, and secure REST APIs  
- Implement authentication & authorization using JWT  
- Apply backend architecture and enterprise practices  
- Ensure data consistency with transactions and locking  
- Design scalable and maintainable systems  

The system exposes APIs to manage:

- Customers  
- Hotels  
- Rooms  
- Bookings  

---

## 🔐 Security Implementation

### ✅ User Registration & Login (JWT Authentication)

- Secure login using JWT tokens
- Token-based authentication for protected APIs
- Centralized handling of invalid credentials and access denial

### ✅ Role-Based Access Control (RBAC)

- **ADMIN**
  - Manage hotels
  - Manage rooms
  - View all bookings

- **CUSTOMER**
  - Create bookings
  - View only their own bookings

### ✅ API Protection Rules

- Users can access only their own bookings
- Admin-only APIs are protected
- Unauthorized access returns structured JSON responses

---

## 🛠️ Technology Stack

- Java  
- Spring Boot  
- Spring Security (JWT)  
- Spring Data JPA  
- MySQL  
- Maven  
- Swagger (OpenAPI)  
- Postman  
- Git & GitHub  

---

## 🏗️ Architecture

The project follows proper layered architecture:

controller → service → repository → database

### Layers

- Controller Layer — Handles HTTP requests and responses  
- Service Layer — Business logic, validations, transactions  
- Repository Layer — Database interaction via JPA  
- DTO Layer — Clean request/response separation  
- Security Layer — JWT authentication & RBAC  
- Exception Layer — Global exception handling  

---

## 🗄️ Database Design & Constraints

### ✅ DB-Level Improvements

- Foreign key constraints
- Unique constraints
- Optimistic locking using `@Version`
- Proper indexes on:
  - status
  - customer_id
  - hotel_id
  - room_id
  - check-in & check-out dates

### ✅ Optimistic Locking

- Version field added to Booking entity
- Prevents concurrent modification issues

### ✅ Transaction Management

- Booking flow wrapped in `@Transactional`
- Ensures atomicity and consistency
- Prevents partial data persistence

---

## 📦 Booking Business Validations

✔ Prevent double booking for overlapping dates  
✔ Prevent booking in the past  
✔ Ensure check-in < check-out  
✔ Validate room belongs to selected hotel  
✔ Prevent concurrent booking conflicts  

---

## 🔎 Pagination, Sorting & Filtering

Implemented for:

- Hotels
- Rooms
- Bookings

Supports:

- Page number
- Page size
- Sorting by fields
- Direction (ASC/DESC)

---

## 🔍 Search APIs

### Hotels

- Search by city  
  `GET /api/hotels?city=Delhi&page=0&size=10`

### Rooms

- Filter by price range  
  `GET /api/hotels/{hotelId}/rooms?minPrice=1000&maxPrice=5000`

- Search by availability  
  `GET /api/hotels/{hotelId}/rooms?checkIn=2026-03-10&checkOut=2026-03-15`

### Bookings

- Filter by status  
- Filter by customer  
- Filter by hotel  
- Pagination & sorting supported  

---

## 🚀 How to Run

### Create Database

```sql
CREATE DATABASE hotel_booking_system;
```

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_system
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.open-in-view=false
```

### Run Application

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

## 📚 Swagger Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

## 📖 Available APIs

All APIs are under:

```
/api
```

### 👤 Authentication

- Register → POST `/api/auth/register`
- Login → POST `/api/auth/login`

### 🏨 Hotel Module

- Add Hotel (ADMIN) → POST `/api/hotels`
- List Hotels → GET `/api/hotels`
- Get Hotel → GET `/api/hotels/{id}`

### 🛏️ Room Module

- Add Room (ADMIN) → POST `/api/hotels/{hotelId}/rooms`
- Search Rooms → GET `/api/hotels/{hotelId}/rooms`
- Get Room → GET `/api/hotels/{hotelId}/rooms/{roomId}`

### 📖 Booking Module

- Create Booking (CUSTOMER) → POST `/api/bookings`
- Cancel Booking → PUT `/api/bookings/{id}/cancel`
- Get Booking → GET `/api/bookings/{id}`
- Search Bookings → GET `/api/bookings`

Allowed Status Values:

- CONFIRMED  
- CANCELLED  

---

## ✅ Key Features

✔ JWT Authentication  
✔ Role-Based Access Control  
✔ Secure API endpoints  
✔ Layered architecture  
✔ DTO separation  
✔ Pagination & sorting  
✔ Advanced search APIs  
✔ Booking availability validation  
✔ Optimistic locking  
✔ Transaction management  
✔ DB constraints & indexing  
✔ Global exception handling  
✔ Swagger documentation  

---

## 🔗 Repository

https://github.com/priyanshubirlaa/Hotel_Booking_System
