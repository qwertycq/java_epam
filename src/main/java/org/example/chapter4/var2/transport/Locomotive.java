package org.example.chapter4.var2.transport;

public class Locomotive {
    private String fuelType;
    private int power;
    private int weight;

    public Locomotive(String fuelType, int power, int weight) {
        this.fuelType = fuelType;
        this.power = power;
        this.weight = weight;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getPower() {
        return power;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Локомотив{" +
                "Тип топлива='" + fuelType + '\'' +
                ", Мощность=" + power +
                ", Вес=" + weight +
                '}';
    }
}
