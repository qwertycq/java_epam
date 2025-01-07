package chapter13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:h2:mem:weatherdb;DB_CLOSE_DELAY=-1";  // В памяти
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getTestConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./test_weatherdb", USER, PASSWORD);
    }
}
