# Spring Boot Custom Authentication Filter Example

This Spring Boot application demonstrates how to implement a custom authentication filter to intercept incoming HTTP requests and handle authentication based on a custom header.

## Requirements

- Java 8 or higher
- Maven or Gradle

## Dependencies

This project uses the following dependencies:

- Spring Boot 3.2.4
- Jackson for JSON serialization/deserialization
- javax.servlet for HTTP servlet support

## Setup

1. Clone the repository:

```bash
git clone "https://github.com/PoojaPadir/PinggyAssignment.git"

2.Navigate to the project directory:
cd assignment

3.Build the project using Maven:
mvn clean install

4.Run the application:
mvn spring-boot:run

The application will start and listen for HTTP requests on port 8080 by default.
