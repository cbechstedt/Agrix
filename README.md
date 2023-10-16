# Farm API

![Java](https://img.shields.io/badge/Java-17-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.1-brightgreen) ![MySQL](https://img.shields.io/badge/MySQL-blue)

I developed a REST API in Java, using the Spring ecosystem. This API utilizes Spring Data JPA for
persistence in a MySQL database and Spring Security with JWT for authentication and authorization.

## Database

### Modelo de Tabelas

The data model includes the following tables:

- **farm**: represents a farm.
- **crop**: represents a plantation and maintains a 'many-to-one' relationship with the 'farm'
  table.
- **fertilizer**: represents a fertilizer and maintains a 'many-to-many' relationship with the '
  crop' table through the **crop_fertilizer** table.

![Tabelas](images/agrix-tabelas-fase-b.png)

## Installation

To install the project's dependencies, execute the following command:

```bash
mvn install -DskipTests
```

## Running Tests

To execute all tests, use the following command:

```bash
mvn test
```

### Specific Test

To run a specific test, use the following command, replacing 'TestClassName' with the desired test
class name:

```bash
mvn test -Dtest=TestClassName
```

## Static Code Analysis

I used Checkstyle for static code analysis. To run Checkstyle manually on a project, execute the
following command:

```bash
mvn checkstyle:check
```