import java.sql.*;
import java.util.Scanner;

public class hms {

    static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    static final String USER = "root";
    static final String PASS = "your_password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // CREATE Patient
    public void addPatient(String name, int age, String gender, String phone) {
        String sql = "INSERT INTO patients(name, age, gender, phone) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, phone);
            int rows = pstmt.executeUpdate();
            System.out.println("Patient added successfully. Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ Patients
    public void listPatients() {
        String sql = "SELECT * FROM patients";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("patient_id") + ": " +
                    rs.getString("name") + ", Age: " +
                    rs.getInt("age") + ", Gender: " +
                    rs.getString("gender") + ", Phone: " +
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE Patient
    public void updatePatientPhone(int patientId, String newPhone) {
        String sql = "UPDATE patients SET phone = ? WHERE patient_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhone);
            pstmt.setInt(2, patientId);
            int rows = pstmt.executeUpdate();
            System.out.println("Patient updated successfully. Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE Patient
    public void deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            int rows = pstmt.executeUpdate();
            System.out.println("Patient deleted successfully. Rows affected: " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HospitalManagement hm = new HospitalManagement();
        Scanner sc = new Scanner(System.in);

        // Example usage
        hm.addPatient("Ayush Panda", 23, "Male", "9876543210");
        hm.listPatients();
        hm.updatePatientPhone(1, "9998887776");
        hm.deletePatient(1);
    }
}
