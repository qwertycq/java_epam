package chapter13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/weatherdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getTestConnection() throws SQLException {
        String testUrl = "jdbc:postgresql://localhost:5432/weatherdb_test";
        String user = "postgres";
        String password = "pass";
        return DriverManager.getConnection(testUrl, user, password);
    }
}
