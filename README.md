# Red Ventures - Backend

## Installing and running

Before running the application, be sure to install [Maven](https://maven.apache.org). Clone the repository, navigate to 
the project root and run the command below.

```
mvn clean install
```

This command will build the project. Now navigate to the *src/main/docker* (`cd src/main/docker`) folder and run the 
command below, which will start the database as well as the application.

```
docker-compose up
```

## Application

The application will run at [http://localhost:8080/api](http://localhost:8080/api). To execute any request to the API, 
you must be authenticated. The authentication is performed through a POST request at 
[http://localhost:8080/api/authenticate](http://localhost:8080/api/authenticate) with **Content-Type application/x-www-form-urlencoded**
and with the username and password below.

```
username: admin
password: password
```

The request response will contain the authentication token to use the API.

## Documentation

[Swagger](https://swagger.io/) is used to document the API. Accessing 
[http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html) you can view all public endpoints 
and all their details.

The available endpoints are listed below.

- GET `/users` [http://localhost:8080/api/users](http://localhost:8080/api/users)
- GET `/users/:id` [http://localhost:8080/api/users/:id](http://localhost:8080/api/users/:id)
- GET `/widgets` [http://localhost:8080/api/widgets](http://localhost:8080/api/widgets)
- GET `/widgets/:id` [http://localhost:8080/api/widgets/:id](http://localhost:8080/api/widgets/:id)
- POST `/widgets` for creating new widgets [http://localhost:8080/api/widgets](http://localhost:8080/api/widgets)
- PUT `/widgets/:id` for updating existing widgets [http://localhost:8080/api/widgets/:id](http://localhost:8080/api/widgets/:id)