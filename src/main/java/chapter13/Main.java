package chapter13;

public class Main {
    public static void main(String[] args) {
        WeatherDAO weatherDAO = new WeatherDAO();

        System.out.println("Погода в определенном регионе:");
        weatherDAO.getWeatherByRegion("Санкт-Петербург");

        System.out.println("\nДаты со снегом и температурой ниже -5:");
        weatherDAO.getSnowDates("Москва", -5);

        System.out.println("\nПогода в регионах, где языком является англиский:");
        weatherDAO.getWeatherByLanguage("Английский");

        System.out.println("\nСредняя температура в регионах площадью > 500 за последнюю неделю:");
        weatherDAO.getAverageTemperature(500);
    }
}
