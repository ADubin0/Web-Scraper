import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLDatabaseConnection {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
    	System.out.println("Username:");
    	Scanner sc = new Scanner (System.in);
    	String username = sc.nextLine();
    	System.out.println("Password:");
    	String password = sc.nextLine();
        String connectionUrl =
                "jdbc:sqlserver://DESKTOP-4MPP399.database.windows.net:1433;"
                        + "database=AdventureWorks;"
                        + "user="+ username + "@DESKTOP-4MPP399;"
                        + "password=" + password + ";"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}