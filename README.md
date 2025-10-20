# MELI Order System API - V1.0.0

![Java](https://img.shields.io/badge/Java-17+-orange.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg) ![Maven](https://img.shields.io/badge/Maven-blue.svg) ![H2](https://img.shields.io/badge/Database-H2-lightgrey.svg)

This repository contains the source code for the MELI Order System, a RESTful API developed with Spring Boot. The project was created to address and solve operational issues in an e-commerce order management system, providing a robust, well-documented, and easily testable solution.

**Current Version: 1.0.0** (Core CRUD functionality for orders implemented).

---

## ✨ Key Features

- **Full CRUD Functionality:** Create, Read, Update, and Delete operations for orders.
- **In-Memory Database:** Utilizes H2 Database for rapid development and testing, with a web console for easy data inspection.
- **RESTful Architecture:** Follows REST principles for a clean and standard API design.
- **Automated Build & Run:** Includes a startup script (`startup.bat`) to automate the build and execution process, simulating a real-world deployment.

---

## 🛠️ Technologies & Dependencies

- **Framework:** Spring Boot 3.x
- **Language:** Java 17
- **Build Tool:** Apache Maven
- **Core Dependencies:**
  - `Spring Web`: For building RESTful web applications.
  - `Spring Data JPA`: For data persistence and repository management.
  - `H2 Database`: For the in-memory relational database.
- **API Testing:** Postman

---

## 🚀 Getting Started

Follow these instructions to get the project running on your local machine.

### Prerequisites

- Java Development Kit (JDK) 17 or higher.
- Apache Maven (can be installed or used via the included Maven Wrapper).
- [Postman](https://www.postman.com/downloads/) for testing the API.

### Installation & Execution

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/carlop13/order_system.git
    cd order_system
    ```

2.  **Run the Startup Script:**
    This is the recommended way to build and run the application. The script automates both steps.
    
    Open a terminal (Command Prompt or PowerShell on Windows) in the project's root directory and run:
    ```bash
    .\startup.bat
    ```
    The script will first compile and package the application into a `.jar` file using Maven, and then it will launch the server.

3.  **Verify the Application is Running:**
    The server will start on `http://localhost:8080`. You will see the Spring Boot banner and log messages confirming the startup.

---

## 🛰️ API Endpoints (CRUD)

All endpoints are available under the base path `/api/orders`.

### 1. Create an Order

- **URL:** `/`
- **Method:** `POST`
- **Request Body (JSON):**
  ```json
  {
      "customerName": "Carlos Lopez",
      "product": "Laptop",
      "quantity": 1,
      "price": 25000.00
  }
  ```
- **Success Response:**
  - **Code:** `(201 Created): The newly created order document, including its unique id.`

### 2. Get All Orders
- **URL:** `/`
- **Method:** `GET`
- **Success Response:**
  - **Code:** `(200  OK): A JSON array containing all existing orders.`

### 3. Get Order by ID
- **URL:** `/{id}`
- **Method:** `GET`
- **Success Response:**
  - **Code:** `(200  OK): The specific order document matching the provided id.`
- **Error Response:**
  - **Code:** `(404 Not Found): The specific order document matching the provided id.`

### 4. Update an Order

- **URL:** `/{id}`
- **Method:** `PUT`
- **Request Body (JSON): Include the fields you want to update.**
  ```json
   {
    "product": "Laptop Pro",
    "price": 28000.00,
    "status": "SHIPPED"
   }
  ```

- **Success Response:**
  - **Code:** `(200 OK): The updated order document.`

### 2. Delete an Order
- **URL:** `/{id}`
- **Method:** `DELETE`
- **Success Response:**
  - **Code:** `(200  OK): An empty response, confirming the deletion.`

---

## 🧪 API Testing with Postman

This repository includes a pre-configured Postman collection to facilitate testing of all CRUD endpoints.

- **File:** `MELI Order System - Sprint 1.postman_collection.json`
- **Location:** Project root directory.

To use it, simply import this file into your Postman application. It contains all the requests (Create, Read, Update, Delete) ready to be executed against your local running server.

---

## 💻 H2 Database Console

While the application is running, you can access the in-memory H2 database console to visually inspect the data.

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** (leave blank)

You can run SQL queries like `SELECT * FROM ORDERS;` to see the data you've manipulated via the API.

---

## 🌳 Environment Profiles

This project is configured to run in multiple environments using Spring Profiles. This allows for different configurations (e.g., database connections) depending on whether the application is running in development, testing, or production.

The following profiles are configured:

- **`dev` (Default):** Uses an in-memory H2 database with a web console. Ideal for local development and quick, interactive testing.
- **`prod`:** Connects to an external MySQL database. Designed for a production deployment, with sensitive credentials managed via environment variables.
- **`test`:** Uses a separate in-memory H2 database (`create-drop` mode). This profile is **automatically activated when running automated tests** (e.g., with JUnit) to ensure a clean and isolated environment for each test run.

### How to Activate `dev` or `prod` Profiles

You can activate a specific profile by setting a Java system property when running the application. *The `test` profile is activated automatically by the testing framework.*

#### Running from an IDE (VS Code `launch.json` or IntelliJ)
Configure your launch settings to include a VM argument:
- **`dev` Profile:** `-Dspring.profiles.active=dev`
- **`prod` Profile:** `-Dspring.profiles.active=prod`

#### Running from the Command Line
Pass the system property directly to the `java -jar` command:
```bash
# Example for running with the 'prod' profile on Windows
java -Dspring.profiles.active=prod -jar target/order-system-0.0.1-SNAPSHOT.jar

# Example for running with the 'prod' profile on Linux/Mac
java -Dspring.profiles.active=prod -jar target/order-system-0.0.1-SNAPSHOT.jar
```

---

## 🔐 Environment Variables for Production

When running with the prod profile, you must provide the database credentials as environment variables.

Setting Environment Variables

- On Windows (PowerShell):
```bash
$env:DB_USERNAME="your_mysql_user"
$env:DB_PASSWORD="your_mysql_password"
```

- On Linux/Mac:
```bash
export DB_USERNAME="your_mysql_user"
export DB_PASSWORD="your_mysql_password"
```

After setting these variables in your terminal, run the application from the same terminal session.