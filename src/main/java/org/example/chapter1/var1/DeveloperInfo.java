package org.example.chapter1.var1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeveloperInfo {
    public static void main(String[] args) {
        String developer = "Ochirov B. B762-2";
        String date = "07.09.2024 14:00";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        System.out.println("Фамилия разработчика: " + developer);
        System.out.println("Дата и время получения задания: " + date);
        System.out.println("Дата и время сдачи задания: " + now.format(formatter));
    }
}
