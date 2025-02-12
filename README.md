# Seat Service

## Overview
The **Seat Service** is a microservice within the Movie Reservation System responsible for managing seat availability and reservations. It handles seat status updates and ensures users can book available seats for movie screenings.

## Features
- Retrieve available seats for a specific schedule.
- Update seat availability upon successful booking.
- Communicate with other services to ensure seat reservations are valid.

## Technologies Used
- **Spring Boot** – Core framework for building the microservice.
- **Spring Data JPA** – For interacting with the database.
- **MySQL** – Database for storing seat-related information.
- **Feign Client** – For inter-service communication.
- **Spring Cloud Eureka** – Service discovery and registration.
- **JWT Authentication** – Securing API endpoints.

## API Endpoints

### Seat Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/seats` | Retrieve all seats |
| `GET` | `/seats/{id}` | Retrieve specific seat |
| `GET` | `/seats/schedule/{scheduleId}` | Retrieve all seats for a specific schedule |
| `POST` | `/seats/multiple` | Retrieve many seats with specific ids |
| `POST` | `/seats/create` | Create seat |
| `POST` | `/seats/multiple/create` | Create multiple seats at once |
| `PUT` | `/seats/update` | Update seat availability |
| `PUT` | `/seats/update/multiple` | Update many seats at once |
| `PUT` | `/seats/availability/reset/{id}` | Reset availability for a specific seat |
| `PUT` | `/seats/availability/reset` | Reset availability of all seats for a specific schedule |

## Service Communication
- Communicates with **Ticket Service** to validate seat reservations.
- Communicates with **Reservation Service** to manage seat assignments.

## Installation & Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/ChristosDurro/movie-reservation-system-seat-service.git
   ```
2. Navigate to the project folder:
   ```bash
   cd movie-reservation-system-seat-service
   ```
3. Configure the `application.properties` file:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/seat_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # Further Eureka and other configurations you might use
   ```
4. Build and run the service:
   ```bash
   mvn spring-boot:run
   ```

---

This service is part of the **Movie Reservation System**, designed to showcase a microservices-based architecture with Spring Boot.

