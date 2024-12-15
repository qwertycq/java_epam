package chapter12;

class Client implements Runnable {
    private final Account account;
    private final Account targetAccount;

    public Client(Account account, Account targetAccount) {
        this.account = account;
        this.targetAccount = targetAccount;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Клиент выполняет операции с деньгами
                account.deposit(200);
                account.withdraw(150);
                account.transfer(targetAccount, 100);
                account.pay(50);
                account.deposit(200);

                // Задержка между операциями
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
