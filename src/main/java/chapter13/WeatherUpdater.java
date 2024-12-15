package chapter13;

import java.sql.*;

public class WeatherUpdater {
    private Connection connection;

    public WeatherUpdater() throws SQLException {
        this.connection = DBConnection.getConnection();
    }

    // Обновление температуры в базе данных
    public void updateTemperature(int regionId, Date date, int newTemperature) throws SQLException {
        String query = "UPDATE weather SET temperature = ? WHERE region_id = ? AND date = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newTemperature);
            stmt.setInt(2, regionId);
            stmt.setDate(3, date);
            stmt.executeUpdate();
        }
    }

    // Добавление новой записи о погоде
    public void addWeatherRecord(int regionId, Date date, int temperature, String precipitation) throws SQLException {
        String query = "INSERT INTO weather (region_id, date, temperature, precipitation) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, regionId);
            stmt.setDate(2, date);
            stmt.setInt(3, temperature);
            stmt.setString(4, precipitation);
            stmt.executeUpdate();
        }
    }
}
