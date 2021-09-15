FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ADD target/money-transfer-0.0.1.jar money-transfer.jar
ENTRYPOINT ["java", "-jar", "money-transfer.jar"]