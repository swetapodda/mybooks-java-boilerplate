# MyBooks - A Case Study

## Problem Statement

Build a system to search for a book title, show results, add books to favourite list and recommend most liked/favourite books to user.

## Requirements

### The application needs to fetch book details from the following Open Library API.
https://openlibrary.org/developers/api

Refer the following URLs to explore more on the Books open library APIs.
https://openlibrary.org/dev/docs/api/search
https://openlibrary.org/dev/docs/api/books

### A frontend where the user can register/login to the application, search books by string, title or author, get books details, add book to favourite list and view recommended books.
### User can add a book to favourite list and should be able to view the favourite list.
### A recommendation service should be able to store all the unique favourite books from all the users and maintain counter for number of users added a particular book into favourite list.
### An option to view recommended books should be available on frontend. 

## Microservices/Modules
- UserService - should be able to manage user accounts.
- UI (User interface) -  should be able to
    - Search a book by string, title or author
    - View book details
    - Add a book to favourite list
    - should be able to view favourite books
    - should be able to view recommended books
- UI should be responsive which can run smoothly on various devices 
- FavouriteService - should be able to store and retrieve all the favourite books for a user
- BookRecommendationService - should be able to store all the unique favourite books from all the users and maintain counter for number of users added a particular book into favourite list.

## Tech Stack
- Spring Boot
- MySQL, MongoDB
- API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

- Building frontend:
  1. Building responsive views:
    - Register/Login
    - Search for a book
    - Book list - populating from external API
    - Build a view to show favourite books
    - Build a view to show recommended books
  2. Using Services to populate these data in views
  3. Stitching these views using Routes and Guards
  4. Making the UI Responsive
  5. E2E and Unit tests
  6. Writing CI configuration file
  7. Dockerize the frontend

- Building the UserService
  1. Creating a server in Spring Boot to facilitate user registration and login using     JWT token and MySQL
  2. Writing swagger documentation
  3. Unit Testing
  4. Write CI Configuration
  5. Dockerize the application
  6. Write docker-compose file to build both frontend and backend application

- Create an API Gateway which can serve UI and API Request from same host

- Building the Favourite Service
  1. Building a server in Spring Boot to facilitate CRUD operation over favourite books    stored in MongoDB
  2. Writing Swagger Documentation
  3. Build a Producer for RabbitMQ which
    i. Produces events like what user added to favourite list
  4. Write Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose

- Building the Book Recommendation Service
  1. Building a Consumer for RabbitMQ
    - i.  On a new event generated update the recommendations in the system. Store the        recommendation list in MongoDB.
    - ii. Maintain list of unique recommended books based on what user added into             favourite list and keep counter for number of users added a particular book         into favourite list
  2. Build an API to get Recommendations
  3. Writing Swagger Documentation
  4. Write Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose
  8. Update the API Gateway

- Create Eureka server and make other services as client

- Demonstrate the entire application


https://openlibrary.org/people/AGU35
https://covers.openlibrary.org/w/id/283491.jpg
http://openlibrary.org/works/OL27258W/editions.json?limit=50
https://openlibrary.org/dev/docs/restful_api#query

http://covers.openlibrary.org/b//books/OL26435876M/$value-$size.jpg

https://openlibrary.org/subjects/halley's_comet.json?limit=12&offset=12
https://openlibrary.org/subjects/space_flight.json?limit=200&offset=1
https://openlibrary.org/subjects/fiction.json?limit=200&offset=1