// Очиров Б. Б762-2, 8 Вариант

package chapter3.var3;

public class Main {
    public static void main(String[] args) {
        ComplexNumber x1 = new ComplexNumber(1, 2);
        ComplexNumber y1 = new ComplexNumber(3, 4);
        ComplexNumber z1 = new ComplexNumber(5, 6);

        ComplexNumber x2 = new ComplexNumber(2, 1);
        ComplexNumber y2 = new ComplexNumber(4, 3);
        ComplexNumber z2 = new ComplexNumber(6, 5);

        Point point1 = new Point(x1, y1, z1);
        Point point2 = new Point(x2, y2, z2);

        System.out.println("Расстояние от первой точки до начала координат: " + point1.distanceToOrigin());

        System.out.println("Расстояние между точками: " + point1.distanceTo(point2));
    }
}
