// Вариант: 8 Очиров Б. Б762-2

package org.example.chapter4.var1;

public class Main {
    public static void main(String[] args) {
        Point bottomLeft = new Point(0, 0);
        Square square = new Square(bottomLeft, 5, "red");

        // Выводим начальное состояние квадрата
        System.out.println("Квадрат: " + square);

        square.scale(1.5);
        System.out.println("Масштабируем: " + square);

        square.rotate(45);
        System.out.println("Поворачиваем: " + square);

        square.setColor("blue");
        System.out.println("Смена цвета: " + square);
    }
}
