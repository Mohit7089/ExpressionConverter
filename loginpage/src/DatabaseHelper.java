import java.sql.*;


public class DatabaseHelper {
    
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "root"; 
    private static final String PASSWORD = "******"; 

    // Load MySQL Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to validate user login
    public static boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
