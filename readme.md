# Spring Boot File Handling Application

## Overview

This is a simple **Spring Boot Application** designed to demonstrate basic file handling and RESTful user management. The project allows users to upload files, manage user data, and test the application with pre-written unit tests.

---

## Project Structure

├── HELP.md                        # Documentation links
├── mvnw, mvnw.cmd                 # Maven wrapper scripts
├── pom.xml                        # Maven project descriptor file
├── src
│   ├── main
│   │   ├── java/com/example/demo  # Java source files
│   │   │   ├── DemoApplication.java   # Main entry point
│   │   │   ├── FileHandler.java       # File handling utility
│   │   │   ├── ServiceComponent.java  # Service component (if used)
│   │   │   ├── User.java              # User data model
│   │   │   └── UserController.java    # REST controller for user operations
│   │   └── resources
│   │       └── application.properties # Application configurations
│   └── test/java/com/example/demo
│       └── DemoApplicationTests.java  # Unit tests
├── target                          # Compiled classes and build outputs

---

## Key Features

### 1. **User Management**
- **Create**: Add a new user with details like ID, name, and location.
- **Retrieve**: Fetch details of all users or a specific user by ID.
- **Update**: Modify existing user details.
- **Delete**: Remove a user by ID.

### 2. **File Handling**
- Supports uploading, deleting, and verifying the existence of files in a directory.

---

## REST API Endpoints

### User Operations (`/users`)

| HTTP Method | Endpoint       | Description                 |
|-------------|----------------|-----------------------------|
| POST        | `/users`       | Create a new user           |
| GET         | `/users`       | Retrieve all users          |
| GET         | `/users/{id}`  | Get user details by ID      |
| PUT         | `/users/{id}`  | Update an existing user     |
| DELETE      | `/users/{id}`  | Delete a user by ID         |

### File Handling (Future Implementation)
Add methods to upload, delete, and retrieve file details.

---

## Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.8+**
# Build and Run the Project

### Build the project:
```bash
./mvnw clean install
```
Run the application:
bash
Copy code
./mvnw spring-boot:run
Access the REST API:
http://localhost:8080

Configuration
application.properties
Defines the application name:

properties
Copy code
spring.application.name=demo
Extendable for database, logging, or other properties.

Testing
Run unit tests:
bash
Copy code
./mvnw test
Tests are defined in:
DemoApplicationTests.java
Tools and Technologies
Java 17
Spring Boot 3.3.2
Maven
JUnit 5
References
Spring Boot Documentation
Building RESTful APIs with Spring
Author
Muskan Gupta
