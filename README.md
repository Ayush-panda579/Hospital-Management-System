# Hospital Management System (HMS)

## Overview
The Hospital Management System (HMS) is a Java-based backend application designed to manage patient records and appointments efficiently. The system allows CRUD operations on patients and appointments, ensuring data consistency and improved backend performance through optimized SQL queries.

## Features
- Add, update, view, and delete patient records.
- Schedule, update, and manage appointments.
- Input validation to prevent invalid data entries.
- Relational database design for efficient data storage.
- Optimized SQL queries for faster backend operations.

## Tech Stack
- **Programming Language:** Java (Core Java, OOP)
- **Database:** MySQL
- **Tools:** VS Code / IntelliJ IDEA, Git, GitHub
- **Concepts Applied:** Object-Oriented Design, CRUD Operations, SQL Optimization

## Database Schema
### Patients Table
```sql
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10),
    phone VARCHAR(15)
);
