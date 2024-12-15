package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private double balance;
    private final Lock lock = new ReentrantLock();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " пополнил счет на " + amount + ". Текущий баланс: " + balance);
        } finally {
            lock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " снял " + amount + ". Текущий баланс: " + balance);
                return true;
            } else {
                System.out.println(Thread.currentThread().getName() + " не может снять " + amount + ". Недостаточно средств.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean transfer(Account targetAccount, double amount) {
        lock.lock();
        try {
            if (this.withdraw(amount)) {
                targetAccount.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " перевел " + amount + " на счет " + targetAccount);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean pay(double amount) {
        return withdraw(amount);
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
