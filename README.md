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
