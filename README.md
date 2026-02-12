# note-api

This project is a lightweight RESTful Notes Service built with Java 17 and Spring Boot. It was created as a refresher to revisit core Java concepts and modern Spring Boot development practices.

The service demonstrates:

- REST API design (POST, GET, list endpoints)
- Clean layered architecture (Controller → Service → Repository)
- Constructor-based dependency injection
- DTO separation (Request vs Response models)
- Bean validation (@Valid, @NotBlank, @Size)
- Global exception handling
- In-memory storage using ConcurrentHashMap
- Unit testing with JUnit and Mockito

The project was built primarily for:

- Refreshing Java and Spring fundamentals
- Practicing clean API design principles
- Reinforcing understanding of dependency injection and application structure
- Preparing for backend engineering interviews

Although the current implementation uses in-memory storage for simplicity, the repository layer is designed to be easily replaced with a database-backed implementation (e.g., PostgreSQL).

## Tech stack

- Java 17
- Spring Boot 3.5
- Maven Wrapper (`./mvnw`)
- In-memory repository (`ConcurrentHashMap`)

## Requirements

- JDK 17+
- `make` (optional, for Makefile commands)

## Run locally

### With Makefile

```bash
make run
```

### With Maven wrapper

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080` by default.

## Build and test

```bash
make test
make build
```

Or with Maven directly:

```bash
./mvnw test
./mvnw clean package -DskipTests
```

## API

Base path: `/notes`

### Create note

- Method: `POST`
- URL: `http://localhost:8080/notes`
- Body:

```json
{
  "title": "First note",
  "content": "Hello from note-api"
}
```

Example:

```bash
curl -X POST http://localhost:8080/notes \
  -H "Content-Type: application/json" \
  -d '{"title":"First note","content":"Hello from note-api"}'
```

### Get note by ID

- Method: `GET`
- URL: `http://localhost:8080/notes/{id}`

Example:

```bash
curl http://localhost:8080/notes/<NOTE_ID>
```

### Get all notes

- Method: `GET`
- URL: `http://localhost:8080/notes`

Example:

```bash
curl http://localhost:8080/notes
```

## Validation and errors

- `title` is required (`@NotBlank`) and max length is 100 (`@Size(max=100)`).
- Missing notes return `404`.
- Validation failures return `400` with field-level details.

## Actuator

`spring-boot-starter-actuator` is included. Default endpoint availability follows Spring Boot defaults unless additional actuator properties are configured.

## Makefile targets

```bash
make help
```

Available targets:

- `make deps` - Download dependencies
- `make build` - Compile and package without tests
- `make run` - Run the application
- `make test` - Run tests
- `make package` - Package application jar
- `make verify` - Run Maven verify lifecycle
- `make clean` - Clean build artifacts
