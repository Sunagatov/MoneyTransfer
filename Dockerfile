FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/money-transfer-0.0.1.jar money-transfer-0.0.1.jar
ENTRYPOINT ["java", "-jar", "money-transfer-0.0.1.jar"]