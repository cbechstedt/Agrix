# Farm API

![Java](https://img.shields.io/badge/Java-17-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.1-brightgreen) ![MySQL](https://img.shields.io/badge/MySQL-blue) ![Docker](https://img.shields.io/badge/Docker-Blue)

I developed a REST API in Java, using the Spring ecosystem. This API utilizes Spring Data JPA for
persistence in a MySQL database and Spring Security with JWT for authentication and authorization. 
Moreover, this app is Dockerized, making it easy to deploy and run across different environments.

## Database

### Database Schema

The data model includes the following tables:

- **farm**: represents a farm.
- **crop**: represents a plantation and maintains a 'many-to-one' relationship with the 'farm'
  table.
- **fertilizer**: represents a fertilizer and maintains a 'many-to-many' relationship with the '
  crop' table through the **crop_fertilizer** table.

![Tabelas](images/agrix-tabelas-fase-b.png)

## Installation
### Running the application with Dcoker
- Make sure Docker is installed on your machine.
- Build the Docker image in your terminal from the project directory:
```bash
docker compose up -d
```
To install the project's dependencies, execute the following command:
```bash
mvn install -DskipTests
```

## Request and Response examples
<details>
  <summary>🔍 Request and response format/example on POST '/persons' route to create a user.</summary><br /> 

Request:

```json
{
  "username": "Pedro",
  "password": "123456",
  "role": "ADMIN"
}
```

Response:

Status: 201 Created
```json
{
  "id": 1,
  "username": "Pedro",
  "role": "ADMIN"
}
```
</details>
<details>
  <summary>🔍 Request and response format/example on POST '/auth/login' route to log in and receive a token.</summary><br /> 

Request:

```json
{
  "username": "Pedro",
  "password": "123456"
}
```

Response:

Status: 200 OK
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpc3N1ZXIiLCJzdWIiOiJuYW5pIiwiZXhwIjoxNzEyNzgxMTYxfQ.Kpxq2E7KtANq_Wx8RTYuJEkVljFf3EaHlSCUOKsj9Ss"
}
```
</details>
<details>
  <summary>🔍 Request and response format/example on POST '/farms' route to create a farm.</summary><br />
  It is necessary to include token of type Bearer Token in the Authorization tab to have authorization in this and in other requests.

Request:

```json
{
  "name": "Capão Farm",
  "size": "5"
}
```

Response:

Status: 201 Created
```json
{
  "id": 1,
  "name": "Capão Farm",
  "size": 5.0
}
```
</details>
<details>
  <summary>🔍 Request and response format/example on PUT '/farms' route, to edit a farm.</summary><br />
Request:

```json
{
  "name": "Capão Farm",
  "size": "6.6"
}
```

Response:

Status: 200 OK
```json
{
  "id": 1,
  "name": "Capão Farm",
  "size": 6.6
}
```
</details>

<details>
  <summary>🔍 Request and response format/example on GET '/farms' route to return all farms.</summary><br />
Response:
  
Status: 200 OK
```json
[
  {
    "id": 1,
    "name": "RS Farm",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Imbé Farm",
    "size": 6.3
  }
]
```
</details>
<details>
  <summary>🔍 Request and response format/example on DELETE '/farms/{id}' route to delete a farm based on its id.</summary><br />
Response:
  
Status: 204 No Content

</details>
<details>
  <summary>🔍 Request and response format/example on POST '/fertilizers' route to create a fertilizer.</summary><br /> 

Request:

```json
{
  "name": "NPK",
  "brand": "My Brand npk",
  "composition": "nitrogen, phosphorus and potassium."
}
```

Response:

Status: 201 Created
```json
{
  "id": 1,
  "name": "NPK",
  "brand": "My Brand npk",
  "composition": "nitrogen, phosphorus and potassium."
}
```
</details>

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
