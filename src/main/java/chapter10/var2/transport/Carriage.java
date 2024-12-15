package chapter10.var2.transport;

import java.io.Serializable;

public abstract class Carriage implements Serializable {
    private static final long serialVersionUID = 1L;
    private int comfortLevel;
    private int passengerCapacity;
    private int baggageCapacity;

    public Carriage(int comfortLevel, int passengerCapacity, int baggageCapacity) {
        this.comfortLevel = comfortLevel;
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = baggageCapacity;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getBaggageCapacity() {
        return baggageCapacity;
    }
}