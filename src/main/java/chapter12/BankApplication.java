package chapter12;

import java.util.concurrent.*;

public class BankApplication {
    public static void main(String[] args) {
        Vault vault = new Vault(5000);
        ExecutorService cashierExecutor = Executors.newFixedThreadPool(3);

        VaultObserver vaultObserver = new VaultObserver(vault);
        Thread observerThread = new Thread(vaultObserver);
        observerThread.start();

        for (int i = 0; i < 3; i++) {
            cashierExecutor.submit(new Cashier(vault));
        }

        Account account1 = new Account(1000);
        Account account2 = new Account(500);
        Account account3 = new Account(1500);

        Thread clientThread1 = new Thread(new Client(account1, account2), "Клиент 1");
        Thread clientThread2 = new Thread(new Client(account2, account3), "Клиент 2");
        Thread clientThread3 = new Thread(new Client(account3, account1), "Клиент 3");

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
