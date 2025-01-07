package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Vault {
    private final Lock lock = new ReentrantLock();
    private int balance;

    public Vault(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Баланс хранилища увеличен на " + amount + ". Текущий баланс: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public int withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Баланс хранилища уменьшен на " + amount + ". Текущий баланс: " + balance);
                return amount;
            } else {
                System.out.println("Недостаточно средств в хранилище. Запрошено: " + amount + ", Доступно: " + balance);
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }
}