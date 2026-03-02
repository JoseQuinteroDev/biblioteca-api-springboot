# Biblioteca API (Spring Boot) 📚

Este es uno de mis proyectos de portfolio como **Junior Java Backend Developer**.

He desarrollado una **API REST con Spring Boot** para gestionar una biblioteca sencilla con dos entidades principales: **Autores** y **Libros**.  
Mi objetivo con este proyecto ha sido practicar una base backend sólida: **arquitectura por capas**, **DTOs**, **validación**, **JPA/Hibernate**, **migraciones con Flyway**, **MySQL en Docker**, **pruebas manuales con Postman** y ahora también **tests unitarios con JUnit + Mockito**.

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

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **Jakarta Validation**
- **MySQL 8** (en Docker)
- **Flyway** (migraciones)
- **Postman** (pruebas manuales de endpoints)
- **JUnit 5** (tests unitarios)
- **Mockito** (mocks de repositorios y verificación de interacciones)
- **Maven** (gestión de dependencias con `pom.xml`)

> Nota: actualmente yo ejecuto este proyecto desde **IntelliJ** (no desde terminal con `mvn`).

---

## 🧱 Arquitectura del proyecto (por capas)

He organizado el proyecto con una estructura backend clásica por capas:

- **Controller** → expone endpoints REST
- **Service** → lógica de negocio y validaciones
- **Repository** → acceso a datos con Spring Data JPA
- **DTOs** → separación entre request/response y entidades
- **Entities** → modelo JPA (`Autor`, `Libro`)

Esto me ha permitido practicar una forma de trabajo más cercana a proyectos reales y evitar acoplar directamente el modelo de base de datos con la API pública.

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
│   └── test/
│       └── java/com/josequintero/biblioteca/service/
│           ├── AutorServiceImplTest.java
│           └── LibroServiceImplTest.java
├── postman/
│   ├── Biblioteca API.postman_collection.json
│   └── Local.postman_environment.json
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

---

## ✅ Requisitos previos

Para probar el proyecto en local recomiendo tener:

- **Java 21 (LTS)** recomendado para desarrollo y tests (JUnit + Mockito)

  > También puede funcionar con Java 17+, pero para evitar problemas de compatibilidad con Mockito/Byte Buddy estoy ejecutándolo con **Java 21**.
- **Docker Desktop**
- **IntelliJ IDEA** (recomendado, que es como lo estoy ejecutando yo)
- **Postman** (opcional, pero recomendable para probar la API)

---

## 🚀 Cómo lo ejecuto en local (paso a paso)

### 1) Clonar el repositorio

```bash
git clone https://github.com/JoseQuinteroDev/biblioteca-api-springboot.git
cd biblioteca-api-springboot
```

### 2) Levantar MySQL con Docker

```bash
docker compose up -d
```

Esto levanta un contenedor MySQL con esta configuración:

- **Imagen:** `mysql:8.0`
- **Contenedor:** `biblioteca-mysql`
- **Base de datos:** `biblioteca_db`
- **Puerto host:** `3307`
- **Puerto contenedor:** `3306`

> Uso `3307` en el host para evitar conflicto si ya hay otro MySQL local usando `3306`.

### 3) Ejecutar la API desde IntelliJ

Abro el proyecto y ejecuto la clase:

- `BibliotecaApplication`

(Usando el botón **Run** ▶️ de IntelliJ)

### 4) URL base de la API

```text
http://localhost:8080
```

---

## 🐳 Base de datos (Docker + MySQL)

He dockerizado la base de datos para que el entorno sea fácil de reproducir y no dependa de una instalación local manual de MySQL.

El proyecto usa `docker-compose.yml` para levantar el servicio MySQL con las variables necesarias (`MYSQL_ROOT_PASSWORD`, `MYSQL_DATABASE`, etc.).

---

## 🗃️ Migraciones con Flyway

El esquema de la base de datos está versionado con **Flyway**.

Ruta de migraciones:

```text
src/main/resources/db/migration/
```

### Migración actual

- `V1__create_tables.sql` → crea las tablas:

  - `autores`
  - `libros`

### Configuración relevante

- `spring.flyway.enabled=true`
- `spring.jpa.hibernate.ddl-auto=validate`

Esto me permite:

- crear el esquema con Flyway
- y después validar con JPA/Hibernate que las entidades coinciden con la estructura de BD

---

## 🔌 Endpoints disponibles

## Autores (`/autores`)

- `POST /autores` → crear autor
- `GET /autores` → listar autores
- `GET /autores/{id}` → obtener autor por ID
- `DELETE /autores/{id}` → eliminar autor por ID

## Libros (`/libros`)

- `POST /libros` → crear libro
- `GET /libros` → listar libros
- `GET /libros/{id}` → obtener libro por ID
- `DELETE /libros/{id}` → eliminar libro por ID

---

## 🧾 Ejemplos reales de uso (request / response)

## ✅ Crear autor

**POST** `/autores`

### Body (request)

```json
{
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

### Respuesta esperada (201 Created)

```json
{
  "id": 1,
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

---

## ✅ Listar autores

**GET** `/autores`

### Respuesta esperada (200 OK)

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

## ✅ Obtener autor por ID

**GET** `/autores/1`

### Respuesta esperada (200 OK)

```json
{
  "id": 1,
  "nombre": "Gabriel García Márquez",
  "pais": "Colombia"
}
```

---

## ✅ Crear libro

**POST** `/libros`

### Body (request)

```json
{
  "titulo": "Cien años de soledad",
  "isbn": "9780307474728",
  "autorId": 1
}
```

### Respuesta esperada (201 Created)

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

## ✅ Listar libros

**GET** `/libros`

### Respuesta esperada (200 OK)

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

## ✅ Obtener libro por ID

**GET** `/libros/1`

### Respuesta esperada (200 OK)

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

## ✅ Eliminar libro

**DELETE** `/libros/1`

### Respuesta esperada

- `204 No Content`

---

## ⚠️ Manejo de errores (HTTP)

La API devuelve códigos HTTP coherentes según el caso:

- **400 Bad Request** → datos inválidos (si falla validación)
- **404 Not Found** → recurso no encontrado
- **409 Conflict** → conflicto de negocio (por ejemplo ISBN duplicado)

### Casos típicos que he contemplado

- Crear un libro con un `isbn` ya existente → `409 Conflict`
- Crear un libro con `autorId` inexistente → `404 Not Found`
- Eliminar un autor/libro inexistente → `404 Not Found`

---

## 🧪 Pruebas con Postman (incluidas en el repo)

He añadido una colección de **Postman** para poder probar la API de forma rápida en local.

### Archivos incluidos

```text
postman/Biblioteca API.postman_collection.json
postman/Local.postman_environment.json
```

### Qué incluye la colección

- Requests para **Autores**
  - crear
  - listar
  - obtener por ID
  - eliminar
- Requests para **Libros**
  - crear
  - listar
  - obtener por ID
  - eliminar
- Bodies de ejemplo para los endpoints `POST`
- Algunos tests básicos de respuesta (según request)

---

## ▶️ Cómo usar la colección de Postman (paso a paso)

### 1) Arrancar base de datos + API

Primero:

- `docker compose up -d`
- ejecutar `BibliotecaApplication` desde IntelliJ

### 2) Importar en Postman

Importar:

- `postman/Biblioteca API.postman_collection.json`
- `postman/Local.postman_environment.json`

### 3) Seleccionar environment `Local`

El environment está pensado para trabajar con:

```text
baseUrl = http://localhost:8080
```

### 4) Probar endpoints

La colección usa URLs con variable:

```text
{{baseUrl}}/autores
{{baseUrl}}/libros
```

---

## 💡 Flujo de prueba recomendado en Postman (el que yo suelo seguir)

Para que todo funcione sin errores de IDs, recomiendo este orden:

1. **POST - Crear autor**
2. **GET - Listar autores**
3. **GET - Obtener autor por ID**
4. **POST - Crear libro** (usando un `autorId` existente)
5. **GET - Listar libros**
6. **GET - Obtener libro por ID**
7. **DELETE - Eliminar libro**
8. **DELETE - Eliminar autor** *(si ya no tiene libros asociados / según estado de datos)*

---

## ⚠️ Nota sobre variables de Postman (importante)

Dependiendo de la versión de Postman, el valor de `baseUrl` puede aparecer vacío al importar el environment si se exportó como valor local.

Si pasa eso, simplemente configúralo manualmente en el environment `Local`:

```text
baseUrl = http://localhost:8080
```

---

## ✅ Tests unitarios (JUnit + Mockito)

Además de las pruebas manuales con Postman, he añadido **tests unitarios** para la capa `service` usando:

- **JUnit 5** → ejecución y aserciones (`@Test`, `assertEquals`, `assertThrows`, etc.)
- **Mockito** → mocks de repositorios (`@Mock`), inyección en services (`@InjectMocks`) y verificación de llamadas (`verify(...)`)
- **MockitoExtension** → integración con JUnit 5 (`@ExtendWith(MockitoExtension.class)`)

### Objetivo de estos tests

He querido validar la **lógica de negocio de la capa service** sin tocar base de datos real, mockeando `AutorRepository` y `LibroRepository`.

También he probado:

- **casos felices** (creación/listado correctos)
- **casos de error** (`404 Not Found`, `409 Conflict`)
- **verificación de flujo** (que no se llamen métodos posteriores si una validación falla)

---

### 🧪 Tests implementados en `AutorServiceImplTest`

#### `crear() -> guarda un autor y devuelve AutorResponse`

Valida que:

- se crea correctamente el autor a partir del request
- se llama a `autorRepository.save(...)`
- se devuelve un `AutorResponse` con los datos esperados
- se captura el argumento enviado al repositorio con `ArgumentCaptor<Autor>` para comprobar `nombre` y `pais`

#### `listar() -> devuelve la lista de autores mapeada a AutorResponse`

Valida que:

- `autorRepository.findAll()` devuelve entidades
- el service las mapea correctamente a `List<AutorResponse>`

#### `obtenerPorId() -> lanza 404 si el autor no existe`

Valida que:

- si `autorRepository.findById(id)` devuelve `Optional.empty()`
- el service lanza `ResponseStatusException` con **404 Not Found**

---

### 🧪 Tests implementados en `LibroServiceImplTest`

#### `listar() -> devuelve libros mapeados a LibroResponse`

Valida que:

- `libroRepository.findAll()` devuelve entidades `Libro`
- el service mapea correctamente a `List<LibroResponse>` incluyendo `autorId` y `autorNombre`

#### `obtenerPorId() -> lanza 404 si el libro no existe`

Valida que:

- si `libroRepository.findById(id)` devuelve `Optional.empty()`
- el service lanza `ResponseStatusException` con **404 Not Found**

#### `crear() -> guarda libro y devuelve LibroResponse`

Valida el **caso feliz** completo de creación:

1. comprueba que el ISBN **no existe** (`existsByIsbn = false`)
2. comprueba que el autor **sí existe** (`autorRepository.findById(...)`)
3. guarda el libro (`libroRepository.save(...)`)
4. devuelve `LibroResponse` correctamente mapeado

Además, verifica las **3 interacciones clave** del flujo:

- `libroRepository.existsByIsbn(...)`
- `autorRepository.findById(...)`
- `libroRepository.save(...)`

#### `crear() -> lanza 409 si el ISBN ya existe`

Valida la regla de negocio de ISBN único:

- si `libroRepository.existsByIsbn(...)` devuelve `true`
- el service lanza `ResponseStatusException` con **409 Conflict**
- y **no continúa** el flujo:

  - no busca autor (`autorRepository.findById(...)`)
  - no guarda libro (`libroRepository.save(...)`)

---

### ▶️ Cómo ejecuto los tests

#### Desde IntelliJ

- Click derecho sobre una clase de test (`AutorServiceImplTest` / `LibroServiceImplTest`)
- **Run ...**
- También puedo ejecutar todos los tests desde `src/test/java`

#### Desde Maven (opcional)

```bash
mvn test
```

> Actualmente ejecuto los tests con **JDK 21** para evitar problemas de compatibilidad con Mockito/Byte Buddy en versiones más nuevas de Java.

---

## 📌 Estado actual del proyecto

- [x] CRUD base de Autores
- [x] CRUD base de Libros
- [x] DTOs (request / response)
- [x] Arquitectura por capas
- [x] Migración inicial con Flyway
- [x] MySQL dockerizado
- [x] Colección Postman para pruebas manuales
- [x] Tests unitarios (JUnit + Mockito) en capa service (Autor y Libro)
- [ ] Endpoints de actualización (`PUT` / `PATCH`)
- [ ] Manejador global de excepciones (`@RestControllerAdvice`)
- [ ] Swagger / OpenAPI
- [ ] Spring Security + JWT (básico)

---

## 🔮 Roadmap / mejoras futuras

Quiero seguir mejorándolo con:

- `PUT` / `PATCH` para autores y libros
- paginación y filtros
- `@RestControllerAdvice` para centralizar errores
- ampliar cobertura de tests unitarios (más casos de error y validaciones)
- tests de controller con `MockMvc`
- tests de integración (`@SpringBootTest` / `@DataJpaTest`)
- Swagger / OpenAPI
- Spring Security + JWT (básico)
- Dockerizar también la aplicación (no solo MySQL)
- Automatizar pruebas de Postman con Newman (opcional)

---

## 👨‍💻 Autor

**José Antonio Quintero Cortés**  
Proyecto de portfolio (Junior Java Backend Developer)

GitHub: [@JoseQuinteroDev](https://github.com/JoseQuinteroDev)