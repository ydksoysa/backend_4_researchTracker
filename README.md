
# ReactAuth Backend

This is a **Spring Boot** backend project that provides authentication and authorization services for a React frontend. It supports user registration, login, role-based access, and JWT token-based authentication.

---

## Features

- User Registration (restricted for certain roles like ADMIN and PI)
- Login with JWT token generation
- Hardcoded login for ADMIN and PI accounts
- Role-based access control
- Password encryption with BCrypt
- Database support: H2 (in-memory) and MySQL
- Spring Security integration

---

## Technology Stack

- **Backend Framework:** Spring Boot 3.5.7
- **Language:** Java 21
- **Database:** H2 (runtime) / MySQL (runtime)
- **Authentication:** JWT (JSON Web Token)
- **Security:** Spring Security
- **ORM:** Spring Data JPA
- **Dependency Injection & Utilities:** Lombok
- **Build Tool:** Maven

---

## Getting Started

### Prerequisites

- Java 21
- Maven 3.8+
- MySQL (if using MySQL database)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

---

## Configuration

1. **Database Configuration**

   - H2 (default, in-memory, no configuration required)
   - MySQL configuration (add to `application.properties`):

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/reactauth_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

2. **JWT Configuration**

   - Secret key and expiration time can be set in `application.properties`:

     ```properties
     jwt.secret=YourSecretKey
     jwt.expirationMs=3600000
     ```

---

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/ydksoysa/backend_4_researchTracker.git
Navigate to the project folder:

bash
Copy code
cd reactauth
Build the project:

bash
Copy code
mvn clean install
Run the project:

bash
Copy code
mvn spring-boot:run
