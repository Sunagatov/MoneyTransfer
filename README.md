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
**Create new account**

    POST localhost:8080/account/

**Get account details**

    GET localhost:8080/account/{id}

**Add money**

    PUT localhost:8080/account/add

**Transfer money**

    PATCH localhost:8080/account/transfer

**Withdraw money**

    PUT localhost:8080/account/withdraw
