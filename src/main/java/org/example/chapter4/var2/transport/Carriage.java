package org.example.chapter4.var2.transport;

public abstract class Carriage {
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
