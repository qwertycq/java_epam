package chapter8.var2;

import static org.junit.jupiter.api.Assertions.*;

import org.example.chapter8.var2.ConsonantUtils;
import org.junit.jupiter.api.Test;

public class ConsonantUtilsTest {

    @Test
    public void testIsConsonant() {
        // Проверяем, что символ является согласной
        assertTrue(ConsonantUtils.isConsonant('б'));
        assertTrue(ConsonantUtils.isConsonant('т'));

        // Проверяем, что символ не является согласной
        assertFalse(ConsonantUtils.isConsonant('а'));
        assertFalse(ConsonantUtils.isConsonant('e'));
    }
}
