# Flight Booker App
This is a web-based air travel booker app built with Bootstrap and Spring Boot. It allows users to search for and book flights, view flight details, and manage their bookings. The app is built with a responsive and modern UI using Bootstrap for the frontend, and Spring Boot for the backend to handle user authentication, flight data retrieval, and booking processing.

# Getting Started
Prerequisites
To run the app, you will need to have the following installed:
<ul>
  <li>Java 17 or above</li>
</ul>

# Installation
Clone the repository:

git clone https://github.com/rock-and-code/flightbooker.git

Open the project in your favorite IDE.

Update the database credentials in src/main/resources/application.properties.

# Build and run the project using Maven:

mvn clean install
mvn spring-boot:run
Navigate to http://localhost:8080 in your web browser to view the app.

# Usage
The app provides the following features:

Search for flights: Users can search for flights by entering their origin, destination, and travel dates.
View flight details: Users can view flight details such as departure times, flight number, and price.

Features pending to be added:
Book flights: Users can select a flight and book tickets for it, specifying the number of passengers.
Manage bookings: Users can view and manage their bookings, including canceling bookings.

# Built With
Bootstrap - Frontend framework
Spring Boot - Backend framework
H2 - In-memory Relational database


# License
This project is licensed under the MIT License
