# Student Management System

A robust, console-based Java application built to manage student records with persistent file storage, OOP architecture, custom exceptions, and multithreaded auto-saving.

## Features
- **Full CRUD Operations**: Add, View, Search, Update, and Delete student records.
- **Data Persistence**: Automatic serialization to `data/students.txt` in CSV format.
- **Asynchronous Auto-Saving**: Daemon thread persisting in-memory changes every 30 seconds.
- **Input Resilience**: Comprehensive defensive programming preventing crashes on malformed inputs.

## Java Concepts Used
- **OOP Architecture**: 
  - `Person` (Abstract base class for Abstraction & Encapsulation)
  - `Student` (Inheritance & Polymorphic runtime string representation)
  - `IStudentService` (Interface for loose coupling)
- **Collections Framework**: `HashMap<Integer, Student>` for $O(1)$ lookups and `ArrayList` for sorted sequential reads.
- **Exception Handling**: Custom `StudentNotFoundException` and try-catch blocks over `InputMismatchException` and `IOException`.
- **Multithreading**: `AutoSaveThread` implementing `Runnable` running as a background daemon thread.
- **File Handling**: `BufferedReader` and `BufferedWriter` streams.


