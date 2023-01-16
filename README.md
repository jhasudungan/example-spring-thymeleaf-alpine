# Example Of Spring, Alpine and Thymeleaf

This is an example of Springboot using Thymeleaf+AlpineJs for it's UI side. I cretated this project to serve as a template project ( For backend only or Full-Stack).

## Run Local

_____________________________________________________________________________________________________
To run this project on your local :

```
git pull {githubtothisproject}

cd {projectdir}

mvn springboot:run
```

project should run at localhost at port 8080 by default.

## Project Use Case

_____________________________________________________________________________________________________

A very simple use case was created for this project. There are two entity, **category** and **product**, with One-To-Many relationship between it. Category is the parent and product is the child.

## Project Utilty

_____________________________________________________________________________________________________

* CRUD-Search Category
* CRUD-Search Product

## Project Structure

_____________________________________________________________________________________________________

Below is the project structure :

* **Entity**        : represent database table.
* **Repository**    : interface for database function.
* **Controller**    :
  * **Web**       : controller for web.
  * **API**       : controller for accessing service through rest endpoint.
* **DTO**           : model for request, web response , etc.
* **Exception**     : exception in program.

## Database

_____________________________________________________________________________________________________

This project already embedded H2 Database, so created data will lost upon restart.  You can change the DB configuration in application.properties :

```
# H2 Database Config
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 GUI Config
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.h2.console.settings.trace=false

# Disable Hibernate Auto Configuration
spring.jpa.hibernate.ddl-auto=none
```
