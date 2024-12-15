package chapter10.var2.transport;

import java.io.Serializable;

public class Locomotive implements Serializable {
    private static final long serialVersionUID = 1L;
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
