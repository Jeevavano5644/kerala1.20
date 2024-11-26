package kerala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbcode {
    private Connection con;

    public dbcode() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		   System.out.println("Driver Connected");
		   con = DriverManager.getConnection("jdbc:mysql://localhost/jeeva", "root", "jeeva007");
    }

    public int insert(String name, long mobileNumber, String email, String address) {
        String query = "INSERT INTO contact (Name, Mobile_Number, Email, Address) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setLong(2, mobileNumber);
            ps.setString(3, email);
            ps.setString(4, address);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
            return 0; 
        }
    }

    // Close Connection
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
