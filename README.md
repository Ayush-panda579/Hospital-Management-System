# Hospital Management System (HMS) – Console Edition

## Overview
The Hospital Management System (HMS) is a **Java console-based application** designed to manage patient records and appointments efficiently — **without using any database or SQL**.

All data is stored **in memory** using Java collections (`ArrayList`, etc.) and is lost when the program exits.  
Ideal for learning OOP, collections, input validation, date/time handling, and clean console UI design.

## Features
- Add, view, update, search, and delete **patient records**
- Schedule, view, and manage **appointments** (linked to patients)
- Strong **input validation** (age range, required fields, date format, etc.)
- Auto-incrementing IDs for patients and appointments
- Partial name search for patients
- Cascade deletion (deleting a patient removes their appointments)
- Clean, formatted console output with clear menus and feedback
- Friendly user experience with emojis and success/error messages

## Tech Stack
- **Language**: Java (Core Java + Collections Framework)
- **Data Storage**: In-memory (`ArrayList<Patient>`, `ArrayList<Appointment>`)
- **Date/Time Handling**: `java.time.LocalDateTime`
- **Input/Output**: `java.util.Scanner`
- **IDE / Tools**: VS Code, IntelliJ IDEA, Git, GitHub
- **Key Concepts Demonstrated**:
  - Object-Oriented Programming (classes, encapsulation)
  - CRUD operations
  - Input validation & error handling
  - Collections & searching/filtering
  - Method organization & clean code structure

## Data Model Summary

### Patient
- `patientId` (int, auto-increment)
- `name` (String)
- `age` (int, 0–150)
- `gender` (String)
- `phone` (String, optional)

### Appointment
- `appointmentId` (int, auto-increment)
- `patientId` (int, foreign key reference)
- `appointmentDateTime` (`LocalDateTime`)
- `doctorName` (String)
- `status` (String: Scheduled / Completed / Cancelled)

## How to Run

1. Save the code in a file named `HospitalManagementSystem.java`
2. Compile:
   ```bash
   javac HospitalManagementSystem.java
