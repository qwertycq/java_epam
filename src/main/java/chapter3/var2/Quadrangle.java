package chapter3.var2;

public class Quadrangle {
    double a, b, c, d;
    double angleAB, angleBC, angleCD, angleDA;

    public Quadrangle(double a, double b, double c, double d, double angleAB, double angleBC, double angleCD, double angleDA) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.angleAB = angleAB;
        this.angleBC = angleBC;
        this.angleCD = angleCD;
        this.angleDA = angleDA;
    }

    public double getPerimeter() {
        return a + b + c + d;
    }

    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt((p - a) * (p - b) * (p - c) * (p - d));
    }

    public String getType() {
        if (a == b & b == c && c == d) {
            if (angleAB == 90 && angleBC == 90 && angleCD == 90 && angleDA == 90 ) {
                return "Square";
            } else {
                return "Romb";
            }
        } else if (a == c && b == d && angleAB == 90 && angleBC == 90 && angleCD == 90 && angleDA == 90) {
            return "Rectangle";
        } else {
            return "Proizv";
        }
    }

    @Override
    public String toString() {
        return String.format("Quadrilateral{a=%.2f, b=%.2f, c=%.2f, d=%.2f, area=%.2f, perimeter=%.2f, type=%s}",
                a, b, c, d, getArea(), getPerimeter(), getType());
    }
}
