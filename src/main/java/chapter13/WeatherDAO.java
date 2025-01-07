package chapter13;

import java.sql.*;

public class WeatherDAO {

    private Connection conn;

    public WeatherDAO(Connection conn) {
        this.conn = conn;
    }

    public void getWeatherByRegion(String regionName) {
        String query = "SELECT w.date, w.temperature, w.precipitation " +
                "FROM weather w JOIN region r ON w.region_id = r.id " +
                "WHERE r.name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, regionName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Date: %s, Temp: %.2f, Precipitation: %s%n",
                        rs.getDate("date"), rs.getDouble("temperature"), rs.getString("precipitation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSnowDates(String regionName, double maxTemperature) {
        String query = "SELECT w.date " +
                "FROM weather w JOIN region r ON w.region_id = r.id " +
                "WHERE r.name = ? AND w.precipitation = 'снег' AND w.temperature < ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, regionName);
            stmt.setDouble(2, maxTemperature);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Date: " + rs.getDate("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getWeatherByLanguage(String language) {
        String query = "SELECT r.name, w.date, w.temperature, w.precipitation " +
                "FROM weather w " +
                "JOIN region r ON w.region_id = r.id " +
                "JOIN inhabitant_type i ON r.inhabitant_type_id = i.id " +
                "WHERE i.language = ? AND w.date >= CURRENT_DATE - 7";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, language);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Region: %s, Date: %s, Temp: %.2f, Precipitation: %s%n",
                        rs.getString("name"), rs.getDate("date"), rs.getDouble("temperature"), rs.getString("precipitation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAverageTemperature(double minArea) {
        String query = "SELECT r.name, AVG(w.temperature) AS avg_temp " +
                "FROM weather w " +
                "JOIN region r ON w.region_id = r.id " +
                "WHERE r.area > ? AND w.date >= CURRENT_DATE - 7 " +
                "GROUP BY r.name";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, minArea);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Region: %s, Avg Temp: %.2f%n",
                        rs.getString("name"), rs.getDouble("avg_temp"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
