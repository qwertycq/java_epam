package chapter13;

import java.sql.*;

public class WeatherQuery {
    private Connection connection;

    public WeatherQuery() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    // Вывод погоды для заданного региона
    public void getWeatherByRegion(String regionName) throws SQLException {
        String query = "SELECT w.date, w.temperature, w.precipitation " +
                "FROM weather w " +
                "JOIN regions r ON w.region_id = r.id " +
                "WHERE r.name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, regionName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Дата: " + rs.getDate("date") +
                        ", Температура: " + rs.getInt("temperature") +
                        ", Осадки: " + rs.getString("precipitation"));
            }
        }
    }

    public void getSnowyDaysWithLowTemp(String regionName, int tempThreshold) throws SQLException {
        String query = "SELECT w.date " +
                "FROM weather w " +
                "JOIN regions r ON w.region_id = r.id " +
                "WHERE r.name = ? AND w.precipitation = 'snow' AND w.temperature < ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, regionName);
            stmt.setInt(2, tempThreshold);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Дата: " + rs.getDate("date"));
            }
        }
    }

    public void getWeatherForLastWeek(String language) throws SQLException {
        String query = "SELECT w.date, w.temperature, w.precipitation, r.name " +
                "FROM weather w " +
                "JOIN regions r ON w.region_id = r.id " +
                "JOIN types_of_inhabitants t ON r.inhabitants_type_id = t.id " +
                "WHERE t.language = ? AND w.date > CURRENT_DATE - 7";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Регион: " + rs.getString("name") +
                        ", Дата: " + rs.getDate("date") +
                        ", Температура: " + rs.getInt("temperature") +
                        ", Осадки: " + rs.getString("precipitation"));
            }
        }
    }

    public void getAvgTemperatureForLargeRegions(int areaThreshold) throws SQLException {
        String query = "SELECT AVG(w.temperature) AS avg_temp " +
                "FROM weather w " +
                "JOIN regions r ON w.region_id = r.id " +
                "WHERE r.area > ? AND w.date > CURRENT_DATE - 7";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, areaThreshold);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Средняя температура: " + rs.getInt("avg_temp"));
            }
        }
    }
}
