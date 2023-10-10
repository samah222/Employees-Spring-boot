# Employees-Spring-boot
Employees management project
In this project, we intend to create a basic employee management system using spring boot, MySQL and Thymeleaf templates.
The project using Spring boot 3 and Java 17
The development steps are as follow:
1.	Create database employees on My local MySQL.
2.	Create spring-boot project using spring initializer with the following dependencies: Spring web, Spring data JPA, validation, MySQL driver, Lombok, live reload and Thymeleaf
3.	Create employee entity (id, name, department, email, telephone, job-title, salary, created_at, updated_at). The Id should be generated automatically by the db, validation should be taken as follow:
  - name not blank and its size between 2 and 50 characters
  - telephone should be full number with country code, i.e. start by plus sign (e.g. +2222222…)
  - only 10 digits or plus sign followed by 12 numbers in case of full number with country code (e.g. 0911111111 or +222911111111)
  - email should be valid email and unique
  - salary should be at least 1000.
  - Created_at and updated_at should be timestamp created by the spring boot
4.	Write APIs to 
- Get application info and version which is defined in application.properties
- get all employees (with paging)
- get a specific employee by its id + name
- save a new employee
- update an existing employee
- delete an employee
- filter employees by department
- filter employees by job-title
- filter employees by name
- filter employees whose salary’s in specific range (i.e. between min and max)
- filter employees by name and department
5.	Add global exception class to handle all exceptions that may occur 
6.	Return the proper HTTP status code (i.e. 200 for GET and PUT, 201 for POST and 204 for Delete)
7.	Use three different profiles: dev, qa and prod. Use different database for each profile and make the dev is the default and active profile

To run the application (using terminal) with development profile:
1. mvn clean install
2. cd .\target\
3. java -jar .\employees-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev


