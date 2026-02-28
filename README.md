# Biblioteca API (Autores + Libros)

API REST desarrollada con Spring Boot para gestionar autores y libros.

## 🚀 Tecnologías
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- MySQL (Docker)
- Flyway
- Maven

## 📌 Funcionalidades
- Crear, listar, obtener y eliminar autores
- Crear, listar, obtener y eliminar libros
- Relación `Libro -> Autor` (ManyToOne)
- DTOs para request/response
- Validación de datos
- Manejo de errores HTTP (`404`, `409`)
- Migraciones con Flyway
- Base de datos MySQL en Docker

## 🐳 Base de datos con Docker
Levantar MySQL:
```bash
docker compose up -d
