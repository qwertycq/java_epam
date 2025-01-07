package chapter12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Cashier {
    private final Lock lock = new ReentrantLock();
    private final Condition fundsAvailable = lock.newCondition();
    private final Vault vault;
    private int balance;

    public Cashier(int initialBalance, Vault vault) {
        this.balance = initialBalance;
        this.vault = vault;
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("Баланс кассы увеличен на " + amount + ". Текущий баланс: " + balance);
            fundsAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) throws InterruptedException {
        lock.lock();
        try {
            while (balance < amount) {
                System.out.println("Недостаточно средств в кассе. Ожидание пополнения.");
                fundsAvailable.await();
            }
            balance -= amount;
            System.out.println("Баланс кассы уменьшен на " + amount + ". Текущий баланс: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public void manageFunds() {
        lock.lock();
        try {
            if (balance > 5000) {
                int transferAmount = balance - 5000;
                balance -= transferAmount;
                vault.deposit(transferAmount);
                System.out.println("Переведено " + transferAmount + " из кассы в хранилище.");
            } else if (balance < 1000) {
                int replenishment = vault.withdraw(4000);
                balance += replenishment;
                System.out.println("Касса пополнена из хранилища на: " + replenishment);
            }
        } finally {
            lock.unlock();
        }
    }
}