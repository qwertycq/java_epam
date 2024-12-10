package chapter6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicationTest {

    private Ointment ointment;
    private Tablet tablet;
    private Solution solution;

    @BeforeEach
    public void setUp() {
        // Инициализируем объекты перед каждым тестом
        ointment = new Ointment("Healing Ointment");
        tablet = new Tablet("Pain Relief Tablet");
        solution = new Solution("Antiseptic Solution");

        // Добавляем вещества для тестирования
        ointment.addSubstance("Hydrocortisone", 1.0);
        ointment.addSubstance("Petrolatum", 99.0);

        tablet.addSubstance("Ibuprofen", 200.0);

        solution.addSubstance("Chlorhexidine", 0.5);
    }

    @Test
    public void testUpdateSubstanceStatus() {
        // Проверка изменения статуса вещества
        ointment.updateSubstanceStatus("разрешенное");
        assertEquals("разрешенное", ointment.status);
    }

    @Test
    public void testCalculateDosageOintment() {
        // Проверка дозировки для мази (метод не должен выдавать ошибок)
        ointment.calculateDosage(70, 30);
    }

    @Test
    public void testCalculateDosageTablet() {
        // Проверка дозировки для таблетки
        tablet.calculateDosage(70, 30);
    }

    @Test
    public void testCalculateDosageSolution() {
        // Проверка дозировки для раствора
        solution.calculateDosage(70, 30);
    }

    @Test
    public void testConductResearch() {
        // Проверка, что метод research вызывается без ошибок
        solution.conductResearch();
    }

}
