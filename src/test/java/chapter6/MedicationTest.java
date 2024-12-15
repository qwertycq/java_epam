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
        ointment = new Ointment("Healing Ointment");
        tablet = new Tablet("Pain Relief Tablet");
        solution = new Solution("Antiseptic Solution");

        ointment.addSubstance("Hydrocortisone", 1.0);
        ointment.addSubstance("Petrolatum", 99.0);

        tablet.addSubstance("Ibuprofen", 200.0);

        solution.addSubstance("Chlorhexidine", 0.5);
    }

    @Test
    public void testUpdateSubstanceStatus() {
        ointment.updateSubstanceStatus("разрешенное");
        assertEquals("разрешенное", ointment.status);
    }

    @Test
    public void testCalculateDosageOintment() {
        ointment.calculateDosage(70, 30);
    }

    @Test
    public void testCalculateDosageTablet() {
        tablet.calculateDosage(70, 30);
    }

    @Test
    public void testCalculateDosageSolution() {
        solution.calculateDosage(70, 30);
    }

    @Test
    public void testConductResearch() {
        solution.conductResearch();
    }

}
