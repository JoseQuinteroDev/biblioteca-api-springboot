# Biblioteca API (Spring Boot) 📚

API REST desarrollada con **Spring Boot** para gestionar un dominio sencillo de biblioteca con **Autores** y **Libros**.

Este proyecto forma parte de mi portfolio como **Junior Java Backend Developer**, con foco en arquitectura por capas, DTOs, validación, migraciones de base de datos y entorno reproducible en local con Docker.

---

## ✨ Funcionalidades

### Autores
- Crear autor
- Listar autores
- Obtener autor por ID
- Eliminar autor

### Libros
- Crear libro
- Listar libros
- Obtener libro por ID
- Eliminar libro

### Características técnicas
- Arquitectura por capas (`controller`, `service`, `repository`)
- DTOs para separar request/response
- Validación de entrada con Jakarta Validation
- Manejo de errores con códigos HTTP (`404`, `409`)
- Relación JPA/Hibernate (`ManyToOne`) entre `Libro` y `Autor`
- Versionado de esquema con **Flyway**
- Base de datos MySQL ejecutándose en **Docker**

---

## 🛠️ Stack tecnológico

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **Jakarta Validation**
- **MySQL 8** (Docker)
- **Flyway** (migraciones)
- **Maven**

---

## 🧱 Arquitectura del proyecto

El proyecto sigue una estructura backend por capas:

- **Controller** → expone endpoints REST
- **Service** → lógica de negocio y validaciones
- **Repository** → acceso a datos con Spring Data JPA
- **DTOs** → payloads de entrada/salida
- **Entities** → modelo JPA (`Autor`, `Libro`)

---

## 🗂️ Estructura del proyecto

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
