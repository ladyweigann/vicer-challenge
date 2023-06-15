# viceri-challenge

## Important!
This project is not completely filling all requirements that were proposed.
Due so many deprecated things to configure the Spring Security, I was not able to finish yet.
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

## Proposal
The objective of this challenge is to create a REST API for a task management application (TO-DO). 
## Requirements
- Register a new user
- Add a new task
- Delete a task
- Update a task
- Mark a task as completed
- List pending tasks, optionally filtered by priority
- User authentication using email and password
- Provide API documentation using Swagger
## Tools and Technologies
- Java 17
- Maven
- Spring Boot
- JUnit
- Mockito
- Swagger
- MapStruct
## General Instructions
- Clone this project using HTTPS or SSH
- Inside the project folder, run `mvn clean install`
- Open the project using your favorite IDE and run the main class - `ViceriApplication.java`
- After that, your project should be up and running in the 8080 port
- Now, you can access the Swagger Documentation [here](http://localhost:8080/swagger-ui/index.html#/)
- In the link above, you can see all endpoints, API responses, etc.
- You can use Postman or Insomnia to test this application.

## Endpoints
#### Tasks
- [GET] http://localhost:8080/tasks - Get all tasks with pending status
- [POST] http://localhost:8080/tasks - create a new task with status already set as pending. Request Body:
  ```` Body (Json):  
  {
    "description": String,
    "priority": String
  }
- [PUT] http://localhost:8080/tasks/{id} - Edit a task - Request Body:
  ```` Body (Json):  
  {
    "description": String,
    "priority": String
  }
- [PATCH] http://localhost:8080/tasks/{id}/complete-task - Set a status as complete for the task that you've passed the id as path variable
- [DELETE] http://localhost:8080/tasks/{id} - delete a task

#### Users
- [GET] http://localhost:8080/users - get all users
- [POST] http://localhost:8080/users/register - register a new user
  ```` Body (Json):  
  {
    "name": String,
    "email": String,
    "password": String
  }

- [POST] http://localhost:8080/users/login - login
  ```` Body (Json):  
  {
     "email": String,
     "password": String
  }