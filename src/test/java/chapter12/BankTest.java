package chapter12;

import org.example.chapter12.Bank;
import org.example.chapter12.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankTest {

    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @AfterEach
    void tearDown() {
        if (bank != null) {
            bank.shutdown();
        }
    }

    @Test
    void testWithdrawSuccess() throws InterruptedException {
        Client client = new Client("Тест Клиент", 500, false, bank);

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            bank.addClient(client);
            latch.countDown();
        }).start();

        assertTrue(latch.await(2, TimeUnit.SECONDS), "Клиент должен быть обработан");
        assertTrue(bank.withdraw(500), "Должно быть возможно снять деньги");
    }

    @Test
    void testWithdrawFailInsufficientCash() throws InterruptedException {
        bank.deposit(-4000); // Уменьшаем баланс кассы

        Client client = new Client("Тест Клиент", 6000, false, bank);

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            bank.addClient(client);
            latch.countDown();
        }).start();

        assertTrue(latch.await(2, TimeUnit.SECONDS), "Клиент должен быть обработан");
        assertFalse(bank.withdraw(6000), "Нельзя снять больше, чем есть в кассе");
    }

    @Test
    void testObserverReplenishCash() throws InterruptedException {
        bank.deposit(-4500); // Уменьшаем баланс кассы

        TimeUnit.SECONDS.sleep(3); // Даем время наблюдателю среагировать
        assertTrue(bank.withdraw(500), "Касса должна быть пополнена наблюдателем");
    }

    @Test
    void testObserverTransferExcessCash() throws InterruptedException {
        bank.deposit(6000); // Увеличиваем баланс кассы

        TimeUnit.SECONDS.sleep(3); // Даем время наблюдателю среагировать
        assertFalse(bank.withdraw(11000), "Излишки должны быть отправлены в хранилище, нельзя снять больше");
    }
}
