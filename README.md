# Spring Boot + PostgreSQL

A simple Spring Boot project integrating PostgreSQL.

## Prerequisites
- Java 11+
- PostgreSQL
- Maven or Gradle
  
## Setup & Installation
 **Clone the repository**  
   
  - git clone https://github.com/nizariskandar-mhn/springboot-postgresql.git
  - cd springboot-postgresql

## Configure Database
Update application.properties with your PostgreSQL credentials:

- spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
- spring.datasource.username=your_user
- spring.datasource.password=your_password

## Run the Application

- ./mvnw spring-boot:run  # For Maven
