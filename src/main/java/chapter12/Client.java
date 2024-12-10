package chapter12;

class Client implements Runnable {
    private final Account account;
    private final Bank bank;

    public Client(Bank bank, Account account) {
        this.bank = bank;
        this.account = account;
    }

    @Override
    public void run() {
        try {
            // Пример: клиент снимает деньги
            bank.processWithdrawal(account, 100);
            bank.processDeposit(account, 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
