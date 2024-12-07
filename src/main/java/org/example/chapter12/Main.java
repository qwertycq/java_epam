package org.example.chapter12;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();

        // Создание клиентов
        for (int i = 1; i <= 10; i++) {
            Client client = new Client("Клиент " + i, i * 100, i % 2 == 0, bank);
            new Thread(client).start();
            TimeUnit.MILLISECONDS.sleep(10); // Имитация прихода клиентов
        }

        TimeUnit.SECONDS.sleep(5); // Работа системы
        bank.shutdown(); // Завершение
    }
}