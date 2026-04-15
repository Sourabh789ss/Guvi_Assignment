Inventory & Order Management System
-------------------------------------
Inventory & Order Management System is a robust backend RESTful API designed to manage complex inventory levels, process customer orders, and streamline warehouse operations. Built with Spring Boot, it features secure authentication and automated documentation.

🚀 Features
-------------
Inventory Management: Real-time tracking of stock levels, product categories, and suppliers.

Order Processing: Automated workflow for creating, updating, and managing customer orders.

Secure Authentication: Integrated Spring Security with JWT (JSON Web Tokens) for stateless authentication.

Role-Based Access Control (RBAC): Granular permissions for Admin and Staff roles using @EnableMethodSecurity.

Interactive API Docs: Built-in Swagger UI and OpenAPI 3.0 integration for easy testing.

🛠️ Tech Stack
----------------
Framework: Spring Boot 3.x

Language: Java 17+

Security: Spring Security, JJWT (Java JWT)

Database: MS SQL Server (via JPA/Hibernate)

Documentation: Springdoc OpenAPI

Build Tool: Maven

📋 Prerequisites
------------------
JDK 17 or higher

Maven 3.6+

MS SQL Server instance

🛡️ Security Implementation
------------------------------
The system uses a custom JwtFilter to intercept requests. To access protected endpoints, include the token in your request header:

Key Components:

SecurityConfig: Configures the SecurityFilterChain to permit public access to Auth and Swagger paths while protecting business logic.

JwtUtil: Handles token generation, extraction, and validation using the HS256 algorithm.

📖 API Documentation
-----------------------
Once the application is running, you can explore and test the endpoints visually:

Swagger UI: http://localhost:8081/swagger-ui/index.html

OpenAPI JSON: http://localhost:8081/v3/api-docs

🛤️ Roadmap
------------
[ ] Integration with Email Service for Order Confirmations

[ ] Dashboard for Inventory Analytics

[ ] Multi-warehouse Support

📂 Project Structure
--------------------------
StockFlow/
├── src/
│   ├── main/
│   │   ├── java/com/stockflow
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── exception/
│   │   └── resources/
│   │       ├── application.properties


🧠 Key Concepts Demonstrated
-------------------------------
REST API design
CRUD operations
Layered architecture (Controller → Service → Repository)
DTO-based design
Exception handling
Scalable backend structure
Screenshot
<img width="1005" height="543" alt="image" src="https://github.com/user-attachments/assets/dc939e8e-f9fc-4e53-854b-97fc6b6471a0" />

