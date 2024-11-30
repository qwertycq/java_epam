package org.example.chapter3.var3;

public class Point {
    private ComplexNumber x;
    private ComplexNumber y;
    private ComplexNumber z;

    public Point(ComplexNumber x, ComplexNumber y, ComplexNumber z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceToOrigin() {
        double modX = x.modulus();
        double modY = y.modulus();
        double modZ = z.modulus();
        return Math.sqrt(modX * modX + modY * modY + modZ * modZ);
    }

    public double distanceTo(Point other) {
        double dx = this.x.modulus() - other.x.modulus();
        double dy = this.y.modulus() - other.y.modulus();
        double dz = this.z.modulus() - other.z.modulus();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
