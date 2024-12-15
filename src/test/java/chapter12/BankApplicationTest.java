package chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankApplicationTest {

    private Account account1;
    private Account account2;
    private Vault vault;

    @BeforeEach
    public void setup() {
        account1 = new Account(1000);
        account2 = new Account(500);
        vault = new Vault(5000);
    }

    @Test
    public void testDeposit() {
        account1.deposit(200);
        assertEquals(1200, account1.getBalance(), "Баланс должен быть обновлен после пополнения");
    }

    @Test
    public void testWithdraw() {
        boolean success = account1.withdraw(200);
        assertTrue(success, "Снятие должно быть успешным при достаточном балансе");
        assertEquals(800, account1.getBalance(), "Баланс должен быть обновлен после снятия");

        success = account1.withdraw(1000);
        assertFalse(success, "Снятие должно быть неудачным при недостаточном балансе");
    }

    @Test
    public void testTransfer() {
        boolean success = account1.transfer(account2, 300);
        assertTrue(success, "Перевод должен быть успешным при наличии средств");
        assertEquals(700, account1.getBalance(), "Баланс account1 должен уменьшиться после перевода");
        assertEquals(800, account2.getBalance(), "Баланс account2 должен увеличиться после перевода");

        success = account1.transfer(account2, 1000);
        assertFalse(success, "Перевод должен быть неудачным при недостаточном балансе");
    }

    @Test
    public void testPay() {
        boolean success = account1.pay(100);
        assertTrue(success, "Оплата должна быть успешной при наличии средств");
        assertEquals(900, account1.getBalance(), "Баланс должен быть обновлен после оплаты");

        success = account1.pay(1000);
        assertFalse(success, "Оплата должна быть неудачной при недостаточном балансе");
    }

    @Test
    public void testVaultWithdrawCash() {
        boolean success = vault.withdrawCash(100);
        assertTrue(success, "Снятие из хранилища должно быть успешным при наличии средств");
        assertEquals(4900, vault.getCashInVault(), "Баланс хранилища должен уменьшиться после снятия");

        success = vault.withdrawCash(10000);
        assertFalse(success, "Снятие из хранилища должно быть неудачным при недостаточном количестве средств");
        assertEquals(4900, vault.getCashInVault(), "Баланс хранилища не должен измениться после неудачного снятия");
    }

    @Test
    public void testVaultDepositCash() {
        vault.depositCash(500);
        assertEquals(5500, vault.getCashInVault(), "Баланс хранилища должен увеличиться после пополнения");
    }

    @Test
    public void testVaultReplenishCash() {
        vault = new Vault(500);

        assertEquals(500, vault.getCashInVault(), "Начальный баланс хранилища должен быть 500");

        vault.replenishCash();
        assertEquals(1000, vault.getCashInVault(), "Баланс хранилища должен быть пополнен до минимальной суммы");
    }

    @Test
    public void testVaultMoveExcessCashToStorage() {
        vault = new Vault(15000);

        assertEquals(15000, vault.getCashInVault(), "Начальный баланс хранилища должен быть 15000");

        vault.moveExcessCashToStorage();
        assertEquals(10000, vault.getCashInVault(), "Баланс хранилища должен быть уменьшен до максимально допустимой суммы");
    }

    @Test
    public void testCashierTask() throws InterruptedException {
        Thread cashierThread = new Thread(new Cashier(vault));
        cashierThread.start();

        Thread.sleep(1000);

        assertTrue(vault.getCashInVault() <= 5000, "Баланс хранилища должен уменьшиться после снятия кассиром");
    }

}
