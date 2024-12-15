package chapter12;

class VaultObserver implements Runnable {
    private final Vault vault;

    public VaultObserver(Vault vault) {
        this.vault = vault;
    }

    @Override
    public void run() {
        try {
            while (true) {
                vault.replenishCash();
                vault.moveExcessCashToStorage();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
