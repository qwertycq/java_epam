package org.example.chapter4.var1;

import java.util.Objects;

public class Segment {
    private Point start; // Начальная точка
    private Point end;   // Конечная точка

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    // Метод для вычисления длины отрезка
    public double length() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    // Масштабирование отрезка относительно его центра
    public void scale(double factor) {
        double centerX = (start.getX() + end.getX()) / 2;
        double centerY = (start.getY() + end.getY()) / 2;

        start.setX(centerX + (start.getX() - centerX) * factor);
        start.setY(centerY + (start.getY() - centerY) * factor);

        end.setX(centerX + (end.getX() - centerX) * factor);
        end.setY(centerY + (end.getY() - centerY) * factor);
    }

    // Поворот отрезка относительно его центра
    public void rotate(double angle) {
        double centerX = (start.getX() + end.getX()) / 2;
        double centerY = (start.getY() + end.getY()) / 2;

        rotatePoint(start, centerX, centerY, angle);
        rotatePoint(end, centerX, centerY, angle);
    }

    // Метод для поворота точки
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
        Segment segment = (Segment) o;
        return start.equals(segment.start) && end.equals(segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Segment{" + "start=" + start + ", end=" + end + '}';
    }
}
