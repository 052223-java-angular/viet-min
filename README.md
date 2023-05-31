# eShop Project

## Introduction

This is a Java-based command-line interface (CLI) eCommerce application. The application will be primarily built using Java and will utilize a PostgreSQL database to store product and user information.

## User Stories

- **As a user**, I want to register an account so that I can have a personalized shopping experience.
- **As a user**, I want to log in to my account so that I can access my shopping cart and order history.
- **As a user**, I want to browse through products only when logging in.
- **As a user**, I want to search for products by name, category, or price range so that I can find what I'm looking for.
- **As a user**, I want to add products to my shopping cart so that I can purchase them later.
- **As a user**, I want to modify the quantity or remove items from my cart so that I can make changes before finalizing the purchase.
- **As a user**, I want to check out and pay for my order securely so that my personal and financial information is safe.
- **As a user**, I want to review my order history so that I can keep track of my purchases.
- **As a user**, I want to rate and review products so that I can share my experience with other users.
- **As a user**, I want to view ratings and reviews from other users so that I can make informed buying decisions.


## Features

- Registration
    - Username:
      - 8-20 alphanumeric characters or underscores or dots but not starting or ending with an underscore or dot or having consecutive underscores or dots. 
      - must not be already in use.
    - Password:
      - at least 8 alphanumeric characters with at least one letter and one number.
      - salted and hashed.
- Log in
  - Must use correct combination of username and password.
- Browsing and searching for products
  - Must be logged in to use.
  - Search using:
    - product name.
    - product price.
    - product category.
- Adding products to a shopping cart
    - product must be in stock.
    - quantity to add must not be negative or greater than stock.
- Modifying the shopping cart
  - delete unwanted product
  - change quantity of product
- Secure payment process
  - Payment method verifcation:
    - must have a valid Visa, MasterCard, American Express or Discover card number.
    - expiration date in MM/yyyy format and not be before current date.
    - sercurity code must be numbers of 3 or 4 digits.
  - Product stock verification:
    - must have enough in stock at time of checkout.
    - product stock temporily reduced during check to prevent multiple users from buying at the same time cause stock to fall below 0.
    - restock if any item quantity exceeds stock and payment fails.
- Order history
  - add orders to order history after a sucessful payment.
  - user can view their order history and items ordered.

- Product rating and reviewing
  - One user one review per product.
  - Review can only be modified by user who left the review.
  - View all reviews for a product.



## Tech Stacks

- **Java**: The main programming language used for building the application.
- **PostgreSQL**: Used as the database to store user, product, and order data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Log4j**: A logging utility for debugging purposes.
- **JDBC (Java Database Connectivity)**: An API for connecting and executing queries on the database.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **JUnit, Mockito, and PowerMock**: Used for unit and integration testing.
- **Git and GitHub**: Used for version control.
- **Project Lombok**: Automatic Resource Management, automatic generation of getters, setters, equals, hashCode and toString, and more!


## Project setup
  - Environment setup: https://github.com/052223-java-angular/wikis/blob/main/onboarding.md
  
## Project depencies:
  - junit: https://mvnrepository.com/artifact/junit/junit
  - mockito-junit-jupiter: https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter 
  - mockito-core: https://mvnrepository.com/artifact/org.mockito/mockito-core
  - postgresql: https://mvnrepository.com/artifact/org.postgresql/postgresql
  - lombok: https://mvnrepository.com/artifact/org.projectlombok/lombok
  - log4j-core: https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
  - jbcrypt: https://mvnrepository.com/artifact/org.mindrot/jbcrypt