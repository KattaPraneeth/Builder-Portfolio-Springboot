# 🏗️ Builder Portfolio - Spring Boot Application

A simple project management system built using **Spring Boot** and **PostgreSQL**. This application allows for managing users and projects with different roles like `ADMIN`, `BUILDER`, and `CLIENT`.

## 🚀 Features

* User Management with role-based structure (Admin, Builder, Client)
* Project tracking with status management (Upcoming, In Progress, Completed)
* Input validations, custom exception handling, and logging
* Layered MVC architecture with DTOs and validators

## 🧰 Tech Stack

* Java 17+
* Spring Boot
* PostgreSQL
* Maven
* JPA/Hibernate
* JUnit 5 & Mockito (for unit testing)

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/KattaPraneeth/Builder-Portfolio-Springboot.git
cd Builder-Portfolio-Springboot
```

### 2. Configure Database Credentials

Open the `src/main/resources/application.properties` file and update the following properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Set Up the Database Tables (Optional)

Tables will be auto-created on the first run.
If you'd like to create them manually:

* Open the file `src/queries.sql`
* Run the SQL commands one by one using the `psql` shell or any PostgreSQL client.

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

You can run the app using your IDE or the command line:

```bash
mvn spring-boot:run
```

---

## 🧪 Running Tests

The project includes unit tests for key service layers using JUnit 5 and Mockito. To run all tests:

```bash
mvn test
```

---

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/praneeth/
│   │   ├── Controller/
│   │   ├── DTO/
│   │   ├── Entity/
│   │   ├── Enum/
│   │   ├── Exceptions/
│   │   ├── Repository/
│   │   ├── Service/
│   │   |── Validators/
|   |   └── ExceptionHandler/
│   └── resources/
│       └── application.properties
├── test/
│   └── java/com/praneeth/ServiceTests/
│       └── UserServiceTest.java
└── queries.sql
```

---

## 📬 Contact

For questions or suggestions, please open an issue or reach out via [GitHub](https://github.com/KattaPraneeth).
