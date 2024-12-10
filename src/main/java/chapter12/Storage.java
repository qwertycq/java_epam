package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Storage {
    private double cashReserve;
    private final double MAX_CASH = 10000;
    private final double MIN_CASH = 1000;
    private final Lock lock = new ReentrantLock();

    public Storage(double initialReserve) {
        this.cashReserve = initialReserve;
    }

    public void transferToCash(double amount) {
        lock.lock();
        try {
            if (cashReserve >= amount) {
                cashReserve -= amount;
                System.out.println("Transferred to cash: " + amount);
            } else {
                System.out.println("Not enough cash in storage to transfer: " + amount);
            }
        } finally {
            lock.unlock();
        }
    }

    public void transferToStorage(double amount) {
        lock.lock();
        try {
            cashReserve += amount;
            System.out.println("Transferred to storage: " + amount);
        } finally {
            lock.unlock();
        }
    }

    public double getCashReserve() {
        return cashReserve;
    }
}