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
        weatherDAO = new WeatherDAO();

        // Инициализация тестовых данных
        try (Statement stmt = connection.createStatement()) {
            // Очистка таблиц
            stmt.execute("DROP TABLE IF EXISTS weather, region, inhabitant_type CASCADE");

            // Создание таблиц
            stmt.execute("CREATE TABLE inhabitant_type (id SERIAL PRIMARY KEY, name VARCHAR(100), language VARCHAR(100))");
            stmt.execute("CREATE TABLE region (id SERIAL PRIMARY KEY, name VARCHAR(100), area DOUBLE PRECISION, inhabitant_type_id INT REFERENCES inhabitant_type(id))");
            stmt.execute("CREATE TABLE weather (id SERIAL PRIMARY KEY, region_id INT REFERENCES region(id), date DATE, temperature DOUBLE PRECISION, precipitation VARCHAR(50))");

            // Заполнение тестовыми данными
            stmt.execute("INSERT INTO inhabitant_type (name, language) VALUES ('Urban', 'Русский')");
            stmt.execute("INSERT INTO region (name, area, inhabitant_type_id) VALUES ('Москва', 500.0, 1)");
            stmt.execute("INSERT INTO weather (region_id, date, temperature, precipitation) VALUES (1, CURRENT_DATE - 1, -10.5, 'снег')");
        }
    }

    @Test
    void testGetWeatherByRegion() {
        System.out.println("Running testGetWeatherByRegion...");
        weatherDAO.getWeatherByRegion("Москва");
        assertTrue(true, "Метод отработал без исключений");
    }

    @Test
    void testGetSnowDates() {
        System.out.println("Running testGetSnowDates...");
        weatherDAO.getSnowDates("Москва", -5);
        assertTrue(true, "Метод отработал без исключений");
    }

    @Test
    void testGetWeatherByLanguage() {
        System.out.println("Running testGetWeatherByLanguage...");
        weatherDAO.getWeatherByLanguage("Русский");
        assertTrue(true, "Метод отработал без исключений");
    }

    @Test
    void testGetAverageTemperature() {
        System.out.println("Running testGetAverageTemperature...");
        weatherDAO.getAverageTemperature(400.0);
        assertTrue(true, "Метод отработал без исключений");
    }

    @AfterAll
    void tearDown() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS weather, region, inhabitant_type CASCADE");
        }
        connection.close();
    }
}
