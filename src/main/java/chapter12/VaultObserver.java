package chapter12;

class VaultObserver implements Runnable {
    private final Cashier cashRegister;

    public VaultObserver(Cashier cashRegister) {
        this.cashRegister = cashRegister;
    }

    @Override
    public void run() {
        try {
            while (true) {
                cashRegister.manageFunds();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}