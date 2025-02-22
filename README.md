# Quake 3 Log Parser

This Java application is a parser for log file was generated by a Quake 3 Arena server. 
Grouping the information into a general ranking, with all rounds played on the server, such as:
- Players who played it;
- Total number of deaths on the round;
- Each player's score;
- The type of deaths.

## Run Locally

Clone the project

```bash
  git clone https://github.com/brenoap/quake-log-parser
```

Go to the project directory

```bash
  cd QuakeLogParser
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  java -jar -Dspring.profiles.active=default target/QuakeLogParser-1.0-SNAPSHOT.jar
```

## Check Game Round report

```bash
  http://localhost:8080/api/round-report
```
## Check Death Cause Round report

```bash
  http://localhost:8080/api/death-cause-count
```

## Running Tests

To run tests, run the following command

```bash
  mvn clean test
```

### JaCoCo Report

After running 'mvn clean test', you can check JaCoCo statistics

### SonarQube Statistics

To run check SonarQube statistics, run the following command changing the 'Dsonar.login' token

```bash
  mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=squ_5155f07f6e4b5f8abeeab1457901237051e9ab70
```

## API Reference

#### Swagger

```http
  http://localhost:8080/swagger-ui.html
```

##  Gerando a image Docker do projeto

### Criar a imagem localmente

`docker build -t nenodes/quake-log-parser`

### Enviar a imagem para o DockerHub

`docker push nenodes/quake-log-parser`

## Tech Stack

- [Java](https://www.java.com/en/) - high-level, class-based, object-oriented programming language 
- [Maven](https://maven.apache.org/) - build automation tool used primarily for Java projects
- [Spring Boot](https://spring.io/projects/spring-boot) - open-source Java-based framework used for programming standalone, production-grade applications with minimal effort.
- [Swagger](https://swagger.io/) - suite of tools for API developers from SmartBear Software and a former specification upon which the OpenAPI Specification is based.
- [Lombok](https://projectlombok.org/) - library that automatically plugs into your editor and build tools, spicing up your java
- [JaCoCo](https://www.eclemma.org/jacoco/) - code coverage library
- [SonarQube](https://www.sonarsource.com/products/sonarqube/) - open-source platform developed by SonarSource for continuous inspection of code quality
- [Docker](https://www.docker.com/) - platform designed to help developers build, share, and run container applications.

## Authors

- [@brenoap](https://www.github.com/octokatherine)