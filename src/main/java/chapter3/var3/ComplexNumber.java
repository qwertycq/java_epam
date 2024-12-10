package chapter3.var3;

public class ComplexNumber {
    private double real;
    private double imaginary; // мнимая часть

    public ComplexNumber (double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }
}
