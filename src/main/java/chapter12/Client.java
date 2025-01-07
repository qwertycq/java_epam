package chapter12;

import java.util.Random;

class Client implements Runnable {
    private final Cashier cashRegister;
    private final Random random = new Random();

    public Client(Cashier cashRegister) {
        this.cashRegister = cashRegister;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Клиент");

        try {
            while (true) {
                int action = random.nextInt(3);
                int amount = random.nextInt(1000) + 1;
                switch (action) {
                    case 0:
                        System.out.println(Thread.currentThread().getName() + " внес " + amount);
                        cashRegister.deposit(amount);
                        break;
                    case 1:
                        System.out.println(Thread.currentThread().getName() + " пытается снять " + amount);
                        cashRegister.withdraw(amount);
                        break;
                }
                Thread.sleep(random.nextInt(2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}