package org.example.chapter4.var1;

import java.util.Objects;

public class Square {
    private Point[] vertices; // Массив точек, представляющих вершины квадрата
    private String color;     // Цвет квадрата

    // Конструктор квадрата: задаются нижняя левая вершина, длина стороны и цвет
    public Square(Point bottomLeft, double sideLength, String color) {
        this.vertices = new Point[4];
        this.vertices[0] = bottomLeft;
        this.vertices[1] = new Point(bottomLeft.getX() + sideLength, bottomLeft.getY());
        this.vertices[2] = new Point(bottomLeft.getX() + sideLength, bottomLeft.getY() + sideLength);
        this.vertices[3] = new Point(bottomLeft.getX(), bottomLeft.getY() + sideLength);
        this.color = color;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Масштабирование квадрата
    public void scale(double factor) {
        Point center = getCenter();
        for (Point vertex : vertices) {
            double dx = vertex.getX() - center.getX();
            double dy = vertex.getY() - center.getY();
            vertex.setX(center.getX() + dx * factor);
            vertex.setY(center.getY() + dy * factor);
        }
    }

    // Поворот квадрата вокруг его центра
    public void rotate(double angle) {
        Point center = getCenter();
        for (Point vertex : vertices) {
            rotatePoint(vertex, center.getX(), center.getY(), angle);
        }
    }

    // Вычисление центра квадрата
    private Point getCenter() {
        double centerX = 0;
        double centerY = 0;
        for (Point vertex : vertices) {
            centerX += vertex.getX();
            centerY += vertex.getY();
        }
        return new Point(centerX / 4, centerY / 4);
    }

    // Вспомогательный метод для поворота точки
    private void rotatePoint(Point point, double centerX, double centerY, double angle) {
        double radians = Math.toRadians(angle);
        double dx = point.getX() - centerX;
        double dy = point.getY() - centerY;

        double newX = centerX + dx * Math.cos(radians) - dy * Math.sin(radians);
        double newY = centerY + dx * Math.sin(radians) + dy * Math.cos(radians);

        point.setX(newX);
        point.setY(newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(color, square.color) &&
                Objects.equals(vertices[0], square.vertices[0]) &&
                Objects.equals(vertices[1], square.vertices[1]) &&
                Objects.equals(vertices[2], square.vertices[2]) &&
                Objects.equals(vertices[3], square.vertices[3]);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices[0], vertices[1], vertices[2], vertices[3], color);
    }

    @Override
    public String toString() {
        return "Square{" +
                "vertices=" + vertices[0] + ", " + vertices[1] + ", " + vertices[2] + ", " + vertices[3] +
                ", color='" + color + '\'' +
                '}';
    }
}
