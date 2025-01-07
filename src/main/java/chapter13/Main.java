package chapter13;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseManager.getConnection()) {

            DatabaseInitializer.initializeDatabase();

            WeatherDAO weatherDAO = new WeatherDAO(conn);

            System.out.println("Погода в определенном регионе:");
            weatherDAO.getWeatherByRegion("Санкт-Петербург");

            System.out.println("\nДаты со снегом и температурой ниже -5:");
            weatherDAO.getSnowDates("Москва", -5);

            System.out.println("\nПогода в регионах, где языком является англиский:");
            weatherDAO.getWeatherByLanguage("Английский");

            System.out.println("\nСредняя температура в регионах площадью > 500 за последнюю неделю:");
            weatherDAO.getAverageTemperature(500);

            WeatherModifier weatherModifier = new WeatherModifier();
            Date date = Date.valueOf("2025-01-05");
            weatherModifier.addWeatherData(1, date, -3.0, "снег");

            System.out.println("\nОбновленная погода для Санкт-Петербурга:");
            weatherDAO.getWeatherByRegion("Санкт-Петербург");

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
