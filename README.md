# Library Management Console Application (Evolution)

## Project Context
Following the initial console application for library management (brief 1), the management wants to evolve the system to include data persistence and advanced functionalities. Your mission is to improve the existing application by integrating a relational database and using advanced Java concepts to optimize and extend the functionalities.

## Objectives
- Deepen understanding of OOP concepts in Java, particularly polymorphism and inheritance.
- Design and implement a relational database using PostgreSQL.
- Apply software design principles and UML modeling.
- Master Java 8 features, such as lambda expressions and the Stream API.
- Use Git for version control and JIRA for project management.

---

## General Application Description
The application allows users to manage a library of documents (Books, Magazines, Scientific Journals, University Theses) and supports CRUD operations, borrowing, and reservations. It also manages two types of users: Students and Professors, with adapted borrowing rights.

### Key Features
1. **Document Management**: CRUD for all types of documents.
2. **User Management**: CRUD for Students and Professors.
3. **Borrow Management**: Borrow and return documents.
4. **Reservation Management**: Reserve and cancel reservations for documents.
5. **Advanced Search**: Search documents by title or other attributes.

---

## Technologies Used
- **Java 8**
- **PostgreSQL** for database management.
- **JDBC** for database connection.
- **Git** for version control.
- **JIRA** for task management.
- **UML** for system modeling.

---

## Project Structure
The application follows a layered architecture:
1. **Presentation Layer**: Console interface (`ConsoleUI`).
2. **Business Layer**: Core logic for managing documents and users.
3. **Persistence Layer**: DAO for database access via JDBC.
4. **Utility Layer**: Classes like `DateUtils` for advanced date handling and `InputValidator` for user input validation.

---

## Installation and Usage Instructions

### Prerequisites
- Java 8 installed.
- PostgreSQL installed and running.
- PostgreSQL JDBC driver.
- Git (optional, for cloning the repository).

### Database Configuration
1. Create a PostgreSQL database named `library_db`.
2. Run the provided SQL script located in the `sql` directory to set up the database schema and initialize the data:
   ```bash
   psql -U <your-username> -d library_db -f path/to/sql_script.sql
   ```
3. Update the database credentials in the application's configuration file if necessary.

Running the Project
1. Compile and Run from Source

To run the project in development mode:

    Clone the repository:
    ```bash
    git clone https://github.com/JavaAura/Med_elBachiri_S1_B2_gestionBiblio_V2
    cd library-management
    ```

2. Compile the project:

```bash
javac -d bin -sourcepath src src/main/java/com/library/Main.java
```
3. Run the project:
```bash
java -cp bin com.library.Main
```

2. Run the JAR File

A pre-built JAR file is available for running the application without compiling.

    1- Navigate to the directory containing the JAR file:
    ```bash
    cd out/artifacts/app_jar/
    ```

    2- Run the JAR file using the following command:
    ```bash
    java -jar app.jar
    ```

### Future Improvements

    Graphical User Interface: Upgrade the console interface to a graphical one using JavaFX or Swing.
    User Authentication: Add login functionality with role-based access control.
    Cloud Database: Migrate the PostgreSQL database to a cloud solution for scalability.
    REST API: Expose the library management functionalities via a REST API for easier integration with other systems.
    Performance Optimization: Implement caching strategies for frequently accessed documents and users.

### Author and Contact

    Author: Mohammed El Bachiri
    Contact: med.el.bachiri.00@gmali.com
