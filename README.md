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
- Colección de pruebas con **Postman** (endpoints de autores y libros)

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
- **Postman** (testing manual de endpoints)

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
├── postman/
│   ├── BibliotecaAPI.postman_collection.json
│   └── Local.postman_environment.json   
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

---

## ✅ Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 17+** (recomendado para Spring Boot 3.x)
- **Docker Desktop**
- **Git** (opcional, para clonar)
- **Maven** o usar el **Maven Wrapper** (`mvnw` / `mvnw.cmd`)
- **Postman** (opcional, para probar endpoints manualmente)

---

## 🚀 Puesta en marcha (local)

### 1) Clonar el repositorio

```bash
git clone https://github.com/JoseQuinteroDev/biblioteca-api-springboot.git
cd biblioteca-api-springboot
```

### 2) Levantar MySQL con Docker

```bash
docker compose up -d
```

Esto levanta un contenedor MySQL con la configuración del proyecto:
- **Base de datos:** `biblioteca_db`
- **Puerto host:** `3307`
- **Puerto contenedor:** `3306`

### 3) Ejecutar la aplicación Spring Boot

#### En Windows (Maven Wrapper)
```bash
mvnw.cmd spring-boot:run
```

#### En Linux/macOS (Maven Wrapper)
```bash
./mvnw spring-boot:run
```

> También puedes ejecutarlo desde IntelliJ usando la clase `BibliotecaApplication`.

### 4) URL base de la API

```text
http://localhost:8080
```

---

## 🐳 Base de datos con Docker

El proyecto incluye un `docker-compose.yml` para levantar MySQL 8.

Configuración utilizada:
- **Imagen:** `mysql:8.0`
- **Contenedor:** `biblioteca-mysql`
- **Base de datos:** `biblioteca_db`
- **Mapeo de puertos:** `3307:3306`

> Se usa `3307` en el host para evitar conflictos si ya existe una instalación local de MySQL en `3306`.

---

## 🗃️ Migraciones con Flyway

El esquema de la base de datos se gestiona con **Flyway**.

Las migraciones están en:

```text
src/main/resources/db/migration/
```

### Migración actual
- `V1__create_tables.sql` → crea las tablas `autores` y `libros`

### Nota importante
La aplicación usa:
- `spring.flyway.enabled=true`
- `spring.jpa.hibernate.ddl-auto=validate`

Esto significa que **JPA valida** que las entidades coincidan con el esquema creado por Flyway.

> Recomendación: mantener alineados los tamaños/constraints entre entidades JPA y SQL de Flyway (por ejemplo, `titulo` e `isbn`).

---

## 🔌 Endpoints de la API

### Autores (`/autores`)
- `POST /autores` → crear autor
- `GET /autores` → listar autores
- `GET /autores/{id}` → obtener autor por ID
- `DELETE /autores/{id}` → eliminar autor por ID

### Libros (`/libros`)
- `POST /libros` → crear libro
- `GET /libros` → listar libros
- `GET /libros/{id}` → obtener libro por ID
- `DELETE /libros/{id}` → eliminar libro por ID

---

## 🧾 Ejemplos de uso (request / response)

### ✅ Crear autor
**POST** `/autores`

#### Body (request)
```json
{
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

#### Respuesta esperada (201 Created)
```json
{
  "id": 1,
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

---

### ✅ Listar autores
**GET** `/autores`

#### Respuesta esperada (200 OK)
```json
[
  {
    "id": 1,
    "nombre": "Gabriel García Márquez",
    "pais": "Colombia"
  }
]
```

---

### ✅ Crear libro
**POST** `/libros`

#### Body (request)
```json
{
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1
}
```

#### Respuesta esperada (201 Created)
```json
{
  "id": 1,
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1,
  "autorNombre": "Gabriel García Márquez"
}
```

---

### ✅ Listar libros
**GET** `/libros`

#### Respuesta esperada (200 OK)
```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "isbn": "9780307474728",
    "autorId": 1,
    "autorNombre": "Gabriel García Márquez"
  }
]
```

---

### ✅ Obtener libro por ID
**GET** `/libros/1`

#### Respuesta esperada (200 OK)
```json
{
  "id": 1,
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1,
  "autorNombre": "Gabriel García Márquez"
}
```

---

### ✅ Eliminar libro
**DELETE** `/libros/1`

#### Respuesta esperada
- `204 No Content`

---

## ⚠️ Manejo de errores

La API devuelve códigos HTTP adecuados según el caso:

- **400 Bad Request** → datos inválidos (si falla validación del DTO)
- **404 Not Found** → recurso no encontrado (autor/libro)
- **409 Conflict** → ISBN duplicado al crear un libro

### Casos típicos
- Crear un libro con `isbn` ya existente → `409 Conflict`
- Crear un libro con `autorId` inexistente → `404 Not Found`
- Eliminar un autor/libro inexistente → `404 Not Found`

---

## 🧪 Pruebas con Postman

Este repositorio incluye una colección de **Postman** para probar los endpoints de la API en local.

### Archivos incluidos
```text
postman/BibliotecaAPI.postman_collection.json
postman/Local.postman_environment.json   (opcional)
```

### Qué contiene la colección
- Requests para endpoints de **Autores**
- Requests para endpoints de **Libros**
- Bodies de ejemplo para `POST`
- (Opcional) tests básicos de estado HTTP (`200`, `201`, `204`)

### Cómo usar la colección
1. Levantar MySQL con Docker:
   ```bash
   docker compose up -d
   ```
2. Ejecutar la aplicación Spring Boot
3. Abrir Postman
4. Importar la colección (`BibliotecaAPI.postman_collection.json`)
5. (Opcional) Importar el environment local
6. Ejecutar requests contra:
   ```text
   http://localhost:8080
   ```

### Variables recomendadas (Postman)
Si usas environment, puedes definir:
- `baseUrl = http://localhost:8080`
- `autorId` (opcional)
- `libroId` (opcional)

Ejemplos de URL con variables:
```text
{{baseUrl}}/autores
{{baseUrl}}/autores/{{autorId}}
{{baseUrl}}/libros
{{baseUrl}}/libros/{{libroId}}
```

> Nota: la colección está pensada para ejecutarse en **local** (`localhost`) después de clonar y arrancar el proyecto.

---

## 📌 Estado actual del proyecto

- [x] CRUD base de Autores
- [x] CRUD base de Libros
- [x] DTOs (request/response)
- [x] Migración inicial con Flyway
- [x] MySQL dockerizado
- [x] Colección Postman para pruebas manuales
- [ ] Endpoints de actualización (`PUT` / `PATCH`)
- [ ] Manejador global de excepciones (`@RestControllerAdvice`)
- [ ] Tests unitarios (JUnit + Mockito)
- [ ] Swagger / OpenAPI
- [ ] Spring Security + JWT (básico)

---

## 🔮 Mejoras futuras (Roadmap)

Mejoras previstas para siguientes versiones:
- Añadir `PUT` / `PATCH` para autores y libros
- Añadir paginación y filtros
- Añadir `@RestControllerAdvice` para manejo global de errores
- Añadir tests unitarios e integración
- Añadir documentación Swagger / OpenAPI
- Añadir Spring Security + JWT (básico)
- Dockerizar aplicación + base de datos en un mismo entorno
- Automatizar pruebas de colección Postman con Newman (opcional)

---

## 👨‍💻 Autor

**José Antonio Quintero Cortés**  
Proyecto de portfolio (Junior Java Backend Developer)

GitHub: [@JoseQuinteroDev](https://github.com/JoseQuinteroDev)

---

## 📄 Licencia

Proyecto compartido con fines de aprendizaje y portfolio profesional.

(Si en el futuro lo deseas, se puede añadir una licencia como MIT.)
