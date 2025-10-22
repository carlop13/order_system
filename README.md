# MELI Order System API - V2.0.0

![Java](https://img.shields.io/badge/Java-17-orange.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green.svg) ![Maven](https://img.shields.io/badge/Maven-blue.svg) ![Swagger](https://img.shields.io/badge/API-Swagger-blueviolet.svg)

This repository contains the source code for the MELI Order System, a RESTful API developed with Spring Boot. The project was created to provide a robust, configurable, and well-documented solution for an e-commerce order management system, addressing common challenges like multi-environment deployment and API validation.

**Current Version: 2.0.0** (Feature-complete with CRUD, advanced search, environment profiles, Swagger documentation, and automated testing).

---

## ✨ Key Features

- **Advanced Search:** The primary `GET` endpoint supports **pagination** and **sorting** for efficient data retrieval.
- **Full CRUD Functionality:** Complete Create, Read, Update, and Delete operations for orders.
- **Input Validation:** Incoming data is automatically validated to ensure data integrity.
- **Multi-Environment Support:** Uses **Spring Profiles** to manage distinct configurations for `dev`, `test`, and `prod` environments.
- **Automated API Documentation:** Integrated **Swagger (OpenAPI)** provides a live, interactive documentation UI for all endpoints.
- **Automated Testing:** Includes a suite of **JUnit 5** integration tests to ensure API reliability.
- **Secure Configuration:** Sensitive data (like database passwords) is managed via **environment variables**, not hardcoded in the source.

---

## 🛠️ Technologies & Dependencies

- **Framework:** Spring Boot 3.2.5
- **Language:** Java 17
- **Build Tool:** Apache Maven
- **Data Persistence:** Spring Data JPA, Hibernate
- **Databases:**
  - H2 (for `dev` and `test` profiles)
  - MySQL (for `prod` profile)
- **API Documentation:** Springdoc OpenAPI (Swagger)
- **Validation:** Spring Boot Starter Validation (JSR 303)
- **Testing:** Spring Boot Starter Test (JUnit 5, MockMvc)
- **Utilities:** Lombok

---

## 🚀 Getting Started

Follow these instructions to get the project running on your local machine.

### Prerequisites

- Java Development Kit (JDK) 17 or higher.
- Apache Maven.
- A running instance of MySQL Server (for the `prod` profile).
- [Postman](https://www.postman.com/downloads/) (optional, for manual testing).

### Installation & Execution

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/carlop13/order_system.git
    cd order_system
    ```

2.  **Set up the Production Database (Optional, for `prod` profile):**
    - Connect to your MySQL instance and create the database:
      ```sql
      CREATE DATABASE IF NOT EXISTS meli_orders_prod;
      ```
    - The table will be created automatically by JPA upon first launch.

3.  **Build the project:**
    Open a terminal in the project's root directory and run:
    ```bash
    .\mvnw.cmd clean package
    ```

4.  **Run the application:**
    The application can be launched with different profiles. See the **Environment Profiles** section below for details on how to activate them. By default, it will run with the `dev` profile.
    ```bash
    java -jar target/order-system-0.0.1-SNAPSHOT.jar
    ```
    The server will start on `http://localhost:8080`.

---

## 📖 API Documentation (Swagger)

This API is self-documented using Swagger/OpenAPI. Once the application is running, you can access the interactive UI to view and test all endpoints.

- **Swagger UI URL:** `http://localhost:8080/swagger-ui.html`

---

## 🛰️ API Endpoints

All endpoints are available under the base path `/api/orders`.

### 1. Get All Orders (with Pagination & Sorting)

- **URL:** `/`
- **Method:** `GET`
- **Query Parameters:**
  - `page`: The page number to retrieve (starts at 0).
  - `size`: The number of items per page.
  - `sort`: The field to sort by, optionally followed by `,asc` or `,desc` (e.g., `sort=customerName,desc`).
- **Success Response (`200 OK`):** A paginated JSON object.

### 2. Create an Order

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

### 5. Delete an Order
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