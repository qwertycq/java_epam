package chapter12;

class Cashier implements Runnable {
    private final Vault vault;

    public Cashier(Vault vault) {
        this.vault = vault;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (vault.withdrawCash(100)) {
                    System.out.println(Thread.currentThread().getName() + " снял 100 из кассы.");
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
