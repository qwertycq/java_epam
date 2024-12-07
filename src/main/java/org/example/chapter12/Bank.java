package org.example.chapter12;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Bank {
    private static final int MAX_CASH = 10000;
    private static final int MIN_CASH = 1000;
    private final BlockingQueue<Client> queue = new LinkedBlockingQueue<>();
    private final ExecutorService cashierExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService observerExecutor = Executors.newSingleThreadExecutor();
    private final ReentrantLock lock = new ReentrantLock();
    private int cashInVault = 50000; // Хранилище
    private int cashInRegister = 5000; // Касса

    public Bank() {
        cashierExecutor.submit(this::processClients);
        observerExecutor.submit(this::monitorCash);
    }

    // Клиент добавляется в очередь
    public void addClient(Client client) {
        queue.offer(client);
    }

    // Обработка клиентов кассиром
    private void processClients() {
        while (true) {
            try {
                Client client = queue.take(); // Ждет клиента
                client.performTransaction(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Мониторинг наличности
    private void monitorCash() {
        while (true) {
            lock.lock();
            try {
                if (cashInRegister > MAX_CASH) {
                    int excess = cashInRegister - MAX_CASH;
                    cashInVault += excess;
                    cashInRegister -= excess;
                    System.out.println("Наблюдатель: Излишки отправлены в хранилище: " + excess);
                } else if (cashInRegister < MIN_CASH) {
                    int deficit = Math.min(MIN_CASH - cashInRegister, cashInVault);
                    cashInVault -= deficit;
                    cashInRegister += deficit;
                    System.out.println("Наблюдатель: Касса пополнена из хранилища: " + deficit);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } finally {
                lock.unlock();
            }
        }
    }

    // Операции кассы
    public boolean withdraw(int amount) {
        lock.lock();
        try {
            if (cashInRegister >= amount) {
                cashInRegister -= amount;
                System.out.println("Касса: Снято " + amount);
                return true;
            } else {
                System.out.println("Касса: Недостаточно наличных");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            cashInRegister += amount;
            System.out.println("Касса: Пополнено " + amount);
        } finally {
            lock.unlock();
        }
    }

    // Завершение работы банка
    public void shutdown() {
        cashierExecutor.shutdownNow();
        observerExecutor.shutdownNow();
    }
}