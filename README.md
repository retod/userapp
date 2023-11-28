# userapp
Spring Boot application that provides REST endpoints for managing users

## Environment setup

The application is developed and tested using:
- Windows 10
- Gradle 8.4
- MySql 8.0.35.0
- Java 21

These are required to succesfully build and run the project.

## Build and run application
1. Clone project source
2. Create database schema using <code>/src//main/scripts/dbschema-init.sql</code>
3. Build the application<br>
 Navigate to project directory <code>cd /path/to/userapp</code><br>
 Execute <code>gradle build</code>
4. To run the application execute <code>gradle bootRun</code>

The application will start on port 8080 by defalut.<br>
It is configured to work with local running instance of MySQL on port 3306. For further configuration you can edit <code>/src/main/resources/application.properties</code> file.

## Endpoints

The following endpoints are available for interactions with users:

- GET <code>/api/users</code> - list all users
- GET <code>/api/users/{id}</code> - get user by id
- POST <code>/api/users</code> - create user<br>
 Example payload:<br>
 <code>{
    "firstName" :"John",
    "lastName":"Doe",
    "email": "jd@example.com",
    "birthDate": "2017-02-23"
}</code>
  
- PUT <code>/api/users/{id}</code> - update user by id<br>
 Example payload:<br>
 <code>{
    "firstName" :"John",
    "lastName":"Doe",
    "email": "jd@example.com",
    "birthDate": "2017-02-23"
}</code>

- DELETE <code>/api/users/{id}</code> - delete user by id 
