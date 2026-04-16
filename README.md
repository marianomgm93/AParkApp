# AParkApp 🚗

**Final project for my Programming degree (UTN)**

AParkApp is a SaaS (Software as a Service) platform designed to manage
parking facilities and help users find available parking spots in real
time.

------------------------------------------------------------------------

## 🧩 Overview

AParkApp solves a real-world urban problem: finding and managing parking
efficiently.

The system allows: - Users to search for available parking lots based on
location - Parking owners to manage their facilities - Real-time
tracking of parking availability - Reservations and ticket-based parking
management

------------------------------------------------------------------------

## 🏗️ Architecture

com.aparkapp

-   auth/
-   config/
-   common/
-   features/
    -   user/
    -   parkinglot/
    -   parkingspot/
    -   reservation/
    -   ticket/
    -   stay/

Layers: - Controller → HTTP - Service → Logic - Repository →
Persistence - Entity → Model - DTO → Transfer

------------------------------------------------------------------------

## 📡 API Endpoints

### Auth

POST /auth/register\
POST /auth/login

### Users

GET /users/{id}\
GET /users

### ParkingLot

GET /parking-lots\
POST /parking-lots\
PUT /parking-lots/{id}\
DELETE /parking-lots/{id}

### ParkingSpot

GET /parking-spots/available\
POST /parking-spots

### Ticket

POST /tickets\
PUT /tickets/{id}/close

### Reservation

POST /reservations\
PUT /reservations/{id}/cancel

### Search

GET /search?lat={lat}&lng={lng}&type={vehicleType}

------------------------------------------------------------------------

## 🔐 Security

-   JWT Authentication\
-   Role-based access

------------------------------------------------------------------------

## 🛠️ Tech Stack

-   Java\
-   Spring Boot\
-   Spring Security\
-   JPA / Hibernate\
-   MySQL

------------------------------------------------------------------------

