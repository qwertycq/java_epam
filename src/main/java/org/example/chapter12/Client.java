package org.example.chapter12;

public class Client implements Runnable {
    private final String name;
    private final int amount;
    private final boolean isDeposit; // true - пополнение, false - снятие
    private final Bank bank;

    public Client(String name, int amount, boolean isDeposit, Bank bank) {
        this.name = name;
        this.amount = amount;
        this.isDeposit = isDeposit;
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.addClient(this);
    }

    public void performTransaction(Bank bank) {
        System.out.println(name + " обслуживается. Операция: " + (isDeposit ? "Пополнение" : "Снятие") + " на сумму " + amount);
        if (isDeposit) {
            bank.deposit(amount);
        } else {
            bank.withdraw(amount);
        }
    }
}

