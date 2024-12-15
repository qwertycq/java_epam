package chapter11.var1;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairwiseSumTest {

    @Test
    public void testPairwiseSumEvenCount() {
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5, 6));

        int expected = 21;

        int result = PairwiseSum.pairwiseSum(numbers);

        assertEquals(expected, result, "Сумма для четного количества элементов рассчитана неверно");
    }

    @Test
    public void testPairwiseSumOddCount() {
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5));

        int expected = 15;

        int result = PairwiseSum.pairwiseSum(numbers);

        assertEquals(expected, result, "Сумма для нечетного количества элементов рассчитана неверно");
    }

    @Test
    public void testPairwiseSumSingleElement() {
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(42));

        int expected = 42;

        int result = PairwiseSum.pairwiseSum(numbers);

        assertEquals(expected, result, "Сумма для одного элемента рассчитана неверно");
    }

}
