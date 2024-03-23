FROM ubuntu:latest
LABEL authors="brenoap"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080