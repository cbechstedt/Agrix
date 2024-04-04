# Farm API

![Java](https://img.shields.io/badge/Java-17-brightgreen) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.1-brightgreen) ![MySQL](https://img.shields.io/badge/MySQL-blue)

I developed a REST API in Java, using the Spring ecosystem. This API utilizes Spring Data JPA for
persistence in a MySQL database and Spring Security with JWT for authentication and authorization.

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

To install the project's dependencies, execute the following command:

```bash
mvn install -DskipTests
```

## Request and Response examples
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta na rota POST '/persons', para criar usuário.</summary><br /> 

Exemplo de requisição:

```json
{
  "username": "Pedro",
  "password": "123456",
  "role": "ADMIN"
}
```

Exemplo de resposta:

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
  <summary>🔍 Formato/exemplo de requisição e resposta na rota POST '/auth/login', para logar e receber token.</summary><br /> 

Exemplo de requisição:

```json
{
  "username": "Pedro",
  "password": "123456"
}
```

Exemplo de resposta:

Status: 200 OK
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpc3N1ZXIiLCJzdWIiOiJuYW5pIiwiZXhwIjoxNzEyNzgxMTYxfQ.Kpxq2E7KtANq_Wx8RTYuJEkVljFf3EaHlSCUOKsj9Ss"
}
```
</details>
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta na rota POST '/farms', para criar uma farm.</summary><br />
  É necessário inserir o token, do tipo Bearer Token, na aba Authorization para ter autorização nessa em nas demais requisi.

Exemplo de requisição:

```json
{
  "name": "Capão Farm",
  "size": "5"
}
```

Exemplo de resposta:

Status: 201 Created
```json
{
  "id": 1,
  "name": "RS Farm",
  "size": 5.0
}
```
</details>
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta na rota PUT '/farms', para editar uma farm.</summary><br />
Exemplo de requisição:

```json
{
  "name": "Capão Farm",
  "size": "5"
}
```

Exemplo de resposta:

Status: 201 Created
```json
{
  "id": 1,
  "name": "RS Farm",
  "size": 5.0
}
```
</details>

<details>
  <summary>🔍 Formato/exemplo de resposta na rota GET '/farms', para retornar todas farms.</summary><br />
Exemplo de resposta:
  
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
  <summary>🔍 Formato/exemplo de resposta na rota DELETE '/farms/{id}', para deletar a farm correspondente ao id.</summary><br />
Exemplo de resposta:
  
Status: 204 No Content

</details>
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta na rota POST '/fertilizers', para criar um fertilizante.</summary><br /> 

Exemplo de requisição:

```json
{
  "name": "NPK",
  "brand": "My Brand npk",
  "composition": "nitrogen, phosphorus and potassium."
}
```

Exemplo de resposta:

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
