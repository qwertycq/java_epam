package org.example.chapter11.var1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PairwiseSum {

    public static void main(String[] args) {
        // Пример исходного набора чисел
        Set<Integer> numbers = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        // Вызываем метод для выполнения попарного суммирования
        int result = pairwiseSum(numbers);

        System.out.println("Результат: " + result);
    }

    public static int pairwiseSum(Set<Integer> numbers) {
        // Преобразуем множество в список
        List<Integer> currentList = new ArrayList<>(numbers);

        // Пока список содержит больше одного элемента
        while (currentList.size() > 1) {
            List<Integer> finalCurrentList = currentList;
            List<Integer> tempList = IntStream.range(0, currentList.size() / 2)
                    .mapToObj(i -> finalCurrentList.get(i * 2) + finalCurrentList.get(i * 2 + 1)) // Суммируем попарно
                    .collect(Collectors.toList());

            // Если остался один элемент без пары, добавляем его в следующий этап
            if (currentList.size() % 2 == 1) {
                tempList.add(currentList.get(currentList.size() - 1));
            }

            // Обновляем ссылку на новый список
            currentList = tempList;
        }

        return currentList.get(0); // Возвращаем итоговый результат
    }
}

