package chapter11.var1;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairwiseSumTest {

    @Test
    public void testPairwiseSumEvenCount() {
        // Исходные данные
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5, 6));

        // Ожидаемый результат
        int expected = 21;

        // Вызов метода
        int result = PairwiseSum.pairwiseSum(numbers);

        // Проверка результата
        assertEquals(expected, result, "Сумма для четного количества элементов рассчитана неверно");
    }

    @Test
    public void testPairwiseSumOddCount() {
        // Исходные данные
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(1, 2, 3, 4, 5));

        // Ожидаемый результат
        int expected = 15;

        // Вызов метода
        int result = PairwiseSum.pairwiseSum(numbers);

        // Проверка результата
        assertEquals(expected, result, "Сумма для нечетного количества элементов рассчитана неверно");
    }

    @Test
    public void testPairwiseSumSingleElement() {
        // Исходные данные
        Set<Integer> numbers = new LinkedHashSet<>(Set.of(42));

        // Ожидаемый результат
        int expected = 42;

        // Вызов метода
        int result = PairwiseSum.pairwiseSum(numbers);

        // Проверка результата
        assertEquals(expected, result, "Сумма для одного элемента рассчитана неверно");
    }

}
