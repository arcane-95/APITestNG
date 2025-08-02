# APITestNG

A simple REST API testing project using Java, TestNG, and REST Assured.

## What it does

This project tests REST APIs with different types of HTTP requests:
- GET, POST, PUT, DELETE operations
- Different ways to send request data
- Path and query parameters
- API authentication

## Requirements

- Java 8+
- Gradle

## Setup

1. Clone the project
2. Run: `./gradlew build`

## Run Tests

```bash
# Run all tests
./gradlew test

# Run specific test
./gradlew test --tests HTTPRequestsTest
```

## Test Files

- `HTTPRequestsTest.java` - Basic CRUD operations
- `PostRequestTest.java` - Different POST request methods  
- `PathAndQueryParamTest.java` - URL parameter testing

## Configuration

Update `src/test/resources/config.properties` with your API key if needed.

## APIs Used

- ReqRes.in API for most tests
- Local JSON server for some POST tests (requires `json-server` npm package)

That's it! The tests will run against real APIs and show you how to test different scenarios.
