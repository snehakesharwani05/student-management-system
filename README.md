# Student Management System

## Project Description
A console-based Java application engineered to manage student academic records efficiently. The system provides complete CRUD functionality, handles robust input validation, manages persistent comma-separated text storage, and incorporates background thread scheduling for automated data persistence without blocking user interaction.

---

## Features
* **Full CRUD Operations**: Create, read, search, update, and delete student records with instant runtime reflection.
* **Persistent File Storage**: Automatically serializes records to `data/students.txt` and rehydrates state upon application launch.
* **Multithreaded Auto-Save**: Background daemon thread automatically saves unsaved records to disk every 30 seconds.
* **Resilient Input Handling**: Validates user inputs against datatype mismatches and handles missing entries using custom exceptions.
* **Menu-Driven CLI**: Clean console interface designed for straightforward navigation and error recovery.

---

## Technologies Used
* **Language**: Java (JDK 17+)
* **Development Environment**: Visual Studio Code
* **Version Control**: Git & GitHub

---

## Java Concepts Used
* **Object-Oriented Programming (OOP)**:
  * **Abstraction**: Abstract `Person` base class defining core identity attributes and contract methods.
  * **Inheritance**: `Student` extends `Person` to inherit and specialize state and behavior.
  * **Encapsulation**: Private fields protected with standard getters, setters, and state guards.
  * **Polymorphism**: Method overriding with `@Override public String getDetails()`.
  * **Interfaces**: `IStudentService` interface enforcing architectural loose coupling between the CLI and business logic.
  * **Keywords**: Explicit use of `this`, `super`, and `static` counters for instance tracking.
* **Collections Framework**:
  * `HashMap<Integer, Student>` for O(1) constant-time ID lookups, updates, and deletions.
  * `ArrayList<Student>` for dynamic list conversions and orderly screen printing.
* **Exception Handling**:
  * Custom checked exception: `StudentNotFoundException`.
  * Runtime error mitigation: `try-catch` blocks wrapping `InputMismatchException` and `IOException`.
* **File Handling**:
  * Low-overhead file streaming using `BufferedReader`, `BufferedWriter`, `FileReader`, and `FileWriter`.
* **Multithreading & Concurrency**:
  * Daemon thread implementation (`AutoSaveThread`) executing background tasks via `Runnable`.
  * `synchronized` method qualifiers safeguarding shared student memory structures from race conditions.

---

## How to Run the Project

### Prerequisites
* Java Development Kit (JDK 8 or higher, JDK 17+ recommended)
* Git installed on your system

### Steps

1. **Clone the Repository**
   ```bash
   git clone [https://github.com/snehakesharwani05/student-management-system.git](https://github.com/snehakesharwani05/student-management-system.git)
   cd student-management-system

2. **Compile the Source Files**
   ```bash
   javac -d bin src/model/*.java src/service/*.java src/exception/*.java src/Main.java

3. **Run the Application**
   ```bash
   java -cp bin Main

---

## Future Improvements
* Transition flat-file storage (students.txt) to a relational SQL database using JDBC.
* Build an interactive desktop interface using JavaFX or Swing.
* Add course enrollment tracking and GPA calculation utilities.
* Implement role-based access control (Admin vs. Student view permissions).
