package chapter13;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherDAOTest {

    private Connection connection;
    private WeatherDAO weatherDAO;

    @BeforeAll
    void setUpDatabase() throws SQLException {
        connection = DatabaseConnection.getTestConnection();
        weatherDAO = new WeatherDAO(connection);

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS weather, region, inhabitant_type CASCADE");

            stmt.execute("CREATE TABLE inhabitant_type (id SERIAL PRIMARY KEY, name VARCHAR(100), language VARCHAR(100))");
            stmt.execute("CREATE TABLE region (id SERIAL PRIMARY KEY, name VARCHAR(100), area DOUBLE PRECISION, inhabitant_type_id INT REFERENCES inhabitant_type(id))");
            stmt.execute("CREATE TABLE weather (id SERIAL PRIMARY KEY, region_id INT REFERENCES region(id), date DATE, temperature DOUBLE PRECISION, precipitation VARCHAR(50))");

            stmt.execute("INSERT INTO inhabitant_type (name, language) VALUES ('Urban', 'Русский')");
            stmt.execute("INSERT INTO region (name, area, inhabitant_type_id) VALUES ('Москва', 500.0, 1)");
            stmt.execute("INSERT INTO weather (region_id, date, temperature, precipitation) VALUES (1, CURRENT_DATE - 1, -10.5, 'снег')");
        }
    }

    @Test
    void testGetWeatherByRegion() {
        System.out.println("Running testGetWeatherByRegion...");

        weatherDAO.getWeatherByRegion("Москва");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT temperature FROM weather WHERE region_id = (SELECT id FROM region WHERE name = 'Москва')")) {

            if (rs.next()) {
                double temperature = rs.getDouble("temperature");
                assertEquals(-10.5, temperature, "Температура в регионе Москва должна быть -10.5");
            } else {
                fail("Не найдена информация о погоде для региона Москва.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Ошибка при извлечении данных из базы.");
        }
    }

    @Test
    void testGetSnowDates() {
        System.out.println("Running testGetSnowDates...");

        weatherDAO.getSnowDates("Москва", -5);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT date FROM weather WHERE region_id = (SELECT id FROM region WHERE name = 'Москва') AND precipitation = 'снег' AND temperature < -5")) {

            if (rs.next()) {
                Date snowDate = rs.getDate("date");
                assertNotNull(snowDate, "Дата с снегом должна быть найдена.");
            } else {
                fail("Нет данных о снегопаде с температурой ниже -5 для региона Москва.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Ошибка при извлечении данных из базы.");
        }
    }

    @Test
    void testGetWeatherByLanguage() {
        System.out.println("Running testGetWeatherByLanguage...");

        weatherDAO.getWeatherByLanguage("Русский");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT w.date, w.temperature, w.precipitation FROM weather w JOIN region r ON w.region_id = r.id JOIN inhabitant_type i ON r.inhabitant_type_id = i.id WHERE i.language = 'Русский'")) {

            if (rs.next()) {
                Date date = rs.getDate("date");
                double temperature = rs.getDouble("temperature");
                String precipitation = rs.getString("precipitation");

                assertEquals(-10.5, temperature, "Температура должна быть -10.5");
                assertEquals("снег", precipitation, "Осадки должны быть снегом");
            } else {
                fail("Не найдена информация о погоде для региона с языком 'Русский'.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Ошибка при извлечении данных из базы.");
        }
    }

    @Test
    void testGetAverageTemperature() {
        System.out.println("Running testGetAverageTemperature...");

        weatherDAO.getAverageTemperature(400.0);

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT AVG(temperature) AS avg_temp FROM weather w JOIN region r ON w.region_id = r.id WHERE r.area > 400")) {

            if (rs.next()) {
                double avgTemp = rs.getDouble("avg_temp");
                assertEquals(-10.5, avgTemp, 0.1, "Средняя температура должна быть -10.5");
            } else {
                fail("Не найдены данные о средней температуре для региона с площадью больше 400.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Ошибка при извлечении данных из базы.");
        }
    }

    @AfterAll
    void tearDown() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS weather, region, inhabitant_type CASCADE");
        }
        connection.close();
    }
}
