package chapter8.var2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ConsonantUtilsTest {

    @Test
    public void testIsConsonant() {
        assertTrue(ConsonantUtils.isConsonant('б'));
        assertTrue(ConsonantUtils.isConsonant('т'));

        assertFalse(ConsonantUtils.isConsonant('а'));
        assertFalse(ConsonantUtils.isConsonant('e'));
    }
}
