# Tredara

## Table of Contents

<!--ts-->

- [About](#about)
- [Project SetUp](#project-setup)
  - [Prerequisites](#prerequisites)
  - [Build and Generate the JAR file](#build_and_generate_the_jar_file)
  - [How to run and build in Docker](#how_to_run_and_build_in_Docker)
- [Implementation And Design](#implementation-and-design)
  - [Architectural Diagram](#architectural-diagram)
  - [UseCase Diagram](#usecase-diagram)
  - [Class Diagram](#class-diagram)
  - [Model Diagram](#model-diagram)
  - [Component Diagram](#Component_Diagram)
- [Tool Used For Project Management](#tool-used-for-project-management)
- [Collaboration](#collaboration)
- [Future Enhancement](#future-enhancement)

<!--te-->

## About

Tredara application wants to revolutionize the secondhand market by bringing it to the internet.It was designed to sell
items through bidding so that the customer who bid more up to the end date will get the product.In this Project,we have two users.

1. Admin: The Admin have the option to view all the items of customers, delete any item and also end the bid in some circumstances. Note that he can't perfomr Create/Update/Bidding operations.

2. Customer: The Customer have the following option:
    - Create: Create/Place new Autions item with mandatory information.
    - Update: Update the aution items , if there is any change and also he can re-open the Inaction auction items.
    - Delete: Delete the auction items
    - End Bid: Can end the bid in some circumstances.
    - AllItems: Listed all the items (including inactive) that are belongs to the logged user

Important: The Bid is ended automatically,when the time ends or the seller can end his bid before the time also.The Highest bidder will win the auction item and he gets notified by an email from Tredara.

## Project Setup

#### Prerequisites

Check the following links for documentation and guides:

[![](https://img.shields.io/badge/Maven-f1c40f?style=for-the-badge)](https://maven.apache.org/)
[![](https://img.shields.io/badge/Spring-6db33f?style=for-the-badge&logo=Spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![](https://img.shields.io/badge/Docker-2496ed?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com)
[![](https://img.shields.io/badge/NPM-000000?style=for-the-badge&logo=npm&logoColor=white)](https://docs.npmjs.com/about-npm)
[![](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)](https://reactjs.org/)
[![](https://img.shields.io/badge/Mysql-3E6E93?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/downloads/)
[![](https://img.shields.io/badge/Postman-ff6c37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/downloads/)
[![](https://img.shields.io/badge/typescript-235a97?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)

#### Build and Generate the JAR file (compile the source code)

```
# Git clone (download the source code)
$ git clone https://github.com/novare-tredara/Tredara

# Project structure
.
????????? ...
    ?????????tredara-backend/
         ?????????src
         ?????????pom.xml
    ?????????tredara-frontend/
         ?????????src
         ?????????package.json
    ?????????sql
    ?????????design/
    ?????????docker-compose.yml
    ?????????Dockerfile.backend
    ?????????Dockerfile.frontend
    ?????????README.md

$ cd Tredara
```

```
# Backend Application: Build and Run the JAR file (compile the source code)
$ cd tredara-backend
$ mvn clean package -DskipTests=true
$ java -jar "-Dspring.profiles.active=prod" target/tredara-backend-0.0.1.jar

# Frontenf Application : Install and run
$ cd tredara-frontend
$ npm install
$ npm run start

NOTE:
Without Docker env: Change"proxy": "http://localhost:8080/api/" in package.json file
```

#### How to run and build in Docker (verified in 20.10.21)

```
$ cd Tredara
$ docker compose up --build --force-recreate (or) docker-compose up --build --force-recreate
```

#### Hosted in AWS

Link - http://13.53.43.172:3000

#### How to Setup and Access the Database, Backend and Frontend application

Note: Docker container setup the database enviroenment default data using sql/init.sql file.

- Database:
  - User : root
  - Password : root
  - Database Schema: tredara
  - Port : 3306
  - Initial Database schema: sql/init.sql
- Backend:
  - Profiles : prod (production environemnt) and dev (used during the development phase)
  - Server Port: 8080
  - Server Address : localhost
- Frontend:
  - Port: 3000
  - Address: localhost

Tredara Web application URL: http://localhost:3000/<br>

In order to access the admin section here are credentials:

- email : admin@tredara.com
- password :12345

## Implementation and Design

1. TypeScript ??? Coding language for implementing the Frontend application.
2. React -Framework for building the Graphical User Interface.
3. Eclipse/Intellij Idea ??? IDE for developing backend development.
4. Spring boot- Open Source Framework in Java (Swagger, JobRunr, etc.).
5. Visual Studio - IDE used for developing Frontend development.
6. Postman- Postman is an API platform to design, build, test and iterate API???s.
7. Figma - Design tool to design a website how the website looks.
8. Microsoft word ??? Document preparation.
9. Docker

[Link for Design and documentation](https://docs.google.com/document/d/14i90zX6gmQh2WMupiTy1bymrvfY2LhsFNgJH3Z4uu6Y/edit#)

### Architectural Diagram

Spring boot MVC shall be used in designing the Natflix application, see below:<br>
<img src="design/Architecture-flow-of-spring-boot-Applications.png" width="450" height="250">

Architecture flow of Sprint boot application and React Web application.<br>
<img src="design/Architecture-flow-of-spring-boot-react.png" width="450" height="250">

### Usecase Diagram

A low detail diagram to visualize how the application will work.<br>
<img src="design/Usecase.png" width="450" height="250">

### Class Diagram

The class diagram allows to visualize the overall hierarchy of the project.<br>
<img src="design/ClassDiagram.png" width="450" height="250">

### Model Diagram

The model diagram represents the abstract view of the system. ER diagram stands for Entity Relationship Diagram.<br>
<img src="design/ModelDiagram.png" width="450" height="250">

### Component Diagram

The component tree allows to visualize the overall hierarchy of the project.<br>
<img src="design/ComponentDiagram.png" width="450" height="250">

### Swagger API

Swagger is an open source set of rules, specifications and tools for developing and describing RESTful APIs. The Swagger
framework allows developers to create interactive, machine and human-readable API documentation.

Link to Swagger :http://localhost:8080/swagger-ui/index.html
<br>

<img src="design/APIDocs.png" width="450" height="250">

### Java Logging

A Java logging framework is a computer data logging package for the Java platform. Logging refers to the recording of activity by an application and is a common issue for development teams. Logging frameworks ease and standardize the process of logging for the Java platform

```
<application>/log
|- org
|- META-INF
|- BOOT-INF
|- logs
    |- application.json
    |- application.gz
$ docker container exec  8948c4010ab6 sh -c "ls -ltr /application/logs"
-rw-r--r--    1 root     root        125026 Nov 18 11:02 application.json
```

### JobRunr

JobRunr is a library that we can embed in our application and which allows us to schedule background jobs using a Java 8
lambda. We can use any existing method of our Spring services to create a job without the need to implement an
interface. A job can be a short or long-running process, and it will be automatically offloaded to a background thread
so that the current web request is not blocked.

Link to JobRunr: http://localhost:8000/dashboard/overview
<br>

<img src="design/JobRunr.png" width="450" height="250">

## Tool used for project management

JIRA tool is used for this project, mainly for maintaining the tredara application backlogs. In sort following activities are done

- EPIC
- User stories
- Sprint handling
- etc.

### Link to JIRA Activities

https://github.com/novare-tredara/Tredara/blob/Mallika/design/jiradashboard-Activities.pdf

## Collaboration

This is a team project, all Project management, Frontend and backend are developed in collaboration.

### Team Members -

1. Naga Malleswari
2. Radha
3. SaiLatha Tammana

## Future Enhancement

1. Web hosting with Domain
2. User management
   - Password change
   - Forgot password
   - Email verification
3. Add Favourite auction items
4. Payment service
