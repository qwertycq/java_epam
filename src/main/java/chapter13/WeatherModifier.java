package chapter13;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherModifier {

    public void addWeatherData(int regionId, Date date, double temperature, String precipitation) {
        String query = "INSERT INTO weather (region_id, date, temperature, precipitation) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, regionId);
            stmt.setDate(2, date);
            stmt.setDouble(3, temperature);
            stmt.setString(4, precipitation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
