package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Vault {
    private double cashInVault;
    private static final double MAX_CASH = 10000;
    private static final double MIN_CASH = 1000;

    private final Lock vaultLock = new ReentrantLock();

    public Vault(double initialCash) {
        this.cashInVault = initialCash;
        System.out.println("Хранилище инициализировано с суммой: " + initialCash);
    }

    public boolean withdrawCash(double amount) {
        vaultLock.lock();
        try {
            if (cashInVault >= amount) {
                cashInVault -= amount;
                System.out.println("Хранилище: снято " + amount + ". Текущая сумма в хранилище: " + cashInVault);
                return true;
            } else {
                System.out.println("Хранилище: недостаточно средств для снятия " + amount);
                return false;
            }
        } finally {
            vaultLock.unlock();
        }
    }

    public void depositCash(double amount) {
        vaultLock.lock();
        try {
            cashInVault += amount;
            System.out.println("Хранилище: добавлено " + amount + ". Текущая сумма в хранилище: " + cashInVault);
        } finally {
            vaultLock.unlock();
        }
    }

    public double getCashInVault() {
        vaultLock.lock();
        try {
            return cashInVault;
        } finally {
            vaultLock.unlock();
        }
    }

    public void replenishCash() {
        vaultLock.lock();
        try {
            if (cashInVault < MIN_CASH) {
                System.out.println("Хранилище: пополняем кассу до минимальной суммы.");
                cashInVault = MIN_CASH;
            }
        } finally {
            vaultLock.unlock();
        }
    }

    public void moveExcessCashToStorage() {
        vaultLock.lock();
        try {
            if (cashInVault > MAX_CASH) {
                double excessAmount = cashInVault - MAX_CASH;
                cashInVault = MAX_CASH;
                System.out.println("Хранилище: переведено " + excessAmount + " в хранилище.");
            }
        } finally {
            vaultLock.unlock();
        }
    }
}
