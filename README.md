# Example Of Spring, Alpine and Thymeleaf

This is an example of Springboot using Thymeleaf+AlpineJs for it's UI side. I cretated this project to serve as a template project ( For backend only or Full-Stack).

## Run Local

To run this project on your local :

```bash
git pull {githubtothisproject}

cd {projectdir}

mvn springboot:run
```

project should run at localhost at port 8080 by default. A dummy data will be injected. You can find dummy data sql in **/src/main/resources/data.sql**

## Project Use Case

A very simple use case was created for this project. There are two entity, **category** and **product**, with One-To-Many relationship between it. Category is the parent and product is the child.

## Project Utilty

* CRUD-Search Category
* CRUD-Search Product

## Project Structure

Below is the project structure :

* **Entity**        : represent database table.
* **Repository**    : interface for database function.
* **Controller**    :
  * **Web**       : controller for web.
  * **API**       : controller for accessing service through rest endpoint.
* **DTO**           : model for request, web response , etc.
* **Exception**     : exception in program.

All UI (HTML) Code and Alpine stored in **/src/main/resources/templates**

## Database

This project already embedded H2 Database, so created data will lost upon restart.  You can change the DB configuration in application.properties :

```bash
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
