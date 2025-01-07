package chapter12;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.concurrent.*;

class BankApplicationTest {

    private Vault vault;
    private Cashier cashier;

    @BeforeEach
    void setUp() {
        vault = new Vault(10000);
        cashier = new Cashier(2000, vault);
    }

    @Test
    @DisplayName("Тест функции внесения депозита Кассиром")
    void testCashierDeposit() {
        cashier.deposit(500);
        assertDoesNotThrow(() -> cashier.deposit(500));
    }

    @Test
    @DisplayName("Тест функции снятия средств Кассиром при достаточных средствах")
    void testCashierWithdrawSufficientFunds() throws InterruptedException {
        cashier.withdraw(1000);
        assertDoesNotThrow(() -> cashier.withdraw(1000));
    }

    @Test
    @DisplayName("Тест функции снятия средств Кассиром при недостаточных средствах")
    void testCashierWithdrawInsufficientFunds() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                cashier.withdraw(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            Thread.sleep(1000);
            cashier.deposit(3000);
        });

        executor.shutdownNow();
    }

    @Test
    @DisplayName("Тест взаимодействия Кассира и Сейфа")
    void testCashierVaultInteraction() {
        cashier.deposit(6000);
        assertDoesNotThrow(() -> cashier.manageFunds());
    }

    @Test
    @DisplayName("Тест внесения и снятия средств из Сейфа")
    void testVaultDepositWithdraw() {
        vault.deposit(2000);
        assertDoesNotThrow(() -> vault.deposit(2000));

        int withdrawnAmount = vault.withdraw(5000);
        assertEquals(5000, withdrawnAmount);

        int insufficientAmount = vault.withdraw(20000);
        assertEquals(0, insufficientAmount);
    }
}
