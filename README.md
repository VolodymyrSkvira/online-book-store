<h1 align="center">
Online Book Store
</h1>

<p align="center">
    <img width="500" src="https://imgix.theurbanlist.com/content/article/best-bookstores-brisbane.png?auto=format,compress&w=520&h=390&fit=crop" alt="Book store logo">
</p>

### Introduction
This project represents an online book-selling service that lets users browse through the catalog of numerous books and later purchase them with the delivery to the desired location. The service itself allows for creation and management of books in the catalog, adding them to user's shopping cart and later converting them into the order.

### List of technologies used in the project 

- Java 17
- Spring Boot
- Spring Web
- Spring Data Jpa
- Spring Security
- Lombok
- Liquibase
- MySql 8
- MapStruct 1.5.5
- Swagger ui
- Docker

### Project functionality
`User Registration and Authentication:` Users can create accounts, log into the system, and access the service's functionality.

`Browsing through available books:` Users can check a catalog with available books and their info.

`Browsing through available categories:` Same as with the books, users can browse through available book categories.

`Managing shopping cart:` Users can manage their profile`s shopping cart (add, browse update and delete cart items).

`Managing orders:` Users can create orders based on their shopping cart and manage them.

`Admin functions:` Admins can manage books and categories in the inventory(create, update, delete). 


### List of available endpoints
#### Authentication controller

| Request type | Endpoint                     | Role  | Description                                                          |
|--------------|------------------------------|-------|----------------------------------------------------------------------|
| POST         | /register                    | ALL   | New user registration                                                |
| POST         | /login                       | ALL   | Receive access to user's profile                                     |

#### Book controller

| Request type | Endpoint      | Role  | Description                               |
|--------------|---------------|-------|-------------------------------------------|
| GET          | /books        | USER  | Receive a list of available books         |
| GET          | /books/{id}   | USER  | Get a book by specific id                 |
| GET          | /books/search | ALL   | Update info for a book with a specific id |
| POST         | /books        | ADMIN | Create a new book in the catalogue        |
| PUT          | /books/{id}   | ADMIN | Update a book by ID                       |
| DELETE       | /books/{id}   | ADMIN | Delete a book by ID                       |

#### Category controller

| Request type | Endpoint               | Role  | Description                            |
|--------------|------------------------|-------|----------------------------------------|
| GET          | /categories            | USER  | Receive a list of available categories |
| GET          | /categories/{id}       | USER  | Get a category by specific id          |
| GET          | /categories/{id}/books | USER  | Receive a list of books by category id |
| POST         | /categories            | ADMIN | Create a new category                  |
| PUT          | /categories/{id}       | ADMIN | Update a category by ID                |
| DELETE       | /categories/{id}       | ADMIN | Delete a category by ID                |

#### Shopping cart controller

| Request type | Endpoint               | Role | Description                              |
|--------------|------------------------|------|------------------------------------------|
| GET          | /cart                  | USER | Receive current user's shopping cart     |
| POST         | /cart                  | USER | Add book to current user's shopping cart |
| PUT          | /cart/cart-items/{id}  | USER | Update cart item by given id             |
| DELETE       | /cart/cart-items/{id}  | USER | Delete cart item by given id             |

#### Order controller

| Request type | Endpoint                          | Role  | Description                                |
|--------------|-----------------------------------|-------|--------------------------------------------|
| GET          | /orders                           | USER  | Receive user's order history               |
| GET          | /orders/{id}/items                | USER  | Receive order items by order id            |
| GET          | /orders/{orderId}/items/{itemId}  | USER  | Receive order item by order id and item id |
| POST         | /orders                           | USER  | Allows user to place an order              |
| PATCH        | /orders/{id}                      | ADMIN | Update the status of an existing order     |

### Project launch guide
The following steps are required in order to set up the project on your device:
1. You should have an installed docker instance on your platform
2. Clone the GitHub repository
3. Create a new `.env` file with the necessary environment variables
4. Run `mvn clean package` command in the console
5. Run `docker-compose up --build` command to build and start the Docker containers
6. The application should now be running at `http://localhost:8081`

Will be glad to receive any feedback regarding the project to this email: volodymyrskvira@gmail.com!
