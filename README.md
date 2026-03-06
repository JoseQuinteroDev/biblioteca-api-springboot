# Biblioteca API (Spring Boot) 📚

Proyecto de portfolio como **Junior Java Backend Developer**.

He desarrollado una **API REST con Spring Boot** para gestionar una biblioteca sencilla con dos entidades principales: **Autores** y **Libros**.

Con este proyecto he querido practicar una base backend sólida y realista, trabajando con:

- Arquitectura por capas
- DTOs (request / response)
- Validación
- JPA / Hibernate
- Migraciones con Flyway
- MySQL en Docker
- Dockerización de la aplicación
- Spring Security (HTTP Basic + roles)
- Pruebas manuales con Postman
- Tests unitarios con JUnit + Mockito

> JWT y microservicios quedan como siguientes pasos de evolución del proyecto.

---

## ✨ Qué hace la API

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

---

## 🛠️ Stack tecnológico

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- Jakarta Validation
- Spring Security (HTTP Basic + autorización por roles)
- MySQL 8 (Docker)
- Flyway (migraciones)
- Docker / Docker Compose (BD + app)
- Postman (pruebas manuales)
- JUnit 5
- Mockito
- Maven (incluye wrapper: `.mvn`, `mvnw`, `mvnw.cmd`)

---

## 🧱 Arquitectura del proyecto (por capas)

He organizado el proyecto con una estructura backend clásica por capas:

- **Controller** → expone endpoints REST
- **Service** → lógica de negocio y validaciones
- **Repository** → acceso a datos con Spring Data JPA
- **DTOs** → separación entre request/response y entidades
- **Entities / Model** → modelo JPA (Autor, Libro)
- **Config / Security** → configuración de seguridad (Spring Security)

---

## 🔐 Seguridad (Spring Security)

He añadido una primera capa de seguridad usando Spring Security con:

- HTTP Basic Auth
- Usuarios en memoria (para aprendizaje y pruebas)
- Roles `USER` y `ADMIN`
- Autorización por endpoint y método HTTP

### Política de acceso actual

- `GET /autores`, `GET /libros` → público (`permitAll`)
- `GET /autores/{id}`, `GET /libros/{id}` → `USER` o `ADMIN`
- `POST /autores`, `POST /libros` → solo `ADMIN`
- `DELETE /autores/{id}`, `DELETE /libros/{id}` → solo `ADMIN`

> En una siguiente fase quiero sustituir el acceso con usuarios en memoria por JWT + autenticación real con usuario persistido.

### Usuarios de prueba actuales (entorno de desarrollo)

- **USER**
  - usuario: `jose`
  - contraseña: `1234`

- **ADMIN**
  - usuario: `admin`
  - contraseña: `admin123`

---

## 🗂️ Estructura del proyecto

```text
biblioteca-api-springboot/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/josequintero/biblioteca/
│   │   │   ├── config/
│   │   │   │   └── security/
│   │   │   │       ├── SpringSecurityConfig.java
│   │   │   │       └── SecurityUsersConfig.java
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
│   └── test/
│       └── java/com/josequintero/biblioteca/service/
│           ├── AutorServiceImplTest.java
│           └── LibroServiceImplTest.java
├── postman/
│   ├── Biblioteca API.postman_collection.json
│   └── Local.postman_environment.json
├── Dockerfile
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── .gitignore
```

---

## ✅ Requisitos previos

- Java 21 (LTS)
- Docker Desktop
- Postman (opcional, recomendado)
- IntelliJ IDEA (opcional, recomendado)

> El proyecto incluye **Maven Wrapper**, así que no hace falta tener Maven instalado globalmente.

---

## 🚀 Formas de ejecución

### Opción A — Desarrollo cómodo (recomendada)
- MySQL en Docker
- API desde IntelliJ o con Maven Wrapper en local

### Opción B — Entorno dockerizado completo
- MySQL + API en Docker Compose

---

## ▶️ Opción A: Ejecutar en local (app local + MySQL Docker)

### 1) Clonar el repositorio

```bash
git clone https://github.com/JoseQuinteroDev/biblioteca-api-springboot.git
cd biblioteca-api-springboot
```

### 2) Levantar MySQL con Docker

```bash
docker compose up -d mysql
```

### 3) Ejecutar la API en local

- IntelliJ (recomendado): ejecutar `BibliotecaApplication`

- Maven Wrapper (Windows):
```bash
.\mvnw.cmd spring-boot:run
```

- Maven Wrapper (Linux/macOS):
```bash
./mvnw spring-boot:run
```

### 4) URL base

```text
http://localhost:8080
```

---

## 🐳 Opción B: Ejecutar todo dockerizado (app + MySQL)

### 1) Construir y levantar todo

```bash
docker compose up --build
```

### 2) URL base

```text
http://localhost:8080
```

### 3) Parar el entorno

```bash
docker compose down
```

### 4) Parar y borrar también el volumen de MySQL (⚠️ borra datos)

```bash
docker compose down -v
```

---

## 🐳 Notas sobre Docker (importante)

- Cuando la app está dockerizada, si cambias código Java normalmente tienes que reconstruir la imagen para que Docker use el nuevo `.jar`:

```bash
docker compose up --build
```

- Para desarrollo diario suele ser más cómodo:
  - app local
  - MySQL en Docker

---

## 🗃️ Base de datos (Docker + MySQL)

La base de datos se levanta con Docker Compose y persiste datos mediante un volumen.

- `docker compose down` → los datos se mantienen
- `docker compose down -v` → se borran los datos (se elimina el volumen)

---

## 🗃️ Migraciones con Flyway

Ruta:
```text
src/main/resources/db/migration/
```

Migración actual:
- `V1__create_tables.sql` → crea autores y libros

Config típica:
- `spring.flyway.enabled=true`
- `spring.jpa.hibernate.ddl-auto=validate` (o `none`, según tu configuración)

---

## 🔌 Endpoints disponibles

### Autores (`/autores`)
- `POST /autores` → crear autor (**ADMIN**)
- `GET /autores` → listar autores (**público**)
- `GET /autores/{id}` → obtener autor (**USER/ADMIN**)
- `DELETE /autores/{id}` → eliminar autor (**ADMIN**)

### Libros (`/libros`)
- `POST /libros` → crear libro (**ADMIN**)
- `GET /libros` → listar libros (**público**)
- `GET /libros/{id}` → obtener libro (**USER/ADMIN**)
- `DELETE /libros/{id}` → eliminar libro (**ADMIN**)

---

## 🧾 Ejemplos reales de uso (request / response)

Para endpoints protegidos, usa Basic Auth:
- `admin / admin123` (**ADMIN**)
- `jose / 1234` (**USER**)

### ✅ Crear autor (ADMIN)

`POST /autores`  
Auth: Basic Auth → `admin / admin123`

Body:
```json
{
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

Respuesta esperada (201):
```json
{
  "id": 1,
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

### ✅ Crear libro (ADMIN)

`POST /libros`  
Auth: Basic Auth → `admin / admin123`

Body:
```json
{
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1
}
```

Respuesta esperada (201):
```json
{
  "id": 1,
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1
}
```

### ✅ Listar autores

`GET /autores`

Respuesta esperada (200):
```json
[
  {
    "id": 1,
    "nombre": "Gabriel García Márquez",
    "pais": "Colombia"
  }
]
```

### ✅ Listar libros

`GET /libros`

Respuesta esperada (200):
```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "isbn": "9780307474728",
    "autorId": 1
  }
]
```

---

## ⚠️ Manejo de errores (HTTP)

- `400 Bad Request` → datos inválidos (validación)
- `404 Not Found` → recurso no encontrado
- `409 Conflict` → conflicto de negocio (ej. ISBN duplicado)

Ejemplo de conflicto por ISBN duplicado:
```json
{
  "timestamp": "2026-03-06T12:00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Ya existe un libro con ese ISBN",
  "path": "/libros"
}
```

---

## 🧪 Tests unitarios (JUnit + Mockito)

Actualmente el proyecto incluye tests unitarios enfocados en la capa **service**, usando:

- **JUnit 5** para estructurar y ejecutar las pruebas
- **Mockito** para simular dependencias como repositorios

### Qué se prueba

- creación correcta de autores y libros
- validaciones de negocio
- errores cuando un recurso no existe
- errores cuando hay conflictos, como un ISBN duplicado

### Ejecutar tests

- IntelliJ: click derecho sobre una clase de test → **Run**

- Maven Wrapper (Windows):
```bash
.\mvnw.cmd test
```

- Maven Wrapper (Linux/macOS):
```bash
./mvnw test
```

---

## 🧪 Postman (colección incluida)

Archivos incluidos:

```text
postman/Biblioteca API.postman_collection.json
postman/Local.postman_environment.json
```

Si al importar el environment `Local` el `baseUrl` aparece vacío:

```text
baseUrl = http://localhost:8080
```

La colección permite probar fácilmente los endpoints en local con diferentes requests ya preparadas.

---

## 📌 Estado actual del proyecto

- [x] CRUD base de Autores
- [x] CRUD base de Libros
- [x] DTOs (request / response)
- [x] Arquitectura por capas
- [x] Migración inicial con Flyway
- [x] MySQL dockerizado
- [x] Aplicación dockerizada (Dockerfile + docker-compose)
- [x] Spring Security (HTTP Basic + roles)
- [x] Colección Postman para pruebas manuales
- [x] Tests unitarios (JUnit + Mockito) en capa service
- [ ] Endpoints de actualización (PUT / PATCH)
- [ ] Manejador global de excepciones (`@RestControllerAdvice`)
- [ ] Swagger / OpenAPI
- [ ] JWT (siguiente paso)
- [ ] Microservicios (futuro)

---

## 🔮 Roadmap / mejoras futuras

Estas son algunas mejoras que quiero seguir incorporando al proyecto para llevarlo a un nivel más sólido como portfolio backend:

- PUT / PATCH para autores y libros
- paginación y filtros
- `@RestControllerAdvice` para centralizar errores
- ampliar cobertura de tests unitarios
- tests de controller con MockMvc
- tests de integración (`@SpringBootTest`, `@DataJpaTest`)
- Swagger / OpenAPI
- Spring Security + JWT
- persistencia real de usuarios y roles
- microservicios con Spring Cloud
- service discovery, gateway y circuit breaker

---

## 👨‍💻 Autor

**José Antonio Quintero Cortés**  
Proyecto de portfolio como **Junior Java Backend Developer**

GitHub: **@JoseQuinteroDev**