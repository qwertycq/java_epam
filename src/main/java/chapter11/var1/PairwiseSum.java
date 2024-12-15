// Вариант: 8 Очиров Б. Б762-2

package chapter11.var1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PairwiseSum {

    public static void main(String[] args) {
        Set<Integer> numbers = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        int result = pairwiseSum(numbers);

        System.out.println("Результат: " + result);
    }

    public static int pairwiseSum(Set<Integer> numbers) {
        List<Integer> currentList = new ArrayList<>(numbers);

        while (currentList.size() > 1) {
            List<Integer> finalCurrentList = currentList;
            List<Integer> tempList = IntStream.range(0, currentList.size() / 2)
                    .mapToObj(i -> finalCurrentList.get(i * 2) + finalCurrentList.get(i * 2 + 1))
                    .collect(Collectors.toList());

            if (currentList.size() % 2 == 1) {
                tempList.add(currentList.get(currentList.size() - 1));
            }

            currentList = tempList;
        }

        return currentList.get(0);
    }
}

