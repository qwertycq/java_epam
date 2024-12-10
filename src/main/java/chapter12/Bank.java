package chapter12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Bank {
    private final BlockingQueue<Client> clientQueue;
    private final Storage storage;

    public Bank(Storage storage) {
        this.storage = storage;
        this.clientQueue = new LinkedBlockingQueue<>();
    }

    public void addClient(Client client) {
        clientQueue.offer(client);
    }

    public Client getNextClient() {
        try {
            return clientQueue.take();  // Блокирует, пока не появится клиент
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public void processWithdrawal(Account account, double amount) throws InterruptedException {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful: " + amount);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    public void processDeposit(Account account, double amount) throws InterruptedException {
        account.deposit(amount);
        System.out.println("Deposit successful: " + amount);
    }
}
