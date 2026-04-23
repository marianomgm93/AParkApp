# 🚗 AParkApp - Parking Management SaaS

AParkApp is a backend SaaS platform designed to manage parking lots, reservations, vehicle access, and billing processes.  
It simulates a real-world system integrating business logic, clean architecture, and scalable API design.

---

## 📌 Features

- Parking lot management
- Parking spot availability tracking
- Vehicle and driver association
- Reservation system
- Ticket-based entry/exit system
- Stay management (hourly, daily, monthly, annual)
- Dynamic pricing configuration
- Billing and invoicing system
- Search and availability endpoints

---

## 🧠 Domain Overview

The system models a real parking business:
Customer → Vehicle → Ticket → Stay → Bill

AParkApp solves a real-world urban problem: finding and managing parking
efficiently.

The system allows: - Users to search for available parking lots based on
location - Parking owners to manage their facilities - Real-time
tracking of parking availability - Reservations and ticket-based parking
management

------------------------------------------------------------------------

## 🏗 Architecture

- **Backend:** Java + Spring Boot  
- **Architecture:** Clean Architecture (Layered)  
- **Database:** Relational (MySQL / SQL Server)  
- **API:** RESTful  
- **Documentation:** OpenAPI (Swagger)

---

## 📦 Project Structure


com.aparkapp

-   auth/
-   config/
-   common/
    -   exception/
    -   utils/
-   features/
    -   parking/
    -   vehicle/
    -   reservation/
    -   ticket/
    -   stay/
    -   billing/
    -   user/


Layers: - Controller → HTTP - Service → Logic - Repository →
Persistence - Entity → Model - DTO → Transfer

------------------------------------------------------------------------

---

## 🗄 Database Design (DER)

Main entities:

- Owners
- Employees
- ParkingLots
- ParkingSpots
- Vehicles
- Drivers (M:N with Vehicles)
- Reservations
- Tickets
- Stays
- StayTypes
- VehicleTypes
- Prices
- Bills
- Customers
- BillingData

---

## 🔗 Key Relationships

- One ParkingLot → Many ParkingSpots
- One Vehicle → Many Tickets
- One Ticket → One Stay
- One Stay → One Bill
- Drivers ↔ Vehicles (Many-to-Many)
- Prices depend on:
  - VehicleType
  - StayType

---

## 🚀 API Endpoints

### 🔐 Auth
- `POST /auth/login`
- `POST /auth/register`

---

### 🅿️ Parking
- `GET /parking-lots`
- `POST /parking-lots`
- `GET /parking-lots/{id}/spots`
- `GET /parking-lots/{id}/availability`

---

### 🚗 Vehicles
- `POST /vehicles`
- `GET /vehicles/{id}`
- `GET /vehicles/{id}/tickets`

---

### 👤 Drivers
- `POST /drivers`
- `GET /drivers/{id}/vehicles`
- `POST /drivers/{id}/vehicles`

---

### 📅 Reservations
- `POST /reservations`
- `GET /reservations`
- `PUT /reservations/{id}/cancel`

---

### 🎫 Tickets
- `POST /tickets`
- `PUT /tickets/{id}/close`

---

### ⏱ Stays
- `GET /stays/{id}`
- `GET /stays/{id}/cost`

---

### 💰 Pricing
- `GET /prices`
- `POST /prices`

---

### 🧾 Billing
- `POST /bills`
- `GET /bills/{id}`

---

### 🔍 Search
- `GET /search?lat=&lng=&vehicleType=`

---

## 💡 Business Logic Highlights

- Hourly stays are paid at exit
- Daily/Monthly stays require upfront payment
- Pricing is dynamic and configurable
- Tickets represent entry/exit
- Stays represent billing logic

---

## 🧪 Example Flow

1. Vehicle enters → Ticket created  
2. Stay is initialized  
3. Vehicle exits → Ticket closed  
4. Cost is calculated  
5. Bill is generated  

---

## 🛠 Future Improvements

- JWT Authentication
- Payment Gateway Integration
- Real-time availability (WebSockets)
- Mobile app integration
- AI-based parking prediction

---
