package chapter13;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            WeatherQuery query = new WeatherQuery();

            query.getWeatherByRegion("Moscow");

            query.getSnowyDaysWithLowTemp("Moscow", -10);

            query.getWeatherForLastWeek("Russian");

            query.getAvgTemperatureForLargeRegions(10000);

            WeatherUpdater updater = new WeatherUpdater();
            // Обновление температуры
            updater.updateTemperature(1, Date.valueOf("2024-12-12"), -5);

            // Добавление новой записи
            updater.addWeatherRecord(1, Date.valueOf("2024-12-13"), -2, "snow");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
