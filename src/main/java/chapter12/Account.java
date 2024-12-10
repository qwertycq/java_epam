package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private double balance;
    private final Lock lock = new ReentrantLock();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }
}