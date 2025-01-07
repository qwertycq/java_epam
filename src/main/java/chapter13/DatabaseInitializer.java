package chapter13;

import java.sql.*;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS region (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "area DOUBLE, " +
                    "inhabitant_type_id INT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS inhabitant_type (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "language VARCHAR(255))");

            stmt.execute("CREATE TABLE IF NOT EXISTS weather (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "region_id INT, " +
                    "date DATE, " +
                    "temperature DOUBLE, " +
                    "precipitation VARCHAR(255), " +
                    "FOREIGN KEY(region_id) REFERENCES region(id))");

            stmt.execute("INSERT INTO region (name, area, inhabitant_type_id) VALUES " +
                    "('Санкт-Петербург', 143.0, 1), " +
                    "('Лондон', 2511.0, 2), " +
                    "('Москва', 2561.0, 1), " +
                    "('Нью-Йорк', 783.8, 2), " +
                    "('Париж', 105.4, 2)");

            stmt.execute("INSERT INTO inhabitant_type (language) VALUES " +
                    "('Русский'), " +
                    "('Английский'), " +
                    "('Французский')");

            stmt.execute("INSERT INTO weather (region_id, date, temperature, precipitation) VALUES " +
                    "(1, '2025-01-01', -5.0, 'снег'), " +
                    "(2, '2025-01-02', 0.0, 'дождь'), " +
                    "(1, '2025-01-02', -3.0, 'снег'), " +
                    "(3, '2025-01-03', -8.0, 'снег'), " +
                    "(4, '2025-01-01', 2.0, 'дождь'), " +
                    "(4, '2025-01-02', 1.5, 'дождь'), " +
                    "(5, '2025-01-01', 5.0, 'ясно'), " +
                    "(5, '2025-01-02', 7.0, 'ясно'), " +
                    "(2, '2025-01-03', 3.0, 'дождь'), " +
                    "(3, '2025-01-04', -6.0, 'снег'), " +
                    "(1, '2025-01-03', -4.0, 'снег')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
