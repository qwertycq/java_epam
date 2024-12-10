package chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank;
    private Storage storage;
    private Account account1;
    private Account account2;
    private Client client1;
    private Client client2;

    @BeforeEach
    void setUp() {
        storage = new Storage(5000);
        bank = new Bank(storage);
        account1 = new Account(1000);
        account2 = new Account(2000);
        client1 = new Client(bank, account1);
        client2 = new Client(bank, account2);
    }

    @Test
    void testDeposit() throws InterruptedException {
        bank.processDeposit(account1, 500);
        assertEquals(1500, account1.getBalance(), "Balance after deposit should be correct");
    }

    @Test
    void testStorageTransferToCash() {
        storage.transferToCash(1000);
        assertEquals(4000, storage.getCashReserve(), "Cash reserve after transfer should be correct");
    }

    @Test
    void testStorageTransferToStorage() {
        storage.transferToStorage(2000);
        assertEquals(7000, storage.getCashReserve(), "Cash reserve after transfer to storage should be correct");
    }

    @Test
    void testBankProcessClientTransactions() throws InterruptedException {
        bank.addClient(client1);
        bank.addClient(client2);

        // Process transactions for both clients
        bank.processDeposit(account1, 200);
        bank.processWithdrawal(account2, 100);

        assertEquals(1200, account1.getBalance(), "Account 1 balance after deposit should be correct");
        assertEquals(1900, account2.getBalance(), "Account 2 balance after withdrawal should be correct");
    }
}
