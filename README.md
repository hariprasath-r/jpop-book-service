# jpop-book-service
Book Service for JPOP Program

Implementation: 
- Uses spring-web, spring-data-jpa and inmemory H2 database
- Uses lombok for generating boiler plate code
- The service context root is /book-api
- Default server port is 8082
- Added actuator endpoint for health http://localhost:8082/books/actuator/health

Features:
1. Exposes REST APIs for performing basic CRUD operations on Book
