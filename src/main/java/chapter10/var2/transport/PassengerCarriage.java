package chapter10.var2.transport;

public class PassengerCarriage extends Carriage {
    private static final long serialVersionUID = 1L;

    public PassengerCarriage(int comfortLevel, int passengerCapacity, int baggageCapacity) {
        super(comfortLevel, passengerCapacity, baggageCapacity);
    }
}