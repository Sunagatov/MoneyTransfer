## Money Transfer Service
REST API for working with account operations

### Technologies
- Kotlin
- Spring Boot
- Maven
- H2 in-memory database
- Liquibase
- JUnit

**API**
-

**Get an existed account details**

    GET localhost:8080/accounts/{id}

**Get all existed accounts**

    GET localhost:8080/accounts

**Create a new account**

    POST localhost:8080/accounts

**Transfer money from one account to another**

    PATCH localhost:8080/accounts/transfer-money

**Add money to an existed account**

    PATCH localhost:8080/accounts/add-money

**Withdraw money from an existed account**

    PATCH localhost:8080/accounts/withdraw-money

**Delete all existed accounts**

    DELETE localhost:8080/accounts

**Delete existed account by id**

    DELETE localhost:8080/accounts/{id}
