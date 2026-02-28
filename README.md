# Biblioteca API (Spring Boot) 📚

REST API developed with **Spring Boot** to manage a simple library domain with **Authors** and **Books**.

This project is part of my backend portfolio as a **Junior Java Backend Developer**, focused on clean layered architecture, DTOs, validation, database migrations, and reproducible local setup using Docker.

---

## ✨ Features

### Authors
- Create author
- List authors
- Get author by ID
- Delete author

### Books
- Create book
- List books
- Get book by ID
- Delete book

### Technical features
- Layered architecture (`controller`, `service`, `repository`)
- DTOs for request/response separation
- Input validation with Jakarta Validation
- Error handling using proper HTTP status codes (`404`, `409`)
- JPA/Hibernate with entity relationships (`ManyToOne`)
- Database schema versioning with **Flyway**
- MySQL database running in **Docker**

---

## 🛠️ Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **Jakarta Validation**
- **MySQL 8** (Docker)
- **Flyway** (database migrations)
- **Maven**

---

## 🧱 Project Architecture

This project follows a layered backend structure:

- **Controller** → Exposes REST endpoints
- **Service** → Business logic and validations
- **Repository** → Data access with Spring Data JPA
- **DTOs** → Request/response payloads
- **Entities** → JPA domain model (`Autor`, `Libro`)

---

## 🗂️ Project Structure

```text
biblioteca-api-springboot/
├── src/
│   ├── main/
│   │   ├── java/com/josequintero/biblioteca/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   └── response/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── BibliotecaApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   │           └── V1__create_tables.sql
├── docker-compose.yml
├── pom.xml
└── README.md
