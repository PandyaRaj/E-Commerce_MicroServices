<<<<<<< HEAD
# E-commerce Microservice Application

This is a basic e-commerce application built using microservices architecture with Spring Framework in Java. The application is containerized with Docker and uses Kafka for asynchronous communication.

## Functional Requirements

- **Frontend**: A simple user interface to browse products, view details, and add them to a cart. Once a product is purchased, the transaction details are displayed and the cart is emptied.
- **Products Service**: Manages product information including name, description, price, image id, and stock availability. It has endpoints to create, delete, and fetch products.
- **Admin Service**: Manages admin operations such as creating, deleting, viewing, and updating products. Admins can also view all orders. Operations can be performed through Postman.
- **Orders Service**: Processes customer orders. This includes receiving product selections and quantities, calculating total price, and updating product stock.
- **Image Service**: Retrieves product images based on image Id.

## Technical Requirements

- **Microservices**: Separate, independent microservices for Products, Orders, Admin, and Image.
- **Containerization**: Docker containers for each microservice and their respective databases.
- **API Gateway**: Manages external requests and routes them to appropriate microservices.
- **Database**: Separate Docker containers for each microservice's database. PostgreSQL is used as the database.
- **Communication**: RESTful APIs for communication between the API Gateway and microservices. Kafka is used for asynchronous communication between microservices when necessary.

Meeting video refer - **meeting-presentation.mp4**

## Database Schemas

- **Products Table**:
  - id (INT PRIMARY KEY)
  - name (VARCHAR(255) NOT NULL)
  - description (TEXT)
  - price (DECIMAL(10,2) NOT NULL)
  - stock (INT NOT NULL)
  - image_id (VARCHAR(50) NOT NULL)
- **Orders Table**:
  - id (INT PRIMARY KEY)
  - order_date (DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)
  - total_price (DECIMAL(10,2) NOT NULL)
- **Images Table**:
  - id (VARCHAR(50) PRIMARY KEY)
  - name (VARCHAR(255) NOT NULL)
  - mime_type (VARCHAR(50) NOT NULL)

## Next JS UI
![image](https://github.com/user-attachments/assets/ce6ca550-c563-4c2a-8d61-b85cb9ebe55c)
=======
E-commerce Microservices Application
Overview
This project is a basic e-commerce application developed using a microservices architecture with the Spring Framework in Java. The application is containerized using Docker, with inter-service communication facilitated by REST APIs and Kafka for asynchronous messaging. The application consists of multiple microservices, each handling a specific aspect of the e-commerce platform.

Project Members
Umang Patel 
Raj Pandya 
Microservices Overview
The application consists of the following microservices:

Products Service: Manages product information such as name, description, price, and stock availability.
Orders Service: Handles customer orders, calculates total prices, and updates product stock.
Admin Service: Manages administrative operations including product management and viewing orders.
Image Service: Retrieves product images based on the image ID.

### MicroServices
1. Products Service: Manages product information and inventory.
2. Admin Service: Provides administrative functionalities.
3. Orders Service: Handles order processing and management.
4. Image Service: Manages product images.

### Architecture
![EcommerceMicroservice](https://github.com/user-attachments/assets/30dc3288-2249-4d3b-978f-ad653498980a)


### Technical stack
1. Database: PostgreSQL/H2 Database
2. Framework: Spring Boot
3. Message Broker: Apache Kafka
4. API Gateway: Spring Cloud Gateway
5. Frontend: React.js



Features

Frontend
    Browse products, view details, and add items to a cart.
    Display transaction details and empty the cart after purchase.

Products Service
    Manage product information including name, description, price, image ID, and stock availability.
    Endpoints for creating, deleting, and fetching products.

Admin Service
    Manage admin operations such as creating, deleting, viewing, and updating products.
    View all orders.
    Optionally, perform operations through an admin dashboard.

Orders Service
    Process customer orders, calculate total price, and update product stock.

Image Service
    Retrieve product images based on image ID.

Technical Requirements

Microservices
    Developed independent microservices for Products, Orders, Admin, and Image using the Spring Framework.

Containerization
    Each microservice and its respective database are containerized using Docker.

API Gateway
    An API Gateway is implemented to manage external requests and route them to the appropriate microservices.

Database
    Separate Docker containers for each microservice's database (PostgreSQL).
    Defined database schemas for Products, Orders, and Images.

Communication
    RESTful APIs are used for primary communication between the API Gateway and microservices.
    Kafka is implemented for asynchronous communication where necessary, such as order processing and product deletion.

Database Schemas

Products Table
    id (INT PRIMARY KEY)
    name (VARCHAR(255) NOT NULL)
    description (TEXT)
    price (DECIMAL(10,2) NOT NULL)
    stock (INT NOT NULL)
    image_id (VARCHAR(50) NOT NULL)

Orders Table
    id (INT PRIMARY KEY)
    order_date (DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)
    total_price (DECIMAL(10,2) NOT NULL)
    Foreign key references to Products.id for ordered products and their quantities.

Images Table
    Optional table for storing image metadata such as name and MIME type.

Communication between Microservices

REST APIs: Primary method for most interactions.
Kafka:
    Order Processing: Orders Service publishes "OrderPlacedEvent" to Kafka; Products Service updates stock asynchronously.

Product Deletion: Admin Service sends a Kafka event to delete product images.

Frontend and Cart
A basic UI is developed using the React framework.
Shopping cart functionality is handled entirely on the frontend without backend persistence.
Deployment
Docker Compose is used to deploy all microservices and their databases as a single unit.

How to Run
    Prerequisites
    Docker and Docker Compose installed.


Java 17 and Maven installed.

Steps
Clone the repository:

bash

Copy code
git clone https://github.com/PandyaRaj/E-Commerce_MicroServices.git
cd e-commerce-microservices
Build the project:

bash
Copy code
mvn clean install
Run Docker Compose:

bash
Copy code
docker-compose up --build
Access the application:

Products Service: http://localhost:<port>/products
Orders Service: http://localhost:<port>/orders
Admin Service: http://localhost:<port>/admin
Image Service: http://localhost:<port>/images
Access API Documentation:

Swagger documentation can be accessed via http://localhost:<port>/swagger-ui.html for each service.
Testing with Postman
Import the provided Postman collection for easier testing of the endpoints.
Use the username: user and password: password for accessing secured endpoints.

Contributors
Umang Patel 
Raj Pandya 

License
This project is licensed under the MIT License
>>>>>>> origin/main
