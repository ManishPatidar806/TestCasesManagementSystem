# Test Case Management System

## Introduction
 This system is designed to streamline the management and organization of test cases for software projects. It offers a user-friendly interface to create, update, and track test cases efficiently.

## Setup Instructions

### Prerequisites
Before you begin, ensure you have the following installed:
- Java Development Kit (JDK) 21 or higher
- Maven
- MongoDB (v7 or higher)

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/ManishPatidar806/TestCasesManagementSystem.git
    cd TestCasesManagementSystem
    ```

2. Install the necessary dependencies:
    ```bash
    mvn clean install
    ```

3. Configure environment variables:
    Create an `application.properties` file in the `src/main/resources` directory with the following content:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/test-case-management
    server.port=8080
    ```

4. Start the MongoDB server:
    ```bash
    mongod
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

6. Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI.

## Sample Data

The sample data for MongoDB is available in the `SampleTestingData/TestingDataForMongodb.test_cases.json` file. This file contains predefined test cases that can be imported into your MongoDB instance to quickly get started with the application.


## Design Details

### Architecture
The application is built using Spring Boot for the backend, with MongoDB as the database. It leverages Spring Data MongoDB for data access, Model Mapper for object mapping, and Spring Validation for input validation. Swagger is integrated for API documentation, and a logger is used for logging purposes.

### Assumptions
- Users have a basic understanding of Java, Spring Boot, and MongoDB.
- The application will be run in a development environment.

### Trade-offs
- MongoDB was chosen for its flexibility and scalability, though it may not be suitable for all types of data.
- Spring Boot was selected for its rapid development capabilities and ease of integration with other Spring projects.

## Note
- Unit and integration testing have not been performed in this project as I am still learning.

