# RevConnect

RevConnect is a Spring Boot backend for a social media platform with authentication, posting, messaging, and real time features.

## Features
- User auth and JWT based security
- Posts, stories, media upload
- Connections and interactions
- Messaging and notifications (WebSocket)
- Search, hashtags, bookmarks
- Admin and analytics endpoints
- Email support (SMTP)

## Tech Stack
- Java 17, Spring Boot 3.2.x
- Spring Web, Data JPA, Security, Validation, AOP
- MySQL
- JWT (jjwt)
- Springdoc OpenAPI

## Prerequisites
- JDK 17
- Maven (or the Maven wrapper in this repo)
- MySQL running locally

## Configuration
Edit application properties and set your values:
- src/main/resources/application.properties

Do not commit real passwords or secrets.

Recommended keys:
- server.port
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password
- jwt.secret
- jwt.expiration
- spring.mail.host
- spring.mail.port
- spring.mail.username
- spring.mail.password

## Run Locally
```bash
./mvnw spring-boot:run
```

App runs on http://localhost:8080 by default.

## API Docs
Swagger UI is available at:
http://localhost:8080/swagger-ui.html

## Tests
```bash
./mvnw test
```

## Build
```bash
./mvnw clean package
```

## Project Structure
```
src/main/java/org/revature/revconnect
  controller/
  service/
  repository/
  model/
  dto/
  security/
src/main/resources
```
