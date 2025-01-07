package chapter12;

import java.util.concurrent.*;

public class BankApplication {
    public static void main(String[] args) {
        Vault vault = new Vault(10000);
        Cashier cashier = new Cashier(2000, vault);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(new VaultObserver(cashier));

        executor.submit(new Client(cashier));

        executor.shutdown();
    }
}