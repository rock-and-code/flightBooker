# Flight Booker App
This is a web-based air travel booker app built with Bootstrap and Spring Boot. It allows users to search for and book flights, view flight details, and manage their bookings. The app is built with a responsive and modern UI using Bootstrap for the frontend, and Spring Boot for the backend to handle user authentication, flight data retrieval, and booking processing.

# HTTPS
The application enables HTTPS by using a self-signed certificate. If you have any problems with the self-signed certificate, you can generate a new one using the following command:

<code>keytool -genkeypair -alias flightBooker -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore flightBooker.p12 -validity 3650</code>

# Installation
Clone the repository:

<code>git clone https://github.com/rock-and-code/flightbooker.git</code>

Open the project in your favorite IDE.

Update the database credentials in src/main/resources/application.properties.

# Build and run the project using Maven:

mvn clean install
mvn spring-boot:run
Navigate to https://localhost:8443 in your web browser to view the app.

# Usage
The app provides the following features:

Search for flights: Users can search for flights by entering their origin, destination, and travel dates.
View flight details: Users can view flight details such as departure times, flight number, and price.

Features pending to be added:
Book flights: Users can select a flight and book tickets for it, specifying the number of passengers.
Manage bookings: Users can view and manage their bookings, including canceling bookings.

# Getting Started
Prerequisites
To run the app, you will need to have the following installed:
<ul>
  <li>Java 17 or above</li>
</ul>

# FlightRestController
The app provides a RESTful API for managing flights.
### Endpoints

### Update a flight by ID
Method: <code>PUT</code>

URL: <code>https://localshots:8443/api/v1/flights/{flightId}</code>

Request body: A JSON object containing the flight data to update.

Response:
<ul>
  <li><code>204 No Content</code>: The flight was updated successfully.</li>
  <li><code>404 Not Found</code>: The flight was not found.</li>
</ul>

### Partially update a flight by ID
Method: <code>PATCH</code>

URL: <code>https://localshots:8443/api/v1/flights/{flightId}</code>

Request body: A JSON object containing the flight data to update.

Response:
<ul>
  <li><code>204 No Content</code>: The flight was updated successfully.</li>
  <li><code>404 Not Found</code>: The flight was not found.</li>
</ul>

### Save a flight
Method: <code>POST</code>

URL: <code>https://localshots:8443/api/v1/flights</code>

Request body: A JSON object containing the flight data to save.

Response:
<ul>
  <li><code>201 Created</code>: The flight was created successfully.</li>
</ul>

### Get all flights for today
Method: <code>GET</code>

URL: <code>https://localshots:8443/api/v1/flights/today</code>

Query parameters:
<ul>
  <li><code>pageNumber</code>: The page number to return. Defaults to 0.</li>
  <li><code>pageSize</code>: The number of flights per page. Defaults to 25.</li>
</ul>

Response:
<ul>
  <li><code>200 OK</code>: A paginated list of flights.</li>
</ul>

### Get all flights starting from today
Method: <code>GET</code>

URL: <code>https://localshots:8443/api/v1/flights</code>

Query parameters:
<ul>
  <li><code>pageNumber</code>: The page number to return. Defaults to 0.</li>
  <li><code>pageSize</code>: The number of flights per page. Defaults to 25.</li>
</ul>

Response:
<ul>
  <li><code>200 OK</code>: A paginated list of flights.</li>
</ul>

### Get a flight by ID
Method: <code>GET</code>

URL: <code>https://localshots:8443/api/v1/flights/{flightId}</code>

Response:
<ul>
  <li><code>200 OK</code>: The flight.</li>
  <li><code>404 Not Found</code>: The flight was not found.</li>
</ul>

### Delete a flight by ID
Method: <code>DELETE</code>

URL: <code>https://localshots:8443/api/v1/flights/{flightId}</code>

Response:
<ul>
  <li><code>204 No Content</code>: The flight was deleted successfully.</li>
  <li><code>404 Not Found</code>: The flight was not found.</li>
</ul>

### Request body
The request body for all endpoints that accept a request body is a JSON object containing the following properties:
<ul>
  <li><code>departureAirport</code>: The departure airport code.</li>
  <li><code>arrivalAirport</code>: The arrival airport code.</li>
  <li><code>departureDate</code>: The departure date.</li>
  <li><code>availableSeats</code>: The number of seats available for sale.</li>
  <li><code>flightNumber</code>: The flight number.</li>
  <li><code>price</code>: The flight ticket price.</li>
</ul>

### Response
The response for all the get endpoints is a JSON object containing the following properties:
<ul>
  <li><code>id</code>: The ID of the flight, if creating or updating a flight.</li>
  <li><code>departureAirport</code>: The departure airport code.</li>
  <li><code>arrivalAirport</code>: The arrival airport code.</li>
  <li><code>departureDate</code>: The departure date.</li>
  <li><code>availableSeats</code>: The number of seats available for sale.</li>
  <li><code>flightNumber</code>: The flight number.</li>
  <li><code>price</code>: The flight ticket price.</li>
</ul>

### Errors
The following errors may be returned:
<ul>
  <li><code>400 Bad Request</code>: The request body is invalid.</li>
  <li><code>404 Not Found</code>: The flight was not found.</li>
</ul>

# UserRestController
This controller provides the following endpoints for managing users:
### Endpoints

### Get all users
This endpoint returns a list of all users.

Method: <code>GET</code>

URL: <code>https://localshots:8443/api/v1/users</code>

### Register a new user
This endpoint register a new user. The request body must contain XML wrapped with <user> tags and with the following properties:

<ul>
  <li><code>firstName</code>: The user's first name.</li>
  <li><code>lastName</code>: The user's last name.</li>
  <li><code>email</code>: The user's email address.</li>
  <li><code>password</code>: The user's password.</li>
</ul>

Example of the body XML:
  
```
  <user>
    <firstName>First Name</firstName>
    <lastName>Last Name</lastName>
    <email>user@email.com</email>
    <password>password</password>
  </user>
```
  
Method: <code>POST</code>

URL: <code>https://localshots:8443/api/v1/users</code>
  
Response:
<ul>
  <li><code>201 Created</code>: The user was registered.</li>
  <li><code>409 Conflict</code>: The user already exists.</li>
</ul>
  
### Get user by id
This endpoint returns a user by ID.
  
Method: <code>GET</code>

URL: <code>https://localshots:8443/api/v1/users/{userId}</code>
  
Response:

<ul>
  <li><code>200 OK</code>: The request was successful.</li>
  <li><code>404 Not Found</code>: The user was not found.</li>
</ul>

# Built With
Bootstrap - Frontend framework
Spring Boot - Backend framework
H2 - In-memory Relational database


# License
This project is licensed under the MIT License
