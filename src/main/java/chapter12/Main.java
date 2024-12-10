// Вариант: 8 Очиров Б. Б762-2

package chapter12;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(5000);  // начальный запас наличных
        Bank bank = new Bank(storage);

        // Создание кассиров
        Cashier cashier1 = new Cashier(bank);
        Cashier cashier2 = new Cashier(bank);

        // Создание клиентов с их счетами
        Account client1Account = new Account(1000);
        Client client1 = new Client(bank, client1Account);
        Account client2Account = new Account(2000);
        Client client2 = new Client(bank, client2Account);

        // Добавляем клиентов в очередь
        bank.addClient(client1);
        bank.addClient(client2);

        // Запуск кассиров
        Thread cashierThread1 = new Thread(cashier1);
        Thread cashierThread2 = new Thread(cashier2);

        cashierThread1.start();
        cashierThread2.start();

        // Симуляция работы с клиентами
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Остановка кассиров
        cashier1.stop();
        cashier2.stop();
    }
}