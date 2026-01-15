import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hms {

    // Patient class to hold data
    static class Patient {
        private static int nextId = 1;
        private int id;
        private String name;
        private int age;
        private String gender;
        private String phone;

        public Patient(String name, int age, String gender, String phone) {
            this.id = nextId++;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return String.format("%-4d | %-20s | %-4d | %-8s | %s",
                    id, name, age, gender, phone);
        }
    }

    // In-memory storage
    private static final List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=====================================");
            System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Add New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Update Patient Phone");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patient by Name");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    updatePhone(scanner);
                    break;
                case 4:
                    deletePatient(scanner);
                    break;
                case 5:
                    searchByName(scanner);
                    break;
                case 6:
                    System.out.println("\nThank you for using Hospital Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please select 1 to 6.");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age! Patient not added.");
            return;
        }

        System.out.print("Enter gender (Male/Female/Other): ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        Patient patient = new Patient(name, age, gender, phone);
        patients.add(patient);
        System.out.println("\nPatient added successfully!");
        System.out.println("Assigned ID: " + patient.getId());
    }

    private static void viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients registered yet.");
            return;
        }

        System.out.println("\nID   | Name                 | Age  | Gender   | Phone");
        System.out.println("------------------------------------------------------------");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    private static void updatePhone(Scanner scanner) {
        System.out.print("Enter patient ID to update: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        Patient patient = findPatientById(id);
        if (patient == null) {
            System.out.println("Patient with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine().trim();

        patient.setPhone(newPhone);
        System.out.println("Phone number updated successfully!");
    }

    private static void deletePatient(Scanner scanner) {
        System.out.print("Enter patient ID to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        Patient patient = findPatientById(id);
        if (patient == null) {
            System.out.println("Patient with ID " + id + " not found.");
            return;
        }

        patients.remove(patient);
        System.out.println("Patient " + patient.getName() + " (ID: " + id + ") deleted successfully.");
    }

    private static void searchByName(Scanner scanner) {
        System.out.print("Enter name (or part of name) to search: ");
        String searchTerm = scanner.nextLine().trim().toLowerCase();

        List<Patient> results = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getName().toLowerCase().contains(searchTerm)) {
                results.add(p);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No patients found with name containing: " + searchTerm);
            return;
        }

        System.out.println("\nSearch results:");
        System.out.println("ID   | Name                 | Age  | Gender   | Phone");
        System.out.println("------------------------------------------------------------");
        for (Patient p : results) {
            System.out.println(p);
        }
    }

    private static Patient findPatientById(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
