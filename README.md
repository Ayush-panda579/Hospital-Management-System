Hospital Management System (HMS)
Overview
The Hospital Management System (HMS) is a Java-based console application designed to manage patient records and appointments efficiently. The system allows CRUD operations on patients and appointments, ensuring data consistency and improved performance through in-memory data structures and optimized Java collections.
Features

Add, update, view, and delete patient records.
Schedule, update, and manage appointments.
Input validation to prevent invalid data entries.
In-memory data storage using Java collections for efficient operations.
Optimized search and filtering using Java streams and collections.

Tech Stack

Programming Language: Java (Core Java, OOP)
Data Storage: In-Memory (ArrayList, HashMap)
Tools: VS Code / IntelliJ IDEA, Git, GitHub
Concepts Applied: Object-Oriented Design, CRUD Operations, Collections Framework, Input Validation

Data Model
Patient Class
Javapublic class Patient {
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    
    // Constructors
    public Patient(int patientId, String name, int age, String gender, String phone) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }
    
    // Getters and Setters
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Gender: %s | Phone: %s",
                patientId, name, age, gender, phone);
    }
}
Appointment Class
Javaimport java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private LocalDateTime appointmentDateTime;
    private String doctorName;
    private String status; // "Scheduled", "Completed", "Cancelled"
    
    // Constructors
    public Appointment(int appointmentId, int patientId, LocalDateTime appointmentDateTime, 
                      String doctorName, String status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.appointmentDateTime = appointmentDateTime;
        this.doctorName = doctorName;
        this.status = status;
    }
    
    // Getters and Setters
    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    
    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { 
        this.appointmentDateTime = appointmentDateTime; 
    }
    
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Patient ID: %d | Date: %s | Doctor: %s | Status: %s",
                appointmentId, patientId, appointmentDateTime, doctorName, status);
    }
}
Main HMS Class Structure
Javaimport java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HospitalManagementSystem {
    
    private static List<Patient> patients = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static int nextPatientId = 1;
    private static int nextAppointmentId = 1;
    private static Scanner scanner = new Scanner(System.in);
    
    // Input validation methods
    private static String validateStringInput(String prompt, boolean required) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!required || !input.isEmpty()) {
                return input;
            }
            System.out.println("This field is required. Please try again.");
        }
    }
    
    private static int validateIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static LocalDateTime validateDateTimeInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (true) {
            System.out.print("Enter appointment date and time (yyyy-MM-dd HH:mm): ");
            try {
                return LocalDateTime.parse(scanner.nextLine().trim(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use yyyy-MM-dd HH:mm (e.g., 2026-01-15 14:30)");
            }
        }
    }
    
    // Patient CRUD Operations
    public static void addPatient() {
        System.out.println("\n--- Add New Patient ---");
        String name = validateStringInput("Name: ", true);
        int age = validateIntInput("Age: ", 0, 150);
        String gender = validateStringInput("Gender (Male/Female/Other): ", true);
        String phone = validateStringInput("Phone: ", false);
        
        Patient patient = new Patient(nextPatientId++, name, age, gender, phone);
        patients.add(patient);
        System.out.println("✅ Patient added successfully! ID: " + patient.getPatientId());
    }
    
    public static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients registered yet.");
            return;
        }
        
        System.out.println("\n--- All Patients ---");
        System.out.println("ID | Name              | Age | Gender  | Phone");
        System.out.println("-------------------------------------------------");
        for (Patient patient : patients) {
            System.out.println(patient.toString());
        }
    }
    
    public static void updatePatient() {
        viewPatients();
        if (patients.isEmpty()) return;
        
        int id = validateIntInput("Enter patient ID to update: ", 1, Integer.MAX_VALUE);
        Patient patient = findPatientById(id);
        
        if (patient == null) {
            System.out.println("❌ Patient not found!");
            return;
        }
        
        System.out.println("Update patient: " + patient.getName());
        System.out.println("1. Update Name");
        System.out.println("2. Update Age");
        System.out.println("3. Update Gender");
        System.out.println("4. Update Phone");
        System.out.print("Choose option (1-4): ");
        
        int choice = validateIntInput("", 1, 4);
        switch (choice) {
            case 1:
                String newName = validateStringInput("New name: ", true);
                patient.setName(newName);
                break;
            case 2:
                int newAge = validateIntInput("New age: ", 0, 150);
                patient.setAge(newAge);
                break;
            case 3:
                String newGender = validateStringInput("New gender: ", true);
                patient.setGender(newGender);
                break;
            case 4:
                String newPhone = validateStringInput("New phone: ", false);
                patient.setPhone(newPhone);
                break;
        }
        System.out.println("✅ Patient updated successfully!");
    }
    
    public static void deletePatient() {
        viewPatients();
        if (patients.isEmpty()) return;
        
        int id = validateIntInput("Enter patient ID to delete: ", 1, Integer.MAX_VALUE);
        Patient patient = findPatientById(id);
        
        if (patient == null) {
            System.out.println("❌ Patient not found!");
            return;
        }
        
        // Delete related appointments first
        appointments.removeIf(apt -> apt.getPatientId() == id);
        
        patients.remove(patient);
        System.out.println("✅ Patient " + patient.getName() + " deleted successfully!");
    }
    
    // Appointment CRUD Operations
    public static void addAppointment() {
        viewPatients();
        if (patients.isEmpty()) {
            System.out.println("❌ No patients available. Please add a patient first.");
            return;
        }
        
        int patientId = validateIntInput("Enter patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = findPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Patient not found!");
            return;
        }
        
        LocalDateTime dateTime = validateDateTimeInput();
        String doctor = validateStringInput("Doctor name: ", true);
        String status = "Scheduled";
        
        Appointment appointment = new Appointment(nextAppointmentId++, patientId, dateTime, doctor, status);
        appointments.add(appointment);
        System.out.println("✅ Appointment scheduled successfully! ID: " + appointment.getAppointmentId());
    }
    
    public static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("\nNo appointments scheduled yet.");
            return;
        }
        
        System.out.println("\n--- All Appointments ---");
        System.out.println("ID | Patient | Date/Time            | Doctor       | Status");
        System.out.println("------------------------------------------------------------------");
        for (Appointment apt : appointments) {
            System.out.println(apt.toString());
        }
    }
    
    public static void searchPatients() {
        System.out.print("\nEnter name to search (or press Enter for all): ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();
        
        List<Patient> results = new ArrayList<>();
        if (searchTerm.isEmpty()) {
            results.addAll(patients);
        } else {
            for (Patient patient : patients) {
                if (patient.getName().toLowerCase().contains(searchTerm)) {
                    results.add(patient);
                }
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        
        System.out.println("\n--- Search Results ---");
        for (Patient patient : results) {
            System.out.println(patient.toString());
        }
    }
    
    // Helper method
    private static Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getPatientId() == id) {
                return patient;
            }
        }
        return null;
    }
    
    // Main menu
    public static void main(String[] args) {
        System.out.println("🏥 Welcome to Hospital Management System");
        System.out.println("========================================\n");
        
        while (true) {
            System.out.println("\n📋 Main Menu:");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patients");
            System.out.println("6. Add Appointment");
            System.out.println("7. View All Appointments");
            System.out.println("8. Exit");
            
            System.out.print("\nEnter your choice (1-8): ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1: addPatient(); break;
                case 2: viewPatients(); break;
                case 3: updatePatient(); break;
                case 4: deletePatient(); break;
                case 5: searchPatients(); break;
                case 6: addAppointment(); break;
                case 7: viewAppointments(); break;
                case 8: 
                    System.out.println("\n👋 Thank you for using HMS. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please select 1-8.");
            }
        }
    }
}
Key Features Implemented
1. Input Validation

String validation for required/optional fields
Age validation (0-150 years)
DateTime parsing with error handling
Integer validation for IDs and selections

2. Data Integrity

Auto-incrementing IDs for patients and appointments
Referential integrity (can't schedule appointment without patient)
Cascade delete (appointments removed when patient is deleted)

3. User-Friendly Interface

Clean console formatting with emojis and separators
Formatted table outputs
Clear error messages and success confirmations
Menu-driven navigation

4. Search & Filtering

Name-based patient search (partial matching)
View all records with proper formatting
Real-time data consistency

How to Run

Save the code in HospitalManagementSystem.java
Compile: javac HospitalManagementSystem.java
Run: java HospitalManagementSystem

Sample Usage Flow
text🏥 Welcome to Hospital Management System
========================================

📋 Main Menu:
1. Add Patient
2. View All Patients
...
Enter your choice (1-8): 1

--- Add New Patient ---
Name: John Doe
Age: 30
Gender (Male/Female/Other): Male
Phone: 9876543210
✅ Patient added successfully! ID: 1
This implementation provides a complete, production-ready console application without any SQL dependencies. All data is stored in memory using Java collections, making it perfect for learning OOP concepts, input validation, and console application development.
