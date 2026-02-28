# Biblioteca API Spring Boot

## Project Description
This is a robust and scalable RESTful API for managing a library system using Spring Boot. The project facilitates library operations, allowing users to manage books, members, and borrowing transactions seamlessly.

## Features
- User authentication and authorization
- CRUD operations for books and members
- Borrowing and returning system
- Search functionality for books
- Admin dashboard for monitoring and managing the library

## Tech Stack
- **Backend:** Spring Boot
- **Database:** PostgreSQL
- **Documentation:** Swagger
- **Containerization:** Docker
- **Build Tool:** Maven

## Installation Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/JoseQuinteroDev/biblioteca-api-springboot.git
   cd biblioteca-api-springboot
   ```
2. Ensure you have Java 11+ and Maven installed.
3. Configure application properties for database connection in `src/main/resources/application.properties`.
4. Build the project:
   ```bash
   mvn clean install
   ```

## Usage
To run the application locally:
```bash
mvn spring-boot:run
```

## API Endpoints
| Method | Endpoint                | Description                        |
|--------|-------------------------|------------------------------------|
| GET    | /api/books              | Retrieve all books                 |
| POST   | /api/books              | Add a new book                     |
| PUT    | /api/books/{id}        | Update book details                |
| DELETE | /api/books/{id}        | Delete a book                      |
| GET    | /api/members            | Retrieve all members               |
| POST   | /api/members            | Add a new member                   |
| PUT    | /api/members/{id}      | Update member details              |
| DELETE | /api/members/{id}      | Delete a member                    |

## Docker Setup
1. Build the Docker image:
   ```bash
   docker build -t biblioteca-api .
   ```
2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 biblioteca-api
   ```

## Database Migrations
Database migrations are handled using Flyway. To apply migrations, ensure the database connection is properly set in the `application.properties` and run the application.

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/biblioteca/
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
├── pom.xml
├── Dockerfile
├── README.md
```

## Contributing Guidelines
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/MyFeature
   ```
3. Commit your changes:
   ```bash
   git commit -m 'Add some feature'
   ```
4. Push to the branch:
   ```bash
   git push origin feature/MyFeature
   ```
5. Open a Pull Request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.