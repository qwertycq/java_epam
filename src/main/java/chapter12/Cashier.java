package chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Cashier implements Runnable {
    private final Bank bank;
    private final ExecutorService clientExecutor;

    public Cashier(Bank bank) {
        this.bank = bank;
        this.clientExecutor = Executors.newFixedThreadPool(10);
    }

    @Override
    public void run() {
        try {
            // Обслуживаем клиентов последовательно
            while (true) {
                Client client = bank.getNextClient();
                if (client != null) {
                    clientExecutor.submit(client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        clientExecutor.shutdown();
    }
}