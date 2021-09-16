## Money Transfer Service
REST API for working with account operations

### Technologies
- Kotlin
- Spring Boot
- Maven
- H2 in-memory database
- Liquibase
- JUnit
- Docker

**Building**
-
    git clone https://github.com/Sunagatov/MoneyTransfer.git
    cd MoneyTransfer
    mvn clean install

**Running**
-
    1) Build an docker image with the following command:
         'docker build -t money-transfer .'

    2) Then we can run it by running the following command:
         'docker run -p 8080:8080 money-transfer'

    3) Then we can open Money Transfer Service API by running the following command:
         'start "" "http://localhost:8080/swagger-ui/"'
    

**API**
-

**Get an existing account details**

    GET localhost:8080/accounts/{id}

**Get all existing accounts**

    GET localhost:8080/accounts

**Create a new account**

    POST localhost:8080/accounts

**Transfer money from one account to another**

    PATCH localhost:8080/accounts/transfer-money

**Add money to an existing account**

    PATCH localhost:8080/accounts/add-money

**Withdraw money from an existing account**

    PATCH localhost:8080/accounts/withdraw-money

**Delete all existing accounts**

    DELETE localhost:8080/accounts

**Delete existing account by id**

    DELETE localhost:8080/accounts/{id}
