# Hospital Management System (HMS) – Console Edition

## Overview

Simple **Java console application** for managing patients and appointments — **no database, no SQL, fully in-memory**.

Perfect for learning:
- Object-Oriented Programming
- Java Collections (`ArrayList`)
- Input validation
- Date & time handling (`java.time`)
- Clean console user interface design

**Important:** All data is stored only in memory → everything is lost when the program exits.

## Features

- Add / View / Update / Delete / Search patients
- Schedule and view appointments (linked to patients)
- Strong input validation (age range, required fields, date format…)
- Auto-incrementing IDs
- Partial name search for patients
- Deleting a patient also removes their appointments (cascade)
- Clear, formatted console output with success/error messages

## Tech Stack

- Language: **Java 8+** (Core + Collections + java.time)
- Data storage: In-memory (`ArrayList<Patient>`, `ArrayList<Appointment>`)
- Input: `java.util.Scanner`
- Date/Time: `java.time.LocalDateTime`
- IDE: VS Code, IntelliJ IDEA, etc.

## Main Classes

- `Patient` – stores patient information
- `Appointment` – stores appointment details
- `hms` – contains menu logic + all operations

## How to Run

1. Save the complete code in one file: `hms.java`

2. Compile:
   ```bash
   javac hms.java
3. Run:
   ```bash
   java hms

## Sample Run
```bash
🏥 Welcome to Hospital Management System
========================================

📋 Main Menu:
1. Add Patient
2. View All Patients
3. Update Patient
4. Delete Patient
5. Search Patients
6. Add Appointment
7. View All Appointments
8. Exit

Enter your choice (1-8): 1

--- Add New Patient ---
Name: Priya Sharma
Age: 28
Gender (Male/Female/Other): Female
Phone: 9123456789

✅ Patient added successfully! ID: 1
----------------------------------------------
Enter your choice (1-8): 2

--- All Patients ---
ID | Name              | Age | Gender   | Phone
-------------------------------------------------
1  | Priya Sharma      | 28  | Female   | 9123456789
